package dev.agustacandi.learn.pestsentry.ui.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.databinding.FragmentEditProfileBinding
import dev.agustacandi.learn.pestsentry.utils.Helper
import org.koin.android.ext.android.inject

class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>() {
    private val editProfileViewModel : EditProfileViewModel by inject()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentEditProfileBinding = FragmentEditProfileBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
        binding.apply {
            appbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            btnUpdateProfile.setOnClickListener {
                val username = usernameTextField.text.toString()
                val email = emailTextField.text.toString()
                if (username.isEmpty()){
                    usernameTextField.error = getString(R.string.empty_field_error)
                }
                if (email.isEmpty()){
                    emailTextField.error = getString(R.string.empty_field_error)
                }
                if (username.isNotEmpty() && email.isNotEmpty()){
                    editProfileViewModel.editProfile(email,username)
                    Helper.showSuccessToast(requireActivity(),"Username and email successfully updated!")
                    editProfileViewModel.logout() //logout to refresh data after changing
                    findNavController().navigate(R.id.action_editProfileFragment_to_loginFragment)
                }
            }
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

}