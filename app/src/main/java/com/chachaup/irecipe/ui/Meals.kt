package com.chachaup.irecipe.ui

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chachaup.irecipe.IRecipeApplication
import com.chachaup.irecipe.R
import com.chachaup.irecipe.adapter.MealListAdapter
import com.chachaup.irecipe.data.Meal
import com.chachaup.irecipe.data.MealResponseItem
import com.chachaup.irecipe.databinding.FragmentMealsBinding
import com.chachaup.irecipe.network.MealInterface
import com.chachaup.irecipe.utils.toast
import com.chachaup.irecipe.vm.CookdVM
import com.chachaup.irecipe.vm.CookdVMFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Meals : Fragment() {

    private val sharedVM: CookdVM by activityViewModels {
        CookdVMFactory((activity?.application as IRecipeApplication).repo)
    }

    private lateinit var binding: FragmentMealsBinding

    private val adapter by lazy { MealListAdapter{meal -> adapterOnClick(meal)} }

    private val iRecipeClient: MealInterface = MealInterface.invoke()

    private val recyclerView by lazy { binding.recyclerViewRecipes }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meals, container, false)
        searchByName("")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        iRecipeClient.getMeals(meal).enqueue(object : Callback<MealResponseItem>{
            override fun onResponse(
                call: Call<MealResponseItem>,
                response: Response<MealResponseItem>
            ) {
                adapter.submitList(response.body()?.meals)
                recyclerView.layoutManager = GridLayoutManager(context,2)
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<MealResponseItem>, t: Throwable) {
                toast("Failed")
            }


        })
    }

    private fun adapterOnClick(meal: Meal){
        sharedVM.meal = meal.strMeal
        sharedVM.instructions = meal.strInstructions
        sharedVM.ingredient1 = meal.strIngredient1
        sharedVM.ingredient2 = meal.strIngredient2
        sharedVM.ingredient3 = meal.strIngredient3
        sharedVM.ingredient4 = meal.strIngredient4
        sharedVM.ingredient5 = meal.strIngredient5
        sharedVM.ingredient6 = meal.strIngredient6
        sharedVM.ingredient7 = meal.strIngredient7
        sharedVM.ingredient8 = meal.strIngredient8
        sharedVM.measure1 = meal.strMeasure1
        sharedVM.measure2 = meal.strMeasure2
        sharedVM.measure3 = meal.strMeasure3
        sharedVM.measure4 = meal.strMeasure4
        sharedVM.measure5 = meal.strMeasure5
        sharedVM.measure6 = meal.strMeasure6
        sharedVM.measure7 = meal.strMeasure7
        sharedVM.measure8 = meal.strMeasure8
        findNavController().navigate(R.id.action_meals_to_mealDetails)
    }

}