package com.ayan.wiseapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.ayan.wiseapp.R
import com.ayan.wiseapp.databinding.FragmentDrinkDetailsBinding
import com.ayan.wiseapp.interfaces.FragmentVisibilityListener
import com.ayan.wiseapp.models.Drink
import com.bumptech.glide.Glide

private const val ARG_PARAM1 = "param1"

class DrinkDetailsFragment : Fragment() {
    private lateinit var binding: FragmentDrinkDetailsBinding
    private var drink: Drink? = null
    private var visibilityListener: FragmentVisibilityListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { drink = it.getParcelable(ARG_PARAM1) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDrinkDetailsBinding.inflate(layoutInflater)
        binding.apply {
            Glide.with(binding.root.context)
                .load(drink?.strDrinkThumb)
                .into(ivDrink)
            tvId.text = getString(R.string.id_value, drink?.idDrink)
            tvDrinkName.text = drink?.strDrink
            if(drink?.dateModified != null) {
                textView4.isVisible = true
                lastModifiedDate.isVisible = true
                lastModifiedDate.text = drink?.dateModified
            }
            tvInstructions.text = drink?.strInstructions
            tvDescriptions.text  = getDescriptions(drink)
            tvIngredients.text  = getIngredients(drink)
            tvMeasures.text = getMeasures(drink)
        }
        return binding.root
    }

    private fun getMeasures(drink: Drink?): CharSequence? {
        var measures = ""
        if(drink?.strMeasure1 != null) measures += "${drink.strMeasure1}\n"
        if(drink?.strMeasure2!= null) measures += "${drink.strMeasure2}\n"
        if(drink?.strMeasure3!= null) measures += "${drink.strMeasure3}\n"
        if(drink?.strMeasure4!= null) measures += "${drink.strMeasure4}\n"
        if(drink?.strMeasure5!= null) measures += "${drink.strMeasure5}\n"
        if(drink?.strMeasure6!= null) measures += "${drink.strMeasure6}\n"
        if(drink?.strMeasure7!= null) measures += "${drink.strMeasure7}\n"
        if(drink?.strMeasure8!= null) measures += "${drink.strMeasure8}\n"
        if(drink?.strMeasure9!= null) measures += "${drink.strMeasure9}\n"
        if(drink?.strMeasure10!= null) measures += "${drink.strMeasure10}\n"
        if(drink?.strMeasure11!= null) measures += "${drink.strMeasure11}\n"
        if(drink?.strMeasure12!= null) measures += "${drink.strMeasure12}\n"
        if(drink?.strMeasure13!= null) measures += "${drink.strMeasure13}\n"
        if(drink?.strMeasure14!= null) measures += "${drink.strMeasure14}\n"
        if(drink?.strMeasure15!= null) measures += "${drink.strMeasure15}\n"
        return measures
    }

    private fun getIngredients(drink: Drink?): CharSequence? {
        var ingredients = ""
        if(drink?.strIngredient1!= null) ingredients += "${drink.strIngredient1}\n"
        if(drink?.strIngredient2!= null) ingredients += "${drink.strIngredient2}\n"
        if(drink?.strIngredient3!= null) ingredients += "${drink.strIngredient3}\n"
        if(drink?.strIngredient4!= null) ingredients += "${drink.strIngredient4}\n"
        if(drink?.strIngredient5!= null) ingredients += "${drink.strIngredient5}\n"
        if(drink?.strIngredient6!= null) ingredients += "${drink.strIngredient6}\n"
        if(drink?.strIngredient7!= null) ingredients += "${drink.strIngredient7}\n"
        if(drink?.strIngredient8!= null) ingredients += "${drink.strIngredient8}\n"
        if(drink?.strIngredient9!= null) ingredients += "${drink.strIngredient9}\n"
        if(drink?.strIngredient10!= null) ingredients += "${drink.strIngredient10}\n"
        if(drink?.strIngredient11!= null) ingredients += "${drink.strIngredient11}\n"
        if(drink?.strIngredient12!= null) ingredients += "${drink.strIngredient12}\n"
        if(drink?.strIngredient13!= null) ingredients += "${drink.strIngredient13}\n"
        if(drink?.strIngredient14!= null) ingredients += "${drink.strIngredient14}\n"
        if(drink?.strIngredient15!= null) ingredients += "${drink.strIngredient15}\n"
        return ingredients
    }

    private fun getDescriptions(drink: Drink?): CharSequence? {
        return "${drink?.strCategory}, ${drink?.strAlcoholic}, ${drink?.strGlass}"
    }

    override fun onAttach(context: Context) {
        visibilityListener = (context as FragmentVisibilityListener)
        visibilityListener?.fragmentVisible()
        super.onAttach(context)
    }

    override fun onDetach() {
        visibilityListener?.fragmentClosed()
        super.onDetach()
    }

    companion object {
        @JvmStatic
        fun newInstance(data: Drink) =
            DrinkDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, data)
                }
            }
    }
}