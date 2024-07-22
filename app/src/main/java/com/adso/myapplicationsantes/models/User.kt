package com.adso.myapplicationsantes.models

data class User(
    val name:String,
    val lastname:String,
    val birthdate:String,
    val location:String,
    val gender:String,
    val documenttype:String,
    val document_number:String,
    val phone:String,
    val email:String,
    val password:String
)
