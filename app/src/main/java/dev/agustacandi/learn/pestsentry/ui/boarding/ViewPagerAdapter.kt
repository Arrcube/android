package dev.agustacandi.learn.pestsentry.ui.boarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.agustacandi.learn.pestsentry.base.BaseFragment

class ViewPagerAdapter(list: ArrayList<BaseFragment<out ViewBinding>>, fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    private val fragmentList = list
    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}