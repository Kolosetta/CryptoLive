package com.example.cryptolive.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.cryptolive.databinding.FragmentCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailFragment : Fragment() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var binding: FragmentCoinDetailBinding
    private lateinit var fromSymbol: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fromSymbol = requireArguments().getString(FROM_SYMBOL_PARAM, EMPTY_SYMBOL)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol).observe(viewLifecycleOwner){
            binding.tvPrice.text = it.price
            binding.tvMinPrice.text = it.dayLowPrice
            binding.tvMaxPrice.text = it.dayHighPrice
            binding.tvLastMarket.text = it.lastMarket
            binding.tvLastUpdate.text = it.lastUpdate
            binding.tvFromSymbol.text = it.fromSymbol
            binding.tvToSymbol.text = it.toSymbol
            Picasso.get().load(it.imgUrl).into(binding.ivLogoCoin)
        }
    }

    companion object {
        private const val FROM_SYMBOL_PARAM = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newInstance(fromSymbol: String) =
            CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(FROM_SYMBOL_PARAM, fromSymbol)
                }
            }
    }
}
