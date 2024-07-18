package com.custard.openinapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.custard.openinapp.ui.frag.recentLink.RecentLinkFragment
import com.custard.openinapp.ui.frag.topLink.TopLinkFragment

class ViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 ->  {
                RecentLinkFragment()
            }
            1 -> {
                TopLinkFragment()
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }
}