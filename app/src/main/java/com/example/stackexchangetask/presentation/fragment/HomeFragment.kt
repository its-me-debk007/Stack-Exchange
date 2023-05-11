package com.example.stackexchangetask.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.stackexchangetask.R
import com.example.stackexchangetask.databinding.FragmentHomeBinding
import com.example.stackexchangetask.presentation.activity.WebViewActivity
import com.example.stackexchangetask.presentation.controller.ClickListener
import com.example.stackexchangetask.presentation.controller.HomeEpoxyController
import com.example.stackexchangetask.presentation.viewmodel.HomeViewModel
import com.example.stackexchangetask.util.ApiState
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        lifecycleScope.launch {
            showUi()
        }
    }

    private suspend fun showUi() {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.questions.collect {
                when (it) {
                    is ApiState.Loading -> {
                        binding.progressBar.show()
                        binding.epoxyRecyclerView.visibility = View.GONE
                    }

                    is ApiState.Error -> {
                        Toast.makeText(context, it.errorMsg, Toast.LENGTH_SHORT).show()
                    }

                    is ApiState.Success -> {
                        val epoxyController = HomeEpoxyController(requireContext(), it.data!!, this@HomeFragment)
                        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
                        binding.epoxyRecyclerView.visibility = View.VISIBLE
                        binding.progressBar.hide()
                    }
                }
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