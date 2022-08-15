package com.example.cryptolive.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.cryptolive.R
import com.example.cryptolive.presentation.adapters.CoinInfoAdapter
import com.example.cryptolive.databinding.ActivityCoinPrceListBinding
import com.example.cryptolive.domain.CoinInfo

class MainActivity : AppCompatActivity(), CoinListFragment.Orientation {

    private lateinit var binding: ActivityCoinPrceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPrceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar) //Установка toolBar как actionBar для старых версий
        supportActionBar?.setDisplayShowTitleEnabled(false)

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