package com.chachaup.irecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.chachaup.irecipe.IRecipeApplication
import com.chachaup.irecipe.R
import com.chachaup.irecipe.databinding.FragmentLoginBinding
import com.chachaup.irecipe.vm.CookdVM
import com.chachaup.irecipe.vm.CookdVMFactory

class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val sharedVM: CookdVM by activityViewModels { CookdVMFactory((activity?.application as IRecipeApplication).repo) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonLogin.setOnClickListener {
                findNavController().navigate(R.id.action_login_to_meals)
                sharedVM.updateBottomNavVisibility(true)
            }
            textViewLinkCreateAccount.setOnClickListener { findNavController().navigate(R.id.action_login_to_createAccount) }
        }
    }

}