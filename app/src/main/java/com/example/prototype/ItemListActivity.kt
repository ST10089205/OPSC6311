package com.example.prototype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.prototype.databinding.ActivityItemListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ItemListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemListBinding

    private companion object{
        const val TAG = "List_View_TAG"
    }
    private var categoryId = ""
    private var category = ""
    private lateinit var itemArrayList: ArrayList<ModelItemView>

    private lateinit var adaptorItemView: AdaptorItemView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        categoryId = intent.getStringExtra("categoryId")!!
        category = intent.getStringExtra("category")!!

        binding.subTitleTv.text = category

        loadListItem()

        binding.searchEdt.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
               try{
                   adaptorItemView.filter!!.filter(s)
               }
               catch (e:Exception){
                   Log.d(TAG, "onTextChanged: ${e.message}")
               }
            }
            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }
    private fun loadListItem(){
         itemArrayList = ArrayList()

        val ref = FirebaseDatabase.getInstance().getReference("Item")
        ref.orderByChild("Id").equalTo(categoryId)
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                  itemArrayList.clear()
                    for (ds in snapshot.children) {

                        val model = ds.getValue(ModelItemView::class.java)
                        if (model != null) {
                            itemArrayList.add(model)
                            Log.d(TAG, "onDataChange:${model.itemName} ${model.id} ")
                        }
                    }
                    adaptorItemView = AdaptorItemView(this@ItemListActivity, itemArrayList)
                    binding.itemsRv.adapter = adaptorItemView

                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }
}