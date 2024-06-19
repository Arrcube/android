package dev.agustacandi.learn.pestsentry.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.databinding.FragmentProfileBinding
import dev.agustacandi.learn.pestsentry.utils.Helper
import dev.agustacandi.learn.pestsentry.utils.PreferenceManager
import dev.agustacandi.learn.pestsentry.utils.ext.gone
import dev.agustacandi.learn.pestsentry.utils.ext.show
import dev.agustacandi.learn.pestsentry.utils.ext.showConfirmDialog
import dev.agustacandi.learn.pestsentry.utils.ext.showSessionDialog
import org.koin.android.ext.android.inject

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private val profileViewModel : ProfileViewModel by inject()
    private val preferenceManager : PreferenceManager by inject()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
        binding.apply {
            tvProfileName.text = preferenceManager.getName
            tvProfileEmail.text = preferenceManager.getEmail
        }
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

            cardDeleteAccount.setOnClickListener {
                showConfirmDialog(
                    title = getString(R.string.are_you_sure),
                    message = getString(R.string.delete_account_dialog_description),
                    positiveButtonText = getString(R.string.yes),
                    negativeButtonText = getString(R.string.no),
                    onPositiveClick = {
                        profileViewModel.deleteAccount()
                    }
                )
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
                        profileViewModel.logout()
                        findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
                    }
                )
            }
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
        profileViewModel.changeResult.observe(viewLifecycleOwner) { result ->
            binding.apply {
                when(result) {
                    is ApiResponse.Loading -> {
                        loadingOverlay.root.show()
                    }

                    is ApiResponse.Success -> {
                        loadingOverlay.root.gone()
                        Helper.showSuccessToast(requireActivity(), result.data.message)
                        profileViewModel.logout()
                        findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
                    }

                    is ApiResponse.Error -> {
                        if (result.errorMessage.contains("401")) {
                            showSessionDialog(
                                onClick = {
                                    try {
                                        preferenceManager.clearAllPreferences()
                                        Helper.reloadKoinModules()
                                        findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
                                    } catch (e: Exception) {
                                        Helper.showErrorToast(
                                            requireActivity(),
                                            e.message.toString()
                                        )
                                    }
                                }
                            )
                        }
                        loadingOverlay.root.gone()
                        Helper.showErrorToast(requireActivity(), result.errorMessage)
                    }

                }
            }
        }
    }

}