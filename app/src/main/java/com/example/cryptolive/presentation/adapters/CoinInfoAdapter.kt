package com.example.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptolive.R
import com.example.cryptolive.databinding.ItemCoinInfoBinding
import com.example.cryptolive.domain.CoinInfo
import com.example.cryptolive.presentation.adapters.CoinInfoViewHolder
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) : RecyclerView.Adapter<CoinInfoViewHolder>() {

    var coinInfoList: List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinInfoViewHolder(binding)
    }

    override fun getItemCount() = coinInfoList.size

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
        val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
        with(holder.binding) {
                tvSymbols.text = String.format(symbolsTemplate, coin.fromSymbol, coin.toSymbol)
                tvPrice.text = coin.price
                tvLastUpdate.text = String.format(lastUpdateTemplate, coin.lastUpdate)
                Picasso.get().load(coin.imgUrl).into(ivLogoCoin)
                root.setOnClickListener {
                    onCoinClickListener?.onCoinClick(coin)
                }
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }
}