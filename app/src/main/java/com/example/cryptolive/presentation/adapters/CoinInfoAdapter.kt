package com.example.cryptolive.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptolive.R
import com.example.cryptolive.databinding.ItemCoinInfoBinding
import com.example.cryptolive.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) : ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback()) {

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
        val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
        val change24hoursTemplate = context.resources.getString(R.string.change_24_hours)
        with(holder.binding) {
                tvSymbols.text = String.format(symbolsTemplate, coin.fromSymbol, coin.toSymbol)
                tvPrice.text = coin.price
                tvLastUpdate.text = String.format(lastUpdateTemplate, coin.lastUpdate)
                valueChange.text = String.format(change24hoursTemplate, coin.change24HourPercent)
                Picasso.get().load(coin.imgUrl).into(ivLogoCoin)
                root.setOnClickListener {
                    onCoinClickListener?.onCoinClick(coin)
                }
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinItem: CoinInfo)
    }
}