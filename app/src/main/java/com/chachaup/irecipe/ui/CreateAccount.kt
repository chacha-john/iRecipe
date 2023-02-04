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
import com.chachaup.irecipe.data.User
import com.chachaup.irecipe.databinding.FragmentCreateAccountBinding
import com.chachaup.irecipe.utils.toast
import com.chachaup.irecipe.vm.CookdVM
import com.chachaup.irecipe.vm.CookdVMFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class CreateAccount : Fragment() {

    private lateinit var binding: FragmentCreateAccountBinding

    private val sharedViewModel: CookdVM by activityViewModels { CookdVMFactory((activity?.application as IRecipeApplication).repo) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_create_account, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonCreateAccount.setOnClickListener {
                val user = User(editTextEmail.text.toString(), editTextPassword.text.toString())
                sharedViewModel.createUser(user).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        toast("Success")
                    }
                    else{
                        toast("There was an error creating new user!")
                    }
                }
                findNavController().navigate(R.id.action_createAccount_to_login)
            }
        }
    }

}