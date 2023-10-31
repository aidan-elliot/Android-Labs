package com.cst3104.id41080471

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var adapter: ChatAdapter
    private val messages = mutableListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_linear)

        editText = findViewById(R.id.editTextText)
        val listView: ListView = findViewById(R.id.listView)
        adapter = ChatAdapter(this, messages)
        listView.adapter = adapter

        findViewById<View>(R.id.sendButton).setOnClickListener {
            addMessage(editText.text.toString(), true)
        }

        findViewById<View>(R.id.receiveButton).setOnClickListener {
            addMessage(editText.text.toString(), false)
        }

        listView.setOnItemLongClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.deleteMessage))
                .setMessage(getString(R.string.rowMessage) + " $position")
                .setPositiveButton(getString(R.string.deleteButton)) { _, _ ->
                    messages.removeAt(position)
                    adapter.notifyDataSetChanged()
                }
                .setNegativeButton("Go back", null)
                .show()
            true
        }
    }

    private fun addMessage(text: String, isSent: Boolean) {
        if (text.isNotBlank()) {
            messages.add(Message(text, isSent))
            editText.text.clear()
            adapter.notifyDataSetChanged()
        }
    }
}
