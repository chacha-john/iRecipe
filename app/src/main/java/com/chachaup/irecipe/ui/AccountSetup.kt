package com.chachaup.irecipe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.chachaup.irecipe.R
import com.chachaup.irecipe.databinding.FragmentAccountSetupBinding

class AccountSetup : Fragment() {
    private lateinit var binding: FragmentAccountSetupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account_setup, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // set click listener for both buttons
            buttonNope.setOnClickListener {
                // navigate to the next fragment
                findNavController().navigate(R.id.action_accountSetup_to_meals)
            }
            buttonYes.setOnClickListener {
                // navigate to the next fragment
                findNavController().navigate(R.id.action_accountSetup_to_meals)
            }
        }
    }

}