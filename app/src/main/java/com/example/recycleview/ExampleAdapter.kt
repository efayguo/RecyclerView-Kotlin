package com.example.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*

class ExampleAdapter(
    private val exampleList: List<ExampleItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

   inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        //where you cache the view and use it in the onbind later
        val imageView: ImageView = itemView.itemImageView
        val textView1: TextView = itemView.textView1
        val textView2: TextView = itemView.textView2

        //create onclick listener on the specific item
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }

        }

    }


    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {

        //row layout -- template, where you can copy it everytime
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.example_item,
            parent, false
        )

        return ExampleViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return exampleList.size
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        //where to show the data on the view
        val currentItem = exampleList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2

    }


}