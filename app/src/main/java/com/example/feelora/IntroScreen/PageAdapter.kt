package com.example.feelora.IntroScreen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(
    activity: IntroActivity,
    private val fragment: List<Fragment>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return fragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragment[position]
    }
}