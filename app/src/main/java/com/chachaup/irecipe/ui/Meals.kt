package com.chachaup.irecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chachaup.irecipe.R
import com.chachaup.irecipe.adapter.MealListAdapter
import com.chachaup.irecipe.data.Meal
import com.chachaup.irecipe.databinding.FragmentMealsBinding
import com.chachaup.irecipe.network.MealInterface
import com.chachaup.irecipe.utils.toast
import com.chachaup.irecipe.vm.CookdVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class Meals : Fragment() {

    private val sharedVM: CookdVM by activityViewModels()

    private lateinit var binding: FragmentMealsBinding

    private val mAdapter by lazy { MealListAdapter{meal -> adapterOnClick(meal)} }

    private val iRecipeClient: MealInterface = MealInterface.invoke()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meals, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchByName("")
        binding.apply {
            iSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchByName(query.toString())
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchByName(newText.toString())
                    return false
                }
            })
        }
    }

    private fun searchByName(meal: String){


        // Use coroutines to make network request
        lifecycleScope.launch {
            try {
                val response = iRecipeClient.getMeals(meal)
                mAdapter.submitList(response.meals)
                binding.recyclerViewRecipes.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = mAdapter
                    visibility = View.VISIBLE
                }
            } catch (e: Exception) {
                toast("Failed")
            } finally {
                binding.progressBarMeals.visibility = View.GONE
            }
        }
    }

    private fun adapterOnClick(meal: Meal){
        sharedVM.mealObject = meal
        findNavController().navigate(R.id.action_meals_to_mealDetails)
    }



}