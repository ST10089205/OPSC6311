package com.example.prototype

class ModelItemView {
    var uid:String = " "
    var itemId: String = " "
    var itemName: String = " "
    var itemDescription: String = " "
    var id: String = " "
    var category: String = " "
    var goal: String = " "
    var timestamp:Long = 0

    constructor()
    constructor(
        uid: String,
        itemId: String,
        itemName: String,
        itemDescription: String,
        id: String,
        category: String,
        goal: String,
        timestamp: Long
    ) {
        this.uid = uid
        this.itemId = itemId
        this.itemName = itemName
        this.itemDescription = itemDescription
        this.id = id
        this.category = category
        this.goal = goal
        this.timestamp = timestamp
    }


}