package com.example.profileapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.profileapp.R
import com.example.profileapp.databinding.FragmentPeopleBinding
import com.example.profileapp.presentation.adapter.MainListAdapter
import com.example.profileapp.presentation.viewModel.PeopleViewModel
import com.example.profileinfo.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PeopleFragment : Fragment(R.layout.fragment_people) {
    private lateinit var binding: FragmentPeopleBinding
    private val viewModel: PeopleViewModel by viewModels()
    @Inject
    lateinit var adapter: MainListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPeopleBinding.bind(view)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvPeople.layoutManager = LinearLayoutManager(requireContext())
        requestApiData()
    }

    private fun requestApiData() {

        viewModel.getPeople()
        viewModel.peopleResponse.asLiveData().observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {

                    response.data?.let {
                        adapter.submitList(it)
                        binding.rvPeople.adapter = adapter
                    }
                }

                is NetworkResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Loading -> {
                    Log.d("Response", "Loading")
                }
            }
        }
    }
}