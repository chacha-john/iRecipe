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
import com.chachaup.irecipe.databinding.FragmentMealDetailsBinding
import com.chachaup.irecipe.databinding.FragmentMealsBinding
import com.chachaup.irecipe.utils.toast
import com.chachaup.irecipe.vm.CookdVM
import com.chachaup.irecipe.vm.CookdVMFactory
import com.squareup.picasso.Picasso

class MealDetails : Fragment() {

    private val sharedVM: CookdVM by activityViewModels {
        CookdVMFactory((activity?.application as IRecipeApplication).repo)
    }

    private lateinit var binding: FragmentMealDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meal_details, container, false)
        binding.strMeal.text = sharedVM.mealObject.strMeal
        binding.strInstructions.text = sharedVM.mealObject.strInstructions
        binding.ingredient1.text = sharedVM.mealObject.strIngredient1
        binding.ingredient2.text = sharedVM.mealObject.strIngredient2
        binding.ingredient3.text = sharedVM.mealObject.strIngredient3
        binding.ingredient4.text = sharedVM.mealObject.strIngredient4
        binding.ingredient5.text = sharedVM.mealObject.strIngredient5
        binding.ingredient6.text = sharedVM.mealObject.strIngredient6
        binding.ingredient7.text = sharedVM.mealObject.strIngredient7
        binding.ingredient8.text = sharedVM.mealObject.strIngredient8
        binding.measure1.text = sharedVM.mealObject.strMeasure1
        binding.measure2.text = sharedVM.mealObject.strMeasure2
        binding.measure3.text = sharedVM.mealObject.strMeasure3
        binding.measure4.text = sharedVM.mealObject.strMeasure4
        binding.measure5.text = sharedVM.mealObject.strMeasure5
        binding.measure6.text = sharedVM.mealObject.strMeasure6
        binding.measure7.text = sharedVM.mealObject.strMeasure7
        binding.measure8.text = sharedVM.mealObject.strMeasure8
        Picasso.get().load(sharedVM.mealObject.strMealThumb).into(binding.mealThumbnail)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonCancel.setOnClickListener{
                findNavController().navigate(R.id.action_mealDetails_to_meals)
            }
            buttonSave.setOnClickListener {
                toast("You have discovered a premium feature")
            }
        }
    }


}