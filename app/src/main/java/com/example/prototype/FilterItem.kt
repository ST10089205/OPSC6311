package com.example.prototype


import android.widget.Filter

class FilterItem : Filter {

    var filterList: ArrayList<ModelItemView>

    var adaptorItemView: AdaptorItemView

    constructor(filterList: ArrayList<ModelItemView>, adaptorItemView: AdaptorItemView) : super() {
        this.filterList = filterList
        this.adaptorItemView = adaptorItemView
    }


    override fun performFiltering(constraint: CharSequence?): FilterResults {
      var constraint:CharSequence? = constraint
      val results = FilterResults()

      if (constraint != null && constraint.isNotEmpty()){

          constraint = constraint.toString().lowercase()
          var filteredModels = ArrayList<ModelItemView>()
          for(i in filterList.indices){
              if (filterList[i].itemName.lowercase().contains(constraint)){
                  filteredModels.add(filterList[i])
              }
          }
          results.count = filteredModels.size
          results.values = filteredModels
      }
        else{

            results.count = filterList.size
            results.values = filterList
      }
        return results
    }

    override fun publishResults(constraint: CharSequence, results: FilterResults) {
       adaptorItemView.listVwArrayList = results.values as ArrayList<ModelItemView>

        adaptorItemView.notifyDataSetChanged()
    }
}