package com.nosh.food.ui.cook

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nosh.food.R
import com.nosh.food.data.base.Resource
import com.nosh.food.databinding.FragmentCookBinding
import com.nosh.food.ui.CookScheduleBottomSheet
import com.nosh.food.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 * Use the [CookFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CookFragment : BaseFragment(), IDishesCallback {
    private lateinit var binding: FragmentCookBinding
    private val viewmodel: CookViewmodel by viewModels()
    private val adapter by lazy { CookAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        if (!::binding.isInitialized)
            binding = /*FragmentCookBinding.inflate(inflater, container, false)*/ DataBindingUtil.inflate(
                inflater, R.layout.fragment_cook, container, false
            )
        return binding.root
    }

    override fun getBundleParameter() {

    }

    override fun setObservers() {
        lifecycleScope.launch {
            viewmodel.dishes.collectLatest { result ->
                withContext(Dispatchers.Main) {
                    setLoadingState(result.state,result.status, result.message)
                    when (result.status) {
                        Resource.Status.SUCCESS -> {
                            Log.d("success in api", "$result")
                            adapter.setList(result.data?.cookItems)
                        }
                        Resource.Status.ERROR -> {
                            showToast(result.message)
                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }

    override fun initViews() {
        binding.rvContent?.apply {
            this.adapter = this@CookFragment.adapter
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * @return A new instance of fragment CookFragment.
         */
        @JvmStatic
        fun newInstance() =
            CookFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onCLick() {
        val modal = CookScheduleBottomSheet()
        childFragmentManager.let { modal.show(it, CookScheduleBottomSheet.TAG) }
    }
}

interface IDishesCallback {
    fun onCLick()
}