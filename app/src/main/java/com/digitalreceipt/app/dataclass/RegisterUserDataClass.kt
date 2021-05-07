package com.digitalreceipt.app.dataclass

data class RegisterUserDataClass(
    val userId: Int,
    val userName:String,
    val userEmail:String,
    val userPassword:String
){
    constructor():this(-1,"","","")
}
