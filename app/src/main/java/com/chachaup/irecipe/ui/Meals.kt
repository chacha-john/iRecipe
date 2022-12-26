package com.chachaup.irecipe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chachaup.irecipe.R
import com.chachaup.irecipe.adapter.MealListAdapter
import com.chachaup.irecipe.databinding.FragmentMealsBinding

class Meals : Fragment() {

    private lateinit var binding: FragmentMealsBinding

    private val adapter by lazy { MealListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meals, container, false)
        val rv = binding.recyclerViewRecipes
        rv.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

        }
    }

}