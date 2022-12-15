package com.djenius.inventoryapps.authentication.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.djenius.inventoryapps.R
import com.djenius.inventoryapps.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment: Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var imm: InputMethodManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fragment = this
        binding.viewmodel = viewModel
        binding.form = viewModel.form

        imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                val message = when(it) {
                    RegisterError.UNAVAILABLE -> {
                        getString(R.string.no_internet_msg)
                    }
                }
                showMessage(message)
                viewModel.clearError()
            }
        }
        viewModel.errorMsg.observe(viewLifecycleOwner) {
            it?.let {
                showMessage(it)
                viewModel.clearErrorMsg()
            }
        }
        viewModel.success.observe(viewLifecycleOwner) {
            if(it) {
                gotoEmailVerify(
                    viewModel.form.email
                )
            }
        }
    }

    fun register() {
        hideKeyboard()
        viewModel.register()
    }

    private fun showMessage(msg: String) {
        val snack = Snackbar.make(binding.registerLayout, msg, Snackbar.LENGTH_LONG)
        snack.show()
    }

    private fun hideKeyboard() {
        view?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun gotoEmailVerify(registeredEmail: String) {
        val action = RegisterFragmentDirections
            .actionRegisterFragmentToEmailVerifyFragment(registeredEmail)
        findNavController().navigate(action)
    }
}