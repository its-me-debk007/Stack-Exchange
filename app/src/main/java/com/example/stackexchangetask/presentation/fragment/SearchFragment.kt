package com.example.stackexchangetask.presentation.fragment

import android.os.Bundle
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
import com.example.stackexchangetask.presentation.controller.HomeEpoxyController
import com.example.stackexchangetask.presentation.viewmodel.SearchViewModel
import com.example.stackexchangetask.util.ApiState
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private var epoxyController: HomeEpoxyController? = null
    var tagged = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        binding.tagMode.setOnCheckedChangeListener { _, isChecked ->

            binding.textInputEditText.hint = if (isChecked) "Enter Tags" else "Search Questions"

            binding.textInputEditText.setOnEditorActionListener { _, actionId, _ ->
                if (isChecked) {
                    if (binding.textInputEditText.text?.isNotBlank() == true)
                        addChip(binding.textInputEditText.text.toString().trim())
                } else {

                    viewModel.searchQuestions(
                        binding.textInputEditText.text.toString(),
                        tagged.joinToString(";")
                    )
                }
                true
            }
        }

        binding.searchBtn.setOnClickListener {
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
                        binding.progressBar.show()
                        binding.epoxyRecyclerView.visibility = View.GONE
                    }

                    is ApiState.Error -> {
                        Log.d("VIEWMODEL", it.errorMsg.toString())
                        Toast.makeText(context, it.errorMsg, Toast.LENGTH_SHORT).show()
                    }

                    is ApiState.Success -> {

                        epoxyController?.updateData(it.data!!) ?: run {

                            epoxyController = HomeEpoxyController(requireContext(), it.data!!)
                            binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController!!)
                        }

                        binding.epoxyRecyclerView.visibility = View.VISIBLE
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
}