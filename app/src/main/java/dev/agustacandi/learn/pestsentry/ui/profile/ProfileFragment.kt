package dev.agustacandi.learn.pestsentry.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)

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