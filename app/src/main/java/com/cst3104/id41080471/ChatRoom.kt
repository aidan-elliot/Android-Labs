package com.cst3104.id41080471

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst3104.id41080471.databinding.ActivityChatRoomBinding
import android.view.LayoutInflater
import android.view.View
import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MyAdapter(
    private val messageList: MutableList<MessageData>,
    private val context: Context
) : RecyclerView.Adapter<MyAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val thisRow = inflater.inflate(R.layout.activity_chat_room, parent, false)
        return MessageViewHolder(thisRow)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val thisRow = messageList[position]
        holder.timeView.text = thisRow.time
        holder.messageView.text = thisRow.message
    }

    override fun getItemCount(): Int = messageList.size

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeView: TextView = itemView.findViewById(R.id.time)
        val messageView: TextView = itemView.findViewById(R.id.message)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val listener = View.OnClickListener {
                    messageList.removeAt(position)
                    notifyItemRemoved(position)
                }
                Snackbar.make(
                    itemView,
                    context.getString(R.string.clickedmessage) + " " + (position + 1),
                    Snackbar.LENGTH_LONG
                ).setAction(context.getString(R.string.delete), listener).show()
            }
        }
    }
}
