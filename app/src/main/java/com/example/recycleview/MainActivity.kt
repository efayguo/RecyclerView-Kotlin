package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ExampleAdapter.OnItemClickListener {


    //make the var to change in the insert and delete function
    private val exampleList = generateDummyList(500)
    private val adapter = ExampleAdapter(exampleList,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //assign everything into the Example
        recyclerView.adapter = adapter
        //vertical
        recyclerView.layoutManager = LinearLayoutManager(this)

        //horizontal
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerView.setHasFixedSize(true)
    }

    //to create a list of example

    private fun generateDummyList(size: Int) : ArrayList<ExampleItem>{
        val list = ArrayList<ExampleItem>()

        for (i in 0 until size) {
            val drawable =  when (i % 5) {
                0 -> R.drawable.android
                1 -> R.drawable.time
                2 -> R.drawable.bed
                3 -> R.drawable.car
                else -> R.drawable.plane
            }

            val item = ExampleItem(drawable,"Item $i","Line $i")
            list += item
        }
        return  list
    }


    //insert with animation at random index to 8
    fun insertItem(view: View) {
        val index = Random.nextInt(8)
        val newItem = ExampleItem(
            R.drawable.avatar,
            "Friends number $index",
            "Beautiful"
        )
        exampleList.add(index,newItem)
        adapter.notifyItemInserted(index)
    }


    //delete with animation at random index to 8
    fun removeItem(view: View) {
        val index = Random.nextInt(8)
        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem: ExampleItem = exampleList[position]
        clickedItem.text1 = "beautiful"
        clickedItem.imageResource = R.drawable.avatar
        adapter.notifyItemChanged(position)

        //or you can pass the data to a different page
    }

}