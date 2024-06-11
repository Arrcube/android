package dev.agustacandi.learn.pestsentry.ui.profile.password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.databinding.FragmentChangePasswordBinding
import dev.agustacandi.learn.pestsentry.utils.Helper
import dev.agustacandi.learn.pestsentry.utils.ext.gone
import org.koin.android.ext.android.inject

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>() {
    private val passwordViewModel : PasswordViewModel by inject()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentChangePasswordBinding = FragmentChangePasswordBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
        binding.apply {
            appbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            btnUpdatePassword.setOnClickListener {
                val newPassword = passwordTextField.text.toString()
                val confirm = confirmPasswordTextField.text.toString()
                if (newPassword.isEmpty()){
                    passwordTextField.error = getString(R.string.empty_field_error)
                }
                if (confirm.isEmpty()){
                    confirmPasswordTextField.error = getString(R.string.empty_field_error)
                }
                if (newPassword!=confirm){
                    confirmPasswordTextField.error = getString(R.string.password_equal_confirm_password)
                }
                if (newPassword.isNotEmpty() && confirm.isNotEmpty() && newPassword == confirm){
                    passwordViewModel.change(newPassword)
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
        //handle result
        binding.apply {
            passwordViewModel.changeResult.observe(viewLifecycleOwner) {
                when (it) {
                    is ApiResponse.Loading -> changeLoadingbar.visibility = View.VISIBLE
                    is ApiResponse.Success -> {
                        Helper.showSuccessToast(requireActivity(), it.data.message)
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }

                    is ApiResponse.Error -> {
                        changeLoadingbar.visibility = View.GONE
                        Helper.showErrorToast(requireActivity(), it.errorMessage)
                    }

                    else -> binding.root.gone()
                }
            }
        }
    }

}