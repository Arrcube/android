package dev.agustacandi.learn.pestsentry.ui.boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.databinding.FragmentThirdBoardingBinding
import dev.agustacandi.learn.pestsentry.utils.Helper
import dev.agustacandi.learn.pestsentry.utils.PreferenceManager
import org.koin.android.ext.android.inject

class ThirdBoardingFragment : BaseFragment<FragmentThirdBoardingBinding>() {

    private val preferenceManager: PreferenceManager by inject()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentThirdBoardingBinding = FragmentThirdBoardingBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
    }

    override fun initProcess() {
        binding.getStartedButton.setOnClickListener {
            preferenceManager.setOnboardingScreenPreference()
            Helper.reloadKoinModules()
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)
        }
    }

    override fun initObservers() {
    }

}