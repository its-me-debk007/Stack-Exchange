package com.example.stackexchangetask.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.stackexchangetask.R
import com.example.stackexchangetask.databinding.FragmentSearchBinding
import com.example.stackexchangetask.presentation.activity.WebViewActivity
import com.example.stackexchangetask.presentation.controller.ClickListener
import com.example.stackexchangetask.presentation.controller.HomeEpoxyController
import com.example.stackexchangetask.presentation.viewmodel.SearchViewModel
import com.example.stackexchangetask.util.ApiState
import com.google.android.material.chip.Chip
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search), ClickListener {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private var epoxyController: HomeEpoxyController? = null
    var tagged = mutableListOf<String>()
    var firstSearch = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        var isChipChecked = false
        var searchQuestionsText = ""
        var searchTagsText = ""

        binding.tagMode.setOnCheckedChangeListener { _, isChecked ->

            isChipChecked = isChecked

            binding.textInputEditText.hint = if (isChecked) "Enter Tags" else "Search Questions"

            if (isChecked) {
                searchQuestionsText = binding.textInputEditText.text.toString()
                binding.textInputEditText.text = SpannableStringBuilder(searchTagsText)

            } else {
                searchTagsText = binding.textInputEditText.text.toString()
                binding.textInputEditText.text = SpannableStringBuilder(searchQuestionsText)
            }

            binding.textInputEditText.selectAll()
        }

        binding.textInputEditText.setOnEditorActionListener { _, _, _ ->
            if (isChipChecked) {
                if (binding.textInputEditText.text?.isNotBlank() == true)
                    addChip(binding.textInputEditText.text.toString().trim())
            } else {

                firstSearch = false

                viewModel.searchQuestions(
                    binding.textInputEditText.text.toString(),
                    tagged.joinToString(";")
                )
            }
            true
        }

        binding.searchBtn.setOnClickListener {
            firstSearch = false

            viewModel.searchQuestions(
                binding.textInputEditText.text.toString(),
                tagged.joinToString(";")
            )

            Log.d("TAGGED", tagged.joinToString(";"))
        }

        lifecycleScope.launch {
            showAndUpdateUi()
        }
    }

    private suspend fun showAndUpdateUi() {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.questions.collect {
                when (it) {
                    is ApiState.Loading -> {
                        if (firstSearch) {
                            return@collect
                        }

                        binding.progressBar.show()
                        binding.placeholderText.visibility = View.GONE
                        binding.epoxyRecyclerView.visibility = View.GONE
                    }

                    is ApiState.Error -> {
                        Log.d("VIEWMODEL", it.errorMsg.toString())
                        Toast.makeText(context, it.errorMsg, Toast.LENGTH_SHORT).show()

                        binding.epoxyRecyclerView.visibility = View.GONE
                        binding.placeholderText.visibility = View.VISIBLE
                        binding.placeholderText.text = "No or Poor Internet connection :("
                        binding.progressBar.hide()
                    }

                    is ApiState.Success -> {

                        if (it.data!!.isEmpty()) {
                            binding.placeholderText.visibility = View.VISIBLE
                            binding.placeholderText.text = "No questions found :("
                            binding.progressBar.hide()
                            return@collect
                        }

                        epoxyController?.updateData(it.data) ?: run {

                            epoxyController = HomeEpoxyController(requireContext(), it.data, this@SearchFragment)
                            binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController!!)
                        }

                        binding.epoxyRecyclerView.visibility = View.VISIBLE
                        binding.placeholderText.visibility = View.GONE
                        binding.progressBar.hide()
                    }
                }
            }
        }
    }

    private fun addChip(text: String) {
        Chip(context).apply {
            this.text = text
            isCloseIconVisible = true
            setOnCloseIconClickListener {
                binding.tagsChipGroup.removeView(this)
                tagged.remove(this.text)
            }

            if (text !in tagged) {
                tagged.add(text)

                binding.tagsChipGroup.addView(this)
                binding.textInputEditText.text = null

                binding.scrollView.postDelayed({

                    binding.scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT)

                }, 100)
            }
        }
    }

    override fun onLinkClick(link: String) {
        Intent(context, WebViewActivity::class.java).apply {
            putExtra("LINK", link)
            startActivity(this)
        }
    }
}