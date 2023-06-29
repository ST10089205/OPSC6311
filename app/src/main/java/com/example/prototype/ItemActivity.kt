package com.example.prototype

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ItemActivity : AppCompatActivity() {
    private lateinit var etItemName: EditText
    private lateinit var etDate: EditText
    private lateinit var etItemDescription: EditText
    private lateinit var btnSaveData: Button
    private lateinit var spinnerNames: Spinner
    private lateinit var namesListener: ValueEventListener


    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        etItemName = findViewById(R.id.etItemName)
        etDate = findViewById(R.id.etDate)
        etItemDescription = findViewById(R.id.etItemDescription)
        btnSaveData = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Item")

        btnSaveData.setOnClickListener {
            saveItemData()


        }

    }

    private fun saveItemData() {

        //getting values
        val itemName = etItemName.text.toString()
        val itemDate = etDate.text.toString()
        val itemDescription = etItemDescription.text.toString()


        if (itemName.isEmpty()) {
            etItemName.error = "Please enter item name"
        }
        if (itemDate.isEmpty()) {
            etDate.error = "Please enter date"
        }
        if (itemDescription.isEmpty()) {
            etItemDescription.error = "Please enter description"
        }

        val itemId = dbRef.push().key!!

        val item = ItemModel(itemId, itemName, itemDate, itemDescription)

        dbRef.child(itemId).setValue(item)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etItemName.text.clear()
                etDate.text.clear()
                etItemDescription.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
