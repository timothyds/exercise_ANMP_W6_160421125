package com.nativepractice.exercisew6_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nativepractice.exercisew6_anmp.R
import com.nativepractice.exercisew6_anmp.databinding.FragmentMotorListBinding
import com.nativepractice.exercisew6_anmp.viewmodel.ListViewModel

class MotorListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val motorListAdapter = MotorListAdapter(arrayListOf())
    private lateinit var binding: FragmentMotorListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMotorListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = motorListAdapter
        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
    }
    fun observeViewModel() {
        viewModel.motorLD.observe(viewLifecycleOwner, Observer {
            motorListAdapter.updateMotorList(it)
        })
        viewModel.motorLoadErrorLD.observe(viewLifecycleOwner, Observer {if(it == true) {
            binding.txtError?.visibility = View.VISIBLE
        } else {
            binding.txtError?.visibility = View.GONE
        }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {if(it == true) {
            binding.recView.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
        } else {
            binding.recView.visibility = View.VISIBLE
            binding.progressLoad.visibility = View.GONE
        }
        })


    }
}