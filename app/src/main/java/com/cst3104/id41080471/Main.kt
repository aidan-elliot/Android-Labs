package com.cst3104.id41080471

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class Main : AppCompatActivity() {
    private lateinit var db: MessageDatabase

    companion object {
        const val DATE_FORMAT = "HH:mm"
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
                    val messageToDelete = messageList[position]

                    // Create AlertDialog.Builder
                    val builder = AlertDialog.Builder(context as Activity)
                    builder.setTitle(R.string.deleteMessage)
                    builder.setMessage(R.string.Areyousure)

                    // Positive Button ("Yes")
                    builder.setPositiveButton(R.string.Yes) { _, _ ->
                        // TODO: Add database delete operation here
                        messageList.removeAt(position)
                        notifyItemRemoved(position)
                        Snackbar.make(itemView, "Item ${position + 1} has been deleted", Snackbar.LENGTH_LONG)
                            .setAction(R.string.Undo) {
                                // Undo the deletion
                                messageList.add(position, messageToDelete)
                                notifyItemInserted(position)
                            }.show()
                    }

                    // Negative Button ("No")
                    builder.setNegativeButton(R.string.No, null) // No action on clicking "No"

                    // Show the AlertDialog
                    builder.create().show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(
            applicationContext,
            MessageDatabase::class.java, "message-database"
        ).build()

        val sendButton = findViewById<Button>(R.id.sendButton)
        val receiveButton = findViewById<Button>(R.id.receiveButton)
        val messageInput = findViewById<EditText>(R.id.enterMessage)
        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)

        val messages = mutableListOf<MessageData>()
        val adapter = MyAdapter(messages, this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        sendButton.setOnClickListener {
            addMessage(messageInput, false, messages, adapter)
        }

        receiveButton.setOnClickListener {
            addMessage(messageInput, true, messages, adapter)
        }
    }

    private fun addMessage(messageInput: EditText, isReceived: Boolean,
                           messages: MutableList<MessageData>, adapter: MyAdapter) {
        val messageText = messageInput.text.toString()
        if (messageText.isNotEmpty()) {
            val dateAndTime = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date())

            // Adding the message to the database and the RecyclerView
            val newMessage = MessageData(message = messageText, time = dateAndTime, isReceived = isReceived)
            GlobalScope.launch(Dispatchers.IO) {
                db.chatMessageDao().insertMessage(newMessage)
                withContext(Dispatchers.Main) {
                    messages.add(newMessage)
                    adapter.notifyItemInserted(messages.size - 1)
                }
            }
            messageInput.text.clear()
        }
    }
}


