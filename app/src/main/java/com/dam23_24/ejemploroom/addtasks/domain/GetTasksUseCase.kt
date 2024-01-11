package com.dam23_24.ejemploroom.addtasks.domain

import com.dam23_24.ejemploroom.addtasks.data.TaskRepository
import com.dam23_24.ejemploroom.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Caso de uso para recuperar las tareas
 *
 * Para acceder al data vamos a necesitar el repositorio, ya que es nuestra puerta de entrada al data.
 * Gracias a Dagger Hilt lo vamos a inyectar en el constructor.
 */
class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.tasks
}