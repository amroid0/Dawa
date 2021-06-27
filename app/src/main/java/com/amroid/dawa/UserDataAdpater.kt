package com.amroid.dawa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amroid.dawa.databinding.ListItemRowBinding
import com.amroid.dawa.model.Data


class UserDataAdpater(val list: MutableList<Data>): RecyclerView.Adapter<UserDataAdpater.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list[position]
        holder.binding.heartLevel.text=item.heartLevel.toString()
        holder.binding.textView.text=item.analysis1.toString()
        holder.binding.textView2.text=item.analysis2.toString()
        holder.binding.textView3.text=item.analysis3.toString()
        holder.binding.textView4.text=item.analysis4.toString()

    }

    class Holder(val binding: ListItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}