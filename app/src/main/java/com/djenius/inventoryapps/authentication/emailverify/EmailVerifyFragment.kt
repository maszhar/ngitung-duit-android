package com.djenius.inventoryapps.authentication.emailverify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.djenius.inventoryapps.databinding.FragmentEmailVerifyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailVerifyFragment: Fragment() {
    val args: EmailVerifyFragmentArgs by navArgs()
    val viewModel: EmailVerifyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEmailVerifyBinding.inflate(
            inflater,
            container,
            false
        )
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setRegisteredEmail(args.registeredEmail)
    }
}