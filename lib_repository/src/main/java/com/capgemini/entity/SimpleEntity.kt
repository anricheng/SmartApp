package com.capgemini.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "simple_data")
data class ItemEntity @JvmOverloads constructor(
    @ColumnInfo(name = "imageUrl") val imageUrl: String = "https://media.gettyimages.com/photos/the-smart-way-to-stay-stress-free-picture-id638999708?k=6&m=638999708&s=612x612&w=0&h=F4JKA_UoRpyfUfmE36_VM6O0froJu6yHjLFAamEKTkY=",
    @ColumnInfo(name = "content") val content: String = "This is just a test",
    @ColumnInfo(name = "isUsed") var isUsed: Boolean = false,
    @PrimaryKey @ColumnInfo(name = "guid") var id: String = UUID.randomUUID().toString()
) {
    val isEmpty: Boolean
        get() = imageUrl.isEmpty() && content.isEmpty()
}
