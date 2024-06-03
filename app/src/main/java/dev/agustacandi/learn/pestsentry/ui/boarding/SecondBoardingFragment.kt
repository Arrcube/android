package dev.agustacandi.learn.pestsentry.ui.boarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.databinding.FragmentSecondBoardingBinding

class SecondBoardingFragment : BaseFragment<FragmentSecondBoardingBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSecondBoardingBinding = FragmentSecondBoardingBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewpager)

        binding.nextButton.setOnClickListener {
            viewPager?.currentItem = 2
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

}