package com.djenius.inventoryapps.authentication.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.djenius.inventoryapps.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment: Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewmodel = viewModel
        binding.form = viewModel.form
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observesData()
    }

    private fun observesData() {
        viewModel.errorMsg.observe(viewLifecycleOwner) {
            it?.let {
                Snackbar.make(binding.registerLayout, it, Snackbar.LENGTH_LONG).show()
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

    private fun gotoEmailVerify(registeredEmail: String) {
        val action = RegisterFragmentDirections
            .actionRegisterFragmentToEmailVerifyFragment(registeredEmail)
        findNavController().navigate(action)
    }
}