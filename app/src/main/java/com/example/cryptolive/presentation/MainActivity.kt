package com.example.cryptolive.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptolive.R
import com.example.cryptolive.databinding.ActivityCoinPrceListBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), CoinListFragment.Orientation {

    private lateinit var binding: ActivityCoinPrceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPrceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar) //Установка toolBar как actionBar для старых версий
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.text){
                    "Избранное" -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.main_fragment_container, FavoritesFragment.newInstance())
                            .addToBackStack(null)
                            .commit()
                    }
                    "Главная" -> {
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