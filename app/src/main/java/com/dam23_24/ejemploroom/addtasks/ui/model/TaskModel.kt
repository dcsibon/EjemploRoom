package com.dam23_24.ejemploroom.addtasks.ui.model

//Nuestro modelo de datos...
data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val task: String,
    var selected: Boolean = false
)