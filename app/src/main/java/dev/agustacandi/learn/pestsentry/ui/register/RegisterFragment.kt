package dev.agustacandi.learn.pestsentry.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shashank.sony.fancytoastlib.FancyToast
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.databinding.FragmentRegisterBinding
import dev.agustacandi.learn.pestsentry.utils.ext.gone
import org.koin.android.ext.android.inject

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    private val registerViewModel : RegisterViewModel by inject()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
    }

    override fun initAction() {
        binding.apply {
            ivRegisterBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
            btnRegister.setOnClickListener {
                val username = etRegisUsername.text.toString()
                val email = etRegisEmail.text.toString()
                val password = etRegisPassword.text.toString()
                val confirm = etRegisConfirm.text.toString()
                if (username.isEmpty()){
                    etRegisUsername.error = getString(R.string.empty_field_error)
                }
                if (email.isEmpty()){
                    etRegisEmail.error = getString(R.string.empty_field_error)
                }
                if (password.isEmpty()){
                    etRegisPassword.error = getString(R.string.empty_field_error)
                }
                if (confirm.isEmpty()){
                    etRegisConfirm.error = getString(R.string.empty_field_error)
                }
                if (confirm != password){
                    etRegisConfirm.error = getString(R.string.password_equal_confirm_password)
                }
                if (email.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty() && confirm.isNotEmpty() && confirm == password){
                    registerViewModel.register(username, email, password)
                }
            }
        }
    }

    override fun initProcess() {
        binding.apply {
            registerViewModel.registerResult.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ApiResponse.Loading -> registerLoadingbar.visibility = View.VISIBLE
                    is ApiResponse.Success -> {
                        registerLoadingbar.visibility = View.GONE
                        FancyToast.makeText(
                            requireContext(),
                            result.data.message,
                            FancyToast.LENGTH_LONG,
                            FancyToast.SUCCESS,
                            false
                        ).show()
                        findNavController().popBackStack()
                    }

                    is ApiResponse.Error -> {
                        registerLoadingbar.visibility = View.GONE
                        FancyToast.makeText(
                            requireContext(),
                            result.errorMessage,
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,
                            false
                        ).show()
                    }

                    else -> binding.root.gone()
                }
            }
        }
    }

    override fun initObservers() {
    }

}