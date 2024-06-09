package dev.agustacandi.learn.pestsentry.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.databinding.FragmentProfileBinding
import dev.agustacandi.learn.pestsentry.utils.ext.showConfirmDialog

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
        binding.apply {
            cardEditProfile.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
            }

            cardPassword.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_changePasswordFragment)
            }

            cardLanguage.setOnClickListener {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }

            cardAbout.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_aboutFragment)
            }

            cardLogout.setOnClickListener {
                showConfirmDialog(
                    title = getString(R.string.are_you_sure),
                    message = "",
                    positiveButtonText = getString(R.string.yes),
                    negativeButtonText = getString(R.string.no),
                    onPositiveClick = {
                        val prefs = prefe
                        findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
                    }
                )
            }
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

}