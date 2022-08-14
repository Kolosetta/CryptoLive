package com.example.cryptolive.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.cryptolive.R
import com.example.cryptolive.presentation.adapters.CoinInfoAdapter
import com.example.cryptolive.databinding.ActivityCoinPrceListBinding
import com.example.cryptolive.domain.CoinInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var binding: ActivityCoinPrceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPrceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar) //Установка toolBar как actionBar для старых версий
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinItem: CoinInfo) {
                if(isVerticalOrientation()){
                    launchDetailActivity(coinItem.fromSymbol)
                }
                else{
                    launchDetailFragment(coinItem.fromSymbol)
                }
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun launchDetailFragment(fromSymbol: String){
        binding.guideline?.setGuidelinePercent(0.5f)
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }

    private fun launchDetailActivity(fromSymbol: String){
        val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity, fromSymbol)
        startActivity(intent)
    }

    private fun isVerticalOrientation(): Boolean{
        return binding.fragmentContainer == null
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.guideline?.setGuidelinePercent(1f)

    }
}