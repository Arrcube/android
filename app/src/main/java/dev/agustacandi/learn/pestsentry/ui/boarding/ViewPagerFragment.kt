package dev.agustacandi.learn.pestsentry.ui.boarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.databinding.FragmentBoardingBinding
import dev.agustacandi.learn.pestsentry.databinding.FragmentViewPagerBinding

class ViewPagerFragment : BaseFragment<FragmentViewPagerBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentViewPagerBinding = FragmentViewPagerBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
    }

    override fun initProcess() {
        val fragmentList = arrayListOf(
            BoardingFragment(),
            SecondBoardingFragment(),
            ThirdBoardingFragment()

        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewpager.adapter = adapter
    }

    override fun initObservers() {
    }

}