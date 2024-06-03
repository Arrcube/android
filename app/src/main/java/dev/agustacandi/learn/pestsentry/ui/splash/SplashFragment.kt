package dev.agustacandi.learn.pestsentry.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.databinding.FragmentSplashBinding
import dev.agustacandi.learn.pestsentry.utils.ConstVal
import dev.agustacandi.learn.pestsentry.utils.PreferenceManager
import dev.agustacandi.learn.pestsentry.utils.ext.gone
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.time.Duration.Companion.seconds

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    private val preferenceManager: PreferenceManager by inject()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
    }

    override fun initProcess() {
        lifecycleScope.launch {
            delay(ConstVal.SPLASH_SCREEN_DURATION.seconds)
            if (preferenceManager.getOnboardingScreen) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }
    }


    override fun initObservers() {
    }

}