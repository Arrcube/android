package dev.agustacandi.learn.pestsentry.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.databinding.FragmentLoginBinding
import dev.agustacandi.learn.pestsentry.utils.Helper
import dev.agustacandi.learn.pestsentry.utils.ext.gone
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val loginViewModel : LoginViewModel by inject()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
        binding.apply {
            loginButton.setOnClickListener {
                val email = etLoginEmail.text.toString()
                val password = etLoginPassword.text.toString()
                if (email.isEmpty()) {
                    etLoginEmail.error = getString(R.string.empty_field_error)
                }
                if (password.isEmpty()){
                    etLoginPassword.error = getString(R.string.empty_field_error)
                }
                if (email.isNotEmpty() && password.isNotEmpty()){
                    loginViewModel.login(email, password)
                }
            }
            btnToRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    override fun initProcess() {
        //handle login result
        binding.apply {
            loginViewModel.loginResult.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ApiResponse.Loading -> loginLoadingbar.visibility = View.VISIBLE
                    is ApiResponse.Success -> {
                        Helper.showSuccessToast(requireActivity(), result.data.message)
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }

                    is ApiResponse.Error -> {
                        loginLoadingbar.visibility = View.GONE
                        Helper.showErrorToast(requireActivity(), result.errorMessage)
                    }

                    else -> binding.root.gone()
                }
            }
        }
    }

    override fun initObservers() {
    }

}