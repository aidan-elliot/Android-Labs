package com.cst3104.id41080471

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.cst3104.id41080471.databinding.ActivityChatRoomBinding

class MyViewHolder(val binding: ActivityChatRoomBinding) : RecyclerView.ViewHolder(binding.root) {

}

class MyAdapter(private val items: List<String>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding = ActivityChatRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.textView.text = items[position]
    }

    override fun getItemCount(): Int = items.size
}

class ChatRoom : AppCompatActivity() {
    private lateinit var binding: ActivityChatRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val myItems = listOf("Item 1", "Item 2", "Item 3") // replace with actual data


        binding.recycleView.adapter = MyAdapter(myItems)
    }
}




