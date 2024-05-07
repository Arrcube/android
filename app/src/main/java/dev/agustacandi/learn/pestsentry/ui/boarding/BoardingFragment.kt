package dev.agustacandi.learn.pestsentry.ui.boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.databinding.FragmentBoardingBinding

class BoardingFragment : BaseFragment<FragmentBoardingBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBoardingBinding = FragmentBoardingBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

}