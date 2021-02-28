package com.capgemini.module_sample.bean

import androidx.room.Entity

@Entity
data class UserEntity(var userName:String, var picture:String, var projectName:String) {

}