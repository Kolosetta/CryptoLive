package com.example.cryptolive.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cryptolive.R
import com.example.cryptolive.databinding.ActivityCoinPrceListBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), CoinListFragment.Orientation {

    private lateinit var binding: ActivityCoinPrceListBinding
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPrceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar) //Установка toolBar как actionBar для старых версий
        supportActionBar?.setDisplayShowTitleEnabled(false)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        if(savedInstanceState == null){
            viewModel.loadDataInBackground()
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.text){
                    "Избранное" -> {
                        supportFragmentManager.popBackStack()
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.main_fragment_container, FavoritesFragment.newInstance())
                            .addToBackStack(null)
                            .commit()
                    }
                    "Главная" -> {
                        binding.guideline?.setGuidelinePercent(1f)
                        supportFragmentManager.popBackStack()
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.main_fragment_container, CoinListFragment.newInstance())
                            .addToBackStack(null)
                            .commit()
                    }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.swipeLayout.setOnRefreshListener {
            lifecycleScope.launch {
                viewModel.loadDataManually()
            }
            binding.swipeLayout.isRefreshing = false
        }

    }

    override fun isVerticalOrientation(): Boolean {
        return binding.secondFragmentContainer == null
    }

    override fun moveGuideLine() {
        binding.guideline?.setGuidelinePercent(0.5f)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.guideline?.setGuidelinePercent(1f)
    }

}