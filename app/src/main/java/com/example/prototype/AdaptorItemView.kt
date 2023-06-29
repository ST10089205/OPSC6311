package com.example.prototype

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.prototype.databinding.RowListVwBinding

class AdaptorItemView : RecyclerView.Adapter<AdaptorItemView.HolderItemView>{

    private var context: Context

    private var listVwArrayList: ArrayList<ModelItemView>



    private lateinit var binding:RowListVwBinding

    constructor(context: Context, listVwArrayList: ArrayList<ModelItemView>) : super() {
        this.context = context
        this.listVwArrayList = listVwArrayList
    }

    inner class HolderItemView(itemView: View) : RecyclerView.ViewHolder(itemView){

        val listView = binding.listRv
        val progressBar = binding.progressBar
        val titleTv = binding.titleTv
        val descriptionTv = binding.descriptionTv
        val categoryTv = binding.categoryTv
        val sizeTv = binding.sizeTv
        val dateTv = binding.dateTv
        val moreBtn = binding.moreBtn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderItemView {
        binding = RowListVwBinding.inflate(LayoutInflater.from(context), parent, false)
        return HolderItemView(binding.root)
    }

    override fun getItemCount(): Int {
        return listVwArrayList.size
    }

    override fun onBindViewHolder(holder: HolderItemView, position: Int) {

        val model = listVwArrayList[position]
        val itemId = model.itemId
        val categoryId = model.id
        val title = model.itemName
        val itemDescription = model.itemDescription
        //val itemPhoto = model.itemPhoto
        val timestamp = model.timestamp
        val formattedDate = MyApplication.formatTimeStamp(timestamp)

        holder.titleTv.text = title
        holder.descriptionTv.text = itemDescription
        holder.dateTv.text = formattedDate

        MyApplication.loadCategory(categoryId, holder.categoryTv)
        //add this function on timestamp 46:50
        //add this function on timestamp 47:24
    }
}