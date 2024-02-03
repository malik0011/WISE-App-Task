package com.ayan.wiseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayan.wiseapp.adapters.DrinksAdapter
import com.ayan.wiseapp.databinding.ActivityMainBinding
import com.ayan.wiseapp.fragments.DrinkDetailsFragment
import com.ayan.wiseapp.interfaces.FragmentVisibilityListener
import com.ayan.wiseapp.interfaces.ListItemClickListener
import com.ayan.wiseapp.models.Drink
import com.ayan.wiseapp.viewmodels.DrinkViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ListItemClickListener, FragmentVisibilityListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DrinkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[DrinkViewModel::class.java]
        setContentView(binding.root)
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.fetchData("mojito") //with default value
        }
        setUpObserver()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.etInput.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                // Handle the search action here
                GlobalScope.launch(Dispatchers.IO) {
                    viewModel.fetchData(binding.etInput.text.toString())
                }
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun setUpObserver() {
        viewModel.drinksLiveData.observe(this) {
            binding.apply {
                pBar.isVisible = true
                rcvDrinks.layoutManager = LinearLayoutManager(applicationContext)
                rcvDrinks.adapter = DrinksAdapter(it, this@MainActivity)
                pBar.isVisible = false
            }
        }
        viewModel.toastLiveData.observe(this) {
            binding.pBar.isVisible = false
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClicked(data: Drink) {
        //opening frag with given data
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DrinkDetailsFragment.newInstance(data))
            .addToBackStack(null)
            .commit()
    }

    override fun fragmentVisible() {
        binding.fragmentContainer.isVisible = true
    }

    override fun fragmentClosed() {
        binding.fragmentContainer.isVisible = false
    }
}