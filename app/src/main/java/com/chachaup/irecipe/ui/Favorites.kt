package com.chachaup.irecipe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.chachaup.irecipe.IRecipeApplication
import com.chachaup.irecipe.R
import com.chachaup.irecipe.databinding.FragmentCreateAccountBinding
import com.chachaup.irecipe.databinding.FragmentFavoritesBinding
import com.chachaup.irecipe.vm.CookdVM
import com.chachaup.irecipe.vm.CookdVMFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Favorites : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding

    private val sharedViewModel: CookdVM by activityViewModels { CookdVMFactory((activity?.application as IRecipeApplication).repo) }

    private lateinit var authStateListener: AuthStateListener
    
    @Inject lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        authStateListener = AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user == null){
                findNavController().navigate(R.id.requestRegistration)
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onStop() {
        super.onStop()
        // add this line to prevent memory leak
        firebaseAuth.removeAuthStateListener(authStateListener)
    }

}