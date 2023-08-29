package com.example.profileapp.presentation

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.profileapp.domain.model.CountriesItem

class PeopleRowBinding {

    companion object {

        @BindingAdapter("onPeopleClickListener")
        @JvmStatic
        fun onPeopleClickListener(peopleRowLayout: ConstraintLayout, result: CountriesItem) {
            peopleRowLayout.setOnClickListener {
            }
        }
    }
}