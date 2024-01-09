package com.dam23_24.ejemploroom.addtasks.domain

import com.dam23_24.ejemploroom.addtasks.data.TaskRepository
import com.dam23_24.ejemploroom.addtasks.ui.model.TaskModel
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.delete(taskModel)
    }
}