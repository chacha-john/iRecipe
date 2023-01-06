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
        binding.strMeal.text = sharedVM.meal
        binding.strInstructions.text = sharedVM.instructions
        binding.ingredient1.text = sharedVM.ingredient1
        binding.ingredient2.text = sharedVM.ingredient2
        binding.ingredient3.text = sharedVM.ingredient3
        binding.ingredient4.text = sharedVM.ingredient4
        binding.ingredient5.text = sharedVM.ingredient5
        binding.ingredient6.text = sharedVM.ingredient6
        binding.ingredient7.text = sharedVM.ingredient7
        binding.ingredient8.text = sharedVM.ingredient8
        binding.measure1.text = sharedVM.measure1
        binding.measure2.text = sharedVM.measure2
        binding.measure3.text = sharedVM.measure3
        binding.measure4.text = sharedVM.measure4
        binding.measure5.text = sharedVM.measure5
        binding.measure6.text = sharedVM.measure6
        binding.measure7.text = sharedVM.measure7
        binding.measure8.text = sharedVM.measure8
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