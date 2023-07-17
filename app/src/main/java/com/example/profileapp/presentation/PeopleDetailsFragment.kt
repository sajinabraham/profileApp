package com.example.profileapp.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.profileapp.R
import com.example.profileapp.databinding.FragmentPeopleDetailsBinding
import com.example.utils.DateUtils

class PeopleDetailsFragment : Fragment(R.layout.fragment_people_details) {
    private lateinit var binding: FragmentPeopleDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPeopleDetailsBinding.bind(view)
        setProfile()
    }

    private fun setProfile() {
        val args: PeopleDetailsFragmentArgs by navArgs()
        binding.apply {
            with(itemImage) {
                profileCIV.load(args.peopleModel?.avatar)
                tvJobtitle.text = args.peopleModel?.jobtitle
                ("My Favorite Colour: " + args.peopleModel?.favouriteColor).also {
                    tvFavColor.text = it
                }
            }
            (args.peopleModel?.firstName + args.peopleModel?.lastName).also { buildString { append(" ") } }
            itemEmail.tvEmail.text = args.peopleModel?.email
            ("Profile Created at: " +
                    args.peopleModel!!.getDateTimeObj()?.let {
                        DateUtils.getFormattedDate(it, DateUtils.DIS_DATE_FORMAT_PATTERN1)
                    }).also { itemDate.tvDate.text = it }
        }


    }
}