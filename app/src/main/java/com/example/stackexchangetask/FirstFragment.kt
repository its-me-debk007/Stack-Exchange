package com.example.stackexchangetask

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.stackexchangetask.databinding.FragmentFirstBinding
import com.example.stackexchangetask.domain.model.OwnerModel
import com.example.stackexchangetask.domain.model.QuestionModel
import com.example.stackexchangetask.presentation.controller.HomeEpoxyController
import com.example.stackexchangetask.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        lifecycleScope.launch {
            showUi()
        }
    }

    private suspend fun showUi() {
//        repeatOnLifecycle(Lifecycle.State.STARTED) {
//            viewModel.questions.collect {
//                when (it) {
//                    is ApiState.Loading -> {
//                        binding.progressBar.show()
//                        binding.epoxyRecyclerView.visibility = View.GONE
//                    }
//
//                    is ApiState.Error -> {
//                        Log.d("VIEWMODEL", it.errorMsg.toString())
//                        Toast.makeText(context, it.errorMsg, Toast.LENGTH_SHORT).show()
//                    }
//
//                    is ApiState.Success -> {
//                        Log.d("VIEWMODEL", it.data.toString())
//                        val epoxyController = HomeEpoxyController(requireContext(), it.data!!)
//                        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
//                        binding.epoxyRecyclerView.visibility = View.VISIBLE
//                        binding.progressBar.hide()
//                    }
//                }
//            }
//        }

        val data = listOf(
            QuestionModel(
                answer_count = 1,
                view_count = 41,
                creation_date = 1683698461,
                last_edit_date = 0,
                link = "https://stackoverflow.com/questions/76215305/what-is-faster-to-compute-in-c-x-0-or-0-x",
                owner = OwnerModel(
                    display_name = "Eviatar",
                    link = "https://stackoverflow.com/users/14950676/eviatar",
                    profile_image = "https://www.gravatar.com/avatar/0c927d3f384603c58b80c3efeac19d2a?s=256&d=identicon&r=PG&f=1"
                ),
                question_id = 76215305,
                score = 0,
                tags = listOf("c", "embedded", "compiler-optimization", "rvalue", "lvalue"),
                title = "What is faster to compute in C? (x==0) or (0==x)?"
            ),

            QuestionModel(
                answer_count = 1,
                view_count = 41,
                creation_date = 1683698461,
                last_edit_date = 0,
                link = "https://stackoverflow.com/questions/76215305/what-is-faster-to-compute-in-c-x-0-or-0-x",
                owner = OwnerModel(
                    display_name = "Eviatar",
                    link = "https://stackoverflow.com/users/14950676/eviatar",
                    profile_image = "https://www.gravatar.com/avatar/0c927d3f384603c58b80c3efeac19d2a?s=256&d=identicon&r=PG&f=1"
                ),
                question_id = 76215305,
                score = 0,
                tags = listOf("c", "embedded", "compiler-optimization", "rvalue", "lvalue"),
                title = "What is faster to compute in C? (x==0) or (0==x)?"
            ),

            QuestionModel(
                answer_count = 1,
                view_count = 41,
                creation_date = 1683698461,
                last_edit_date = 0,
                link = "https://stackoverflow.com/questions/76215305/what-is-faster-to-compute-in-c-x-0-or-0-x",
                owner = OwnerModel(
                    display_name = "Eviatar",
                    link = "https://stackoverflow.com/users/14950676/eviatar",
                    profile_image = "https://www.gravatar.com/avatar/0c927d3f384603c58b80c3efeac19d2a?s=256&d=identicon&r=PG&f=1"
                ),
                question_id = 76215305,
                score = 0,
                tags = listOf("c", "embedded", "compiler-optimization", "rvalue", "lvalue"),
                title = "What is faster to compute in C? (x==0) or (0==x)?"
            ),

            QuestionModel(
                answer_count = 1,
                view_count = 41,
                creation_date = 1683698461,
                last_edit_date = 0,
                link = "https://stackoverflow.com/questions/76215305/what-is-faster-to-compute-in-c-x-0-or-0-x",
                owner = OwnerModel(
                    display_name = "Eviatar",
                    link = "https://stackoverflow.com/users/14950676/eviatar",
                    profile_image = "https://www.gravatar.com/avatar/0c927d3f384603c58b80c3efeac19d2a?s=256&d=identicon&r=PG&f=1"
                ),
                question_id = 76215305,
                score = 0,
                tags = listOf("c", "embedded", "compiler-optimization", "rvalue", "lvalue"),
                title = "What is faster to compute in C? (x==0) or (0==x)?"
            ),

            QuestionModel(
                answer_count = 1,
                view_count = 41,
                creation_date = 1683698461,
                last_edit_date = 0,
                link = "https://stackoverflow.com/questions/76215305/what-is-faster-to-compute-in-c-x-0-or-0-x",
                owner = OwnerModel(
                    display_name = "Eviatar",
                    link = "https://stackoverflow.com/users/14950676/eviatar",
                    profile_image = "https://www.gravatar.com/avatar/0c927d3f384603c58b80c3efeac19d2a?s=256&d=identicon&r=PG&f=1"
                ),
                question_id = 76215305,
                score = 0,
                tags = listOf("c", "embedded", "compiler-optimization", "rvalue", "lvalue"),
                title = "What is faster to compute in C? (x==0) or (0==x)?"
            ),

            QuestionModel(
                answer_count = 1,
                view_count = 41,
                creation_date = 1683698461,
                last_edit_date = 0,
                link = "https://stackoverflow.com/questions/76215305/what-is-faster-to-compute-in-c-x-0-or-0-x",
                owner = OwnerModel(
                    display_name = "Eviatar",
                    link = "https://stackoverflow.com/users/14950676/eviatar",
                    profile_image = "https://www.gravatar.com/avatar/0c927d3f384603c58b80c3efeac19d2a?s=256&d=identicon&r=PG&f=1"
                ),
                question_id = 76215305,
                score = 0,
                tags = listOf("c", "embedded", "compiler-optimization", "rvalue", "lvalue"),
                title = "What is faster to compute in C? (x==0) or (0==x)?"
            ),

            QuestionModel(
                answer_count = 1,
                view_count = 41,
                creation_date = 1683698461,
                last_edit_date = 0,
                link = "https://stackoverflow.com/questions/76215305/what-is-faster-to-compute-in-c-x-0-or-0-x",
                owner = OwnerModel(
                    display_name = "Eviatar",
                    link = "https://stackoverflow.com/users/14950676/eviatar",
                    profile_image = "https://www.gravatar.com/avatar/0c927d3f384603c58b80c3efeac19d2a?s=256&d=identicon&r=PG&f=1"
                ),
                question_id = 76215305,
                score = 0,
                tags = listOf("c", "embedded", "compiler-optimization", "rvalue", "lvalue"),
                title = "What is faster to compute in C? (x==0) or (0==x)?"
            ),

            QuestionModel(
                answer_count = 1,
                view_count = 41,
                creation_date = 1683698461,
                last_edit_date = 0,
                link = "https://stackoverflow.com/questions/76215305/what-is-faster-to-compute-in-c-x-0-or-0-x",
                owner = OwnerModel(
                    display_name = "Eviatar",
                    link = "https://stackoverflow.com/users/14950676/eviatar",
                    profile_image = "https://www.gravatar.com/avatar/0c927d3f384603c58b80c3efeac19d2a?s=256&d=identicon&r=PG&f=1"
                ),
                question_id = 76215305,
                score = 0,
                tags = listOf("c", "embedded", "compiler-optimization", "rvalue", "lvalue"),
                title = "What is faster to compute in C? (x==0) or (0==x)?"
            ),
        )

        val epoxyController = HomeEpoxyController(requireContext(), data)
        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
        binding.epoxyRecyclerView.visibility = View.VISIBLE
        binding.progressBar.hide()
    }
}