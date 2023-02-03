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
import com.chachaup.irecipe.utils.Constants
import com.chachaup.irecipe.utils.toast
import com.chachaup.irecipe.vm.CookdVM
import com.chachaup.irecipe.vm.CookdVMFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
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
        sharedVM.updateBottomNavVisibility(false)
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
        binding.ingredient9.text = sharedVM.mealObject.strIngredient9
        binding.ingredient10.text = sharedVM.mealObject.strIngredient10
        binding.ingredient11.text = sharedVM.mealObject.strIngredient11
        binding.ingredient12.text = sharedVM.mealObject.strIngredient12
        binding.ingredient13.text = sharedVM.mealObject.strIngredient13
        binding.ingredient14.text = sharedVM.mealObject.strIngredient14
        binding.ingredient15.text = sharedVM.mealObject.strIngredient15
        binding.ingredient16.text = sharedVM.mealObject.strIngredient16.toString()
        binding.ingredient17.text = sharedVM.mealObject.strIngredient17.toString()
        binding.ingredient18.text = sharedVM.mealObject.strIngredient18.toString()
        binding.ingredient19.text = sharedVM.mealObject.strIngredient19.toString()
        binding.ingredient20.text = sharedVM.mealObject.strIngredient20.toString()
        binding.measure1.text = sharedVM.mealObject.strMeasure1
        binding.measure2.text = sharedVM.mealObject.strMeasure2
        binding.measure3.text = sharedVM.mealObject.strMeasure3
        binding.measure4.text = sharedVM.mealObject.strMeasure4
        binding.measure5.text = sharedVM.mealObject.strMeasure5
        binding.measure6.text = sharedVM.mealObject.strMeasure6
        binding.measure7.text = sharedVM.mealObject.strMeasure7
        binding.measure8.text = sharedVM.mealObject.strMeasure8
        binding.measure9.text = sharedVM.mealObject.strMeasure9
        binding.measure10.text = sharedVM.mealObject.strMeasure10
        binding.measure11.text = sharedVM.mealObject.strMeasure11
        binding.measure12.text = sharedVM.mealObject.strMeasure12
        binding.measure13.text = sharedVM.mealObject.strMeasure13
        binding.measure14.text = sharedVM.mealObject.strMeasure14
        binding.measure15.text = sharedVM.mealObject.strMeasure15
        binding.measure16.text = sharedVM.mealObject.strMeasure16.toString()
        binding.measure17.text = sharedVM.mealObject.strMeasure17.toString()
        binding.measure18.text = sharedVM.mealObject.strMeasure18.toString()
        binding.measure19.text = sharedVM.mealObject.strMeasure19.toString()
        binding.measure20.text = sharedVM.mealObject.strMeasure20.toString()
        Picasso.get().load(sharedVM.mealObject.strMealThumb).into(binding.mealThumbnail)

        // if the ingredients are empty, hide the textview
        if (sharedVM.mealObject.strIngredient1.isEmpty()) {
            binding.ingredient1.visibility = View.GONE
            binding.measure1.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient2.isEmpty()) {
            binding.ingredient2.visibility = View.GONE
            binding.measure2.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient3.isEmpty()) {
            binding.ingredient3.visibility = View.GONE
            binding.measure3.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient4.isEmpty()) {
            binding.ingredient4.visibility = View.GONE
            binding.measure4.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient5.isEmpty()) {
            binding.ingredient5.visibility = View.GONE
            binding.measure5.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient6.isEmpty()) {
            binding.ingredient6.visibility = View.GONE
            binding.measure6.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient7.isEmpty()) {
            binding.ingredient7.visibility = View.GONE
            binding.measure7.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient8.isEmpty()) {
            binding.ingredient8.visibility = View.GONE
            binding.measure8.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient9.isEmpty()) {
            binding.ingredient9.visibility = View.GONE
            binding.measure9.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient10.isEmpty()) {
            binding.ingredient10.visibility = View.GONE
            binding.measure10.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient11.isEmpty()) {
            binding.ingredient11.visibility = View.GONE
            binding.measure11.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient12.isEmpty()) {
            binding.ingredient12.visibility = View.GONE
            binding.measure12.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient13.isEmpty()) {
            binding.ingredient13.visibility = View.GONE
            binding.measure13.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient14.isEmpty()) {
            binding.ingredient14.visibility = View.GONE
            binding.measure14.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient15.isEmpty()) {
            binding.ingredient15.visibility = View.GONE
            binding.measure15.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient16.toString().isEmpty()) {
            binding.ingredient16.visibility = View.GONE
            binding.measure16.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient17.toString().isEmpty()) {
            binding.ingredient17.visibility = View.GONE
            binding.measure17.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient18.toString().isEmpty()) {
            binding.ingredient18.visibility = View.GONE
            binding.measure18.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient19.toString().isEmpty()) {
            binding.ingredient19.visibility = View.GONE
            binding.measure19.visibility = View.GONE
        }
        if (sharedVM.mealObject.strIngredient20.toString().isEmpty()) {
            binding.ingredient20.visibility = View.GONE
            binding.measure20.visibility = View.GONE
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonCancel.setOnClickListener{
                findNavController().navigate(R.id.action_mealDetails_to_meals)
            }
            buttonSave.setOnClickListener {
                val user = FirebaseAuth.getInstance().currentUser
                val uid = user?.uid
                val databaseRef = uid?.let { it1 ->
                    FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPES).child(
                        it1
                    )
                }
                databaseRef?.push()?.setValue(sharedVM.mealObject)
                toast("Meal saved to your favorites")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        sharedVM.updateBottomNavVisibility(true)
    }


}