package com.example.cryptolive.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.cryptolive.R
import com.example.cryptolive.data.network.ApiFactory.BASE_IMAGE_URL
import com.example.cryptolive.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinViewModel
    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol).observe(this) {
            binding.tvPrice.text = it.price
            binding.tvMinPrice.text = it.dayLowPrice
            binding.tvMaxPrice.text = it.dayHighPrice
            binding.tvLastMarket.text = it.lastMarket
            binding.tvLastUpdate.text = it.lastUpdate
            binding.tvFromSymbol.text = it.fromSymbol
            binding.tvToSymbol.text = it.toSymbol
            Picasso.get().load(it.imgUrl).into(ivLogoCoin)
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}