package com.example.profileapp.presentation

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.profileapp.domain.model.PeopleResult

class PeopleRowBinding {

    companion object {

        @BindingAdapter("onPeopleClickListener")
        @JvmStatic
        fun onPeopleClickListener(peopleRowLayout: ConstraintLayout, result: PeopleResult) {
            peopleRowLayout.setOnClickListener {
                try {
                    Log.d("onPeopleClickListener", "CALLED")
                    val action =
                        PeopleFragmentDirections.actionMainFragmentToProfileDetailsFragment(result)
                    peopleRowLayout.findNavController().navigate(action)

                } catch (e: Exception) {
                    Log.d("onPeopleClickListener", e.toString())
                }
            }
        }
    }
}