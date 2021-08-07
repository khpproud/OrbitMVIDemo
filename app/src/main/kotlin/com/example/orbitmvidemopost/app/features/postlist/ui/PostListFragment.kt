package com.example.orbitmvidemopost.app.features.postlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orbitmvidemopost.R
import com.example.orbitmvidemopost.app.common.NavigationEvent
import com.example.orbitmvidemopost.app.common.SeparatorDecoration
import com.example.orbitmvidemopost.app.common.viewBinding
import com.example.orbitmvidemopost.app.features.postlist.viewmodel.OpenPostNavigationEvent
import com.example.orbitmvidemopost.app.features.postlist.viewmodel.PostListState
import com.example.orbitmvidemopost.app.features.postlist.viewmodel.PostListViewModel
import com.example.orbitmvidemopost.databinding.PostListFragmentBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.orbitmvi.orbit.viewmodel.observe

class PostListFragment : Fragment(R.layout.post_list_fragment) {

    private val viewModel: PostListViewModel by stateViewModel()

    private val binding by viewBinding<PostListFragmentBinding>()

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setTitle(R.string.app_name)
            setLogo(R.drawable.ic_launcher_foreground)
        }

        binding.content.run {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(
                SeparatorDecoration(requireActivity(), R.dimen.separator_margin_start_icon, R.dimen.separator_margin_end)
            )

            adapter = groupAdapter
        }

        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::sideEffect)
    }

    private fun render(state: PostListState) {
        groupAdapter.update(state.overviews.map { PostListItem(it, viewModel)})
    }

    private fun sideEffect(sideEffect: NavigationEvent) {
        when (sideEffect) {
            is OpenPostNavigationEvent ->
                findNavController().navigate(
                    PostListFragmentDirections.actionListFragmentToDetailFragment(
                        sideEffect.post
                    )
                )
        }
    }
}