package com.cst3104.id41080471
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ChatAdapter(private val context: Context, private val messages: MutableList<Message>) : BaseAdapter() {

    override fun getCount(): Int = messages.size

    override fun getItem(position: Int): Any = messages[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false)
        val sentMessageTextView: TextView = view.findViewById(R.id.sentMessage)
        val receivedMessageTextView: TextView = view.findViewById(R.id.receivedMessage)

        val message = getItem(position) as Message

        if (message.isSent) {
            sentMessageTextView.text = message.text
            sentMessageTextView.visibility = View.VISIBLE
            receivedMessageTextView.visibility = View.INVISIBLE
        } else {
            receivedMessageTextView.text = message.text
            receivedMessageTextView.visibility = View.VISIBLE
            sentMessageTextView.visibility = View.INVISIBLE
        }
        return view
    }
}

