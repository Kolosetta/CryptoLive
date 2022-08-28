package com.example.cryptolive.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptolive.R
import com.example.cryptolive.databinding.FragmentCoinListBinding
import com.example.cryptolive.domain.CoinInfo
import com.example.cryptolive.presentation.adapters.CoinInfoAdapter
import java.lang.RuntimeException
import javax.inject.Inject


class CoinListFragment : Fragment() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var binding: FragmentCoinListBinding
    private lateinit var orientation: Orientation

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (activity?.application as CoinApp).component
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
        if(context is Orientation){
            orientation = context
        }
        else{
            throw RuntimeException("Родительская активити фрагмента ${this.javaClass} обязана реализовавать IsVerticalOrientation")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CoinInfoAdapter(requireContext())
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinItem: CoinInfo) {
                if(orientation.isVerticalOrientation()){
                    launchDetailFragmentMainContainer(coinItem.fromSymbol)
                }
                else{
                    launchDetailFragmentSecondContainer(coinItem.fromSymbol)
                }
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        viewModel = ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    private fun launchDetailFragmentMainContainer(fromSymbol: String){
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }

    private fun launchDetailFragmentSecondContainer(fromSymbol: String){
        orientation.moveGuideLine()
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.second_fragment_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }

    companion object{
        fun newInstance() = CoinListFragment()
    }

    interface Orientation{
        fun isVerticalOrientation(): Boolean
        fun moveGuideLine()
    }
}