package com.dam23_24.ejemploroom.addtasks.ui.model

//Nuestro modelo de datos...
data class TaskModel(
    val id: Long = System.currentTimeMillis(),
    val task: String,
    var selected: Boolean = false
)