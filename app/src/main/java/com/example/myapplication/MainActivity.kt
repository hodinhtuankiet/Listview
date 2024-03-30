package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var vitri = -1
    private lateinit var editTextName: EditText
    private lateinit var lv: ListView
    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var arrayCourse: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextText)
        lv = findViewById(R.id.lv)
        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        arrayCourse = ArrayList()
        arrayCourse.add("Android")
        arrayCourse.add("PHP")
        arrayCourse.add("IOS")
        arrayCourse.add("Android")
        arrayCourse.add("Android")

        adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, arrayCourse)
        lv.adapter = adapter

        lv.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            editTextName.setText(arrayCourse[position])
            vitri = position
        }

        lv.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->
            Toast.makeText(this@MainActivity, "Long click: " + arrayCourse[position], Toast.LENGTH_SHORT).show()
            true
        }

        button.setOnClickListener {
            val itemName = editTextName.text.toString()
            arrayCourse.add(itemName)
            adapter.notifyDataSetChanged()
        }

        button2.setOnClickListener {
            if (vitri != -1) {
                arrayCourse[vitri] = editTextName.text.toString()
                adapter.notifyDataSetChanged()
            }
        }

        button3.setOnClickListener {
            if (vitri != -1 && vitri < arrayCourse.size) {
                arrayCourse.removeAt(vitri)
                adapter.notifyDataSetChanged()
                vitri = -1 // Reset vitri after deletion
                editTextName.setText("") // Clear EditText after deletion
            } else {
                Toast.makeText(this@MainActivity, "Select an item to delete", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
