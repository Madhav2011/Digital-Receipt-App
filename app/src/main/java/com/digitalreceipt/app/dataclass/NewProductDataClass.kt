package com.digitalreceipt.app.dataclass

data class NewProductDataClass(
    val id: Int,
    val image: String,
    val name: String,
    val dateOfPurchase: String,
    val billNumber: String,
    val warrentyCard: String,
    val expiryDate: String,
    val category: String,
    val nameShop: String,
    val contact: String,
    val address: String
) {
    constructor() : this(-1, "","", "", "", "", "", "", "", "", "")
}
