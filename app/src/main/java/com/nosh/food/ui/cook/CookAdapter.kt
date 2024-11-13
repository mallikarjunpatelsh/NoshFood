package com.nosh.food.ui.cook

import AppConstants.AdapterConstants.Companion.COOK_FOOTER
import AppConstants.AdapterConstants.Companion.COOK_HEADER
import AppConstants.AdapterConstants.Companion.COOK_RECOMMENDATIONS
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nosh.food.databinding.ItemFooterBinding
import com.nosh.food.databinding.ItemHeaderBinding
import com.nosh.food.databinding.ItemHorizontalRecyclerViewBinding
import com.nosh.food.domain.model.DataItem

class CookAdapter(private val callback: IDishesCallback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: List<DataItem>? = null

    fun setList(list: List<DataItem>?) {
        this.list = null
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            COOK_HEADER -> {
                Cookheader(
                    ItemHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            COOK_FOOTER -> {
                CookFooterVH(
                    ItemFooterBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                RecyclerViewHorizontalVH(
                    ItemHorizontalRecyclerViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    callback
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            COOK_HEADER -> {

            }

            COOK_FOOTER -> {
                (holder as CookFooterVH).bind()
            }

            else -> {
                ((list?.get(position) as? DataItem.DishesUI<*>)?.items as? List<*>)?.let {
                    (holder as RecyclerViewHorizontalVH).bindUi(
                        it
                    )
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return (list?.get(position) as? DataItem.DishesUI<*>)?.itemType ?: COOK_RECOMMENDATIONS
    }
}