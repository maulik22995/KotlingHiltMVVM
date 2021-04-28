package com.example.myapplication.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.R
import com.example.myapplication.common.helper.SwipeToDeleteCallback
import com.example.myapplication.data.model.Content
import com.example.myapplication.databinding.ListFragmentBinding
import com.example.myapplication.ui.list.adapter.ListReleaseDocAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListFragment : Fragment(R.layout.list_fragment),SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: ListViewModel by viewModels()
    lateinit var  binding : ListFragmentBinding
    private val adapter : ListReleaseDocAdapter by lazy {
        ListReleaseDocAdapter(::onItemClick)
    }

    private fun onItemClick(content: Content) {
        val directions = ListFragmentDirections.actionListFragmentToDetailFragment(content)
        NavHostFragment.findNavController(this).navigate(directions)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = ListFragmentBinding.bind(view)
        binding.refLayout.setOnRefreshListener(this)
        binding.reyListData.itemAnimator = DefaultItemAnimator()
        binding.reyListData.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.reyListData.adapter = adapter
        setSwipeToDelete()
        onRefresh()
    }

    private fun setSwipeToDelete() {
        val itemTouchhelper = ItemTouchHelper(object : SwipeToDeleteCallback(this.requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (viewHolder is ListReleaseDocAdapter.MyViewHolder) {
                    // remove the item from recycler view
                    adapter.removeItem(viewHolder.adapterPosition)
                }
            }

        })
        itemTouchhelper.attachToRecyclerView(binding.reyListData)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.liveDataPressRelease.observe(viewLifecycleOwner, Observer {
            binding.refLayout.isRefreshing = false
            adapter.addAllItem(it.content)
        })
    }

    override fun onRefresh() {
        binding.refLayout.isRefreshing = true
        viewModel.fetchAllData()
    }


}