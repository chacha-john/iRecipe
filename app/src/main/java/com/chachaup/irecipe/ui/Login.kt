package com.chachaup.irecipe.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.TAG
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.chachaup.irecipe.IRecipeApplication
import com.chachaup.irecipe.MainActivity
import com.chachaup.irecipe.R
import com.chachaup.irecipe.databinding.FragmentLoginBinding
import com.chachaup.irecipe.utils.toast
import com.chachaup.irecipe.vm.CookdVM
import com.chachaup.irecipe.vm.CookdVMFactory
import com.google.firebase.auth.FirebaseAuth

class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val sharedVM: CookdVM by activityViewModels { CookdVMFactory((activity?.application as IRecipeApplication).repo) }

    private val mAuth = FirebaseAuth.getInstance()

    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                findNavController().navigate(R.id.action_login_to_meals)
                sharedVM.updateBottomNavVisibility(true)
            }

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonLogin.setOnClickListener {
                mAuth.signInWithEmailAndPassword(editTextEmail.text.toString(), editTextPassword.text.toString())
                    .addOnCompleteListener { task ->
                        if (!task.isSuccessful){
                            toast(task.exception.toString())
                        }
                     }
                findNavController().navigate(R.id.action_login_to_meals)
                sharedVM.updateBottomNavVisibility(true)
            }
            textViewLinkCreateAccount.setOnClickListener { findNavController().navigate(R.id.action_login_to_createAccount) }
        }
    }

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(authStateListener)
    }

    override fun onStop() {
        super.onStop()
        if (authStateListener != null){
            mAuth.removeAuthStateListener(authStateListener)
        }
    }

}