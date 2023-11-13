package com.cst3104.id41080471
import android.widget.TextView
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

    override fun onBindViewHolder(holder: MyRowHolder, position: Int) {
        val obj = messages[position]
        holder.messageText.text = obj
    }


    override fun getItemCount(): Int = items.size
}

class MyRowHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val messageText: TextView = itemView.findViewById(R.id.message)
    val timeText: TextView = itemView.findViewById(R.id.time)
    fun MyRowHolder(itemView: View) {
        super(itemView)
    }
}
class ChatRoom : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val myItems = listOf("Item 1", "Item 2", "Item 3")


        binding.recycleView.adapter = MyAdapter(myItems)
    }
}
