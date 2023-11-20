package com.cst3104.id41080471

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import androidx.room.Room
import java.util.concurrent.Executors

class ChatRoom : AppCompatActivity() {
    private lateinit var db: MessageDatabase
    private val dbExecutor = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        db = Room.databaseBuilder(
            applicationContext,
            MessageDatabase::class.java, "message-database"
        ).build()
    }
    fun deleteMessage(messageData: MessageData) {
        dbExecutor.execute {
            db.chatMessageDao().deleteMessage(messageData)
        }
    }

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
            val messageData = messageList[position]

            if (messageData.isReceived) {
                // Show received message views
                holder.receivedMessageView.visibility = View.VISIBLE
                holder.receivedTimeView.visibility = View.VISIBLE
                holder.friendImageView.visibility = View.VISIBLE // Show friend's avatar

                // Hide sent message views
                holder.sentMessageView.visibility = View.GONE
                holder.sentTimeView.visibility = View.GONE
                holder.userImageView.visibility = View.GONE // Hide user's avatar

                // Bind data to received views
                holder.receivedMessageView.text = messageData.message
                holder.receivedTimeView.text = messageData.time
            } else {
                // Show sent message views
                holder.sentMessageView.visibility = View.VISIBLE
                holder.sentTimeView.visibility = View.VISIBLE
                holder.userImageView.visibility = View.VISIBLE // Show user's avatar

                // Hide received message views
                holder.receivedMessageView.visibility = View.GONE
                holder.receivedTimeView.visibility = View.GONE
                holder.friendImageView.visibility = View.GONE // Hide friend's avatar

                // Bind data to sent views
                holder.sentMessageView.text = messageData.message
                holder.sentTimeView.text = messageData.time
            }
        }


        override fun getItemCount(): Int = messageList.size

        inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val sentMessageView: TextView = itemView.findViewById(R.id.message)
            val sentTimeView: TextView = itemView.findViewById(R.id.time)
            val userImageView: ImageView = itemView.findViewById(R.id.userimage)

            val receivedMessageView: TextView = itemView.findViewById(R.id.messagereceived)
            val receivedTimeView: TextView = itemView.findViewById(R.id.timereceived)
            val friendImageView: ImageView = itemView.findViewById(R.id.friendimage)

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
}

