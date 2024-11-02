package com.bugbender.customviewxml.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bugbender.customviewxml.R
import com.bugbender.customviewxml.databinding.ViewholderMainItemBinding

class MainAdapter(
    private val items: List<MainItem>,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<MainAdapter.MainItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainItemViewHolder(
        ViewholderMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        fragmentManager
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class MainItemViewHolder(
        private val binding: ViewholderMainItemBinding,
        private val fragmentManager: FragmentManager
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MainItem) = with(binding.root) {
            text = item.name
            setOnClickListener {
                fragmentManager.beginTransaction()
                    .add(
                        R.id.mainContainer,
                        item.fragmentClass.getDeclaredConstructor().newInstance(),
                    )
                    .addToBackStack(item.fragmentClass.simpleName)
                    .commit()
            }
        }
    }
}