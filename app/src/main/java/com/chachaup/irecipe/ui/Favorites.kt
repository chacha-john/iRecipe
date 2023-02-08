package com.chachaup.irecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chachaup.irecipe.R
import com.chachaup.irecipe.adapter.FirebaseAdapter
import com.chachaup.irecipe.adapter.MealListAdapter
import com.chachaup.irecipe.data.Meal
import com.chachaup.irecipe.databinding.FragmentFavoritesBinding
import com.chachaup.irecipe.utils.Constants
import com.chachaup.irecipe.utils.toast
import com.chachaup.irecipe.vm.CookdVM
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.database.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class Favorites : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding

    private val sharedVM: CookdVM by activityViewModels()

    private lateinit var firebaseAdapter: FirebaseAdapter

    private lateinit var authStateListener: AuthStateListener

    private lateinit var databaseRef: DatabaseReference

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        authStateListener = AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user == null) {
                findNavController().navigate(R.id.requestRegistration)
            } else {
                firebaseAdapter = FirebaseAdapter {
                    adapterOnClick(it)
                }
                binding.recyclerViewRecipes.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = firebaseAdapter
                    visibility = View.VISIBLE
                }

                val databaseReference = FirebaseDatabase.getInstance().reference.child(user.uid)
                databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val meals = snapshot.children.mapNotNull {
                            it.getValue(Meal::class.java)
                        }
                        firebaseAdapter.submitList(meals)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        toast(error.message)
                    }
                })
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

    private fun adapterOnClick(meal: Meal) {
        sharedVM.mealObject = meal
        findNavController().navigate(R.id.action_favorites_to_mealDetails)
    }
}