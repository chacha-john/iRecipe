package com.chachaup.irecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.chachaup.irecipe.R
import com.chachaup.irecipe.adapter.MealListAdapter
import com.chachaup.irecipe.data.MealResponseItem
import com.chachaup.irecipe.databinding.FragmentMealsBinding
import com.chachaup.irecipe.network.MealInterface
import com.chachaup.irecipe.utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Meals : Fragment() {

    private lateinit var binding: FragmentMealsBinding

    private val adapter by lazy { MealListAdapter() }

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
        val rv = binding.recyclerViewRecipes
        iRecipeClient.getMeals("meat").enqueue(object : Callback<MealResponseItem>{

            override fun onResponse(
                call: Call<MealResponseItem>,
                response: Response<MealResponseItem>
            ) {
                adapter.submitList(response.body()?.meals)
                rv.adapter = adapter
            }

            override fun onFailure(call: Call<MealResponseItem>, t: Throwable) {
                toast("Failed")
            }


        })
        binding.apply {

        }
    }

}