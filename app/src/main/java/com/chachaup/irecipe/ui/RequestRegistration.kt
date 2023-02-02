package com.chachaup.irecipe.ui

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.chachaup.irecipe.R
import com.chachaup.irecipe.databinding.FragmentRequestRegistrationBinding

class RequestRegistration : Fragment() {
    private lateinit var binding: FragmentRequestRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_request_registration, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonProceedToLogin.setOnClickListener{
            findNavController().navigate(R.id.login)
        }
    }
}