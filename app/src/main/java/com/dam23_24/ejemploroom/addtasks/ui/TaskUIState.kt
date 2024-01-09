package com.dam23_24.ejemploroom.addtasks.ui

import com.dam23_24.ejemploroom.addtasks.ui.model.TaskModel

/**
 * Estados de la pantalla
 */
sealed interface TaskUIState {
    //Si no recibes datos... usamos object
    object Loading: TaskUIState
    data class Error(val throwable: Throwable): TaskUIState
    data class Success(val tasks:List<TaskModel>) : TaskUIState
}