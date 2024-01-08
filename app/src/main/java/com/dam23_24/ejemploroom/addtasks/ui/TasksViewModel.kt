package com.dam23_24.ejemploroom.addtasks.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dam23_24.ejemploroom.addtasks.ui.model.TaskModel
import javax.inject.Inject

class TasksViewModel @Inject constructor(): ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _myTaskText = MutableLiveData<String>()
    val myTaskText: LiveData<String> = _myTaskText

    //Utilizamos mutableStateListOf porque se lleva mejor con LazyColumn a la hora
    //de refrescar la información en la vista...
    private val _tasks = mutableStateListOf<TaskModel>()
    val tasks: List<TaskModel> = _tasks

    fun onDialogClose() {
        _showDialog.value = false
    }

    /*fun onTaskCreated(task: String) {
        onDialogClose()
        //Log.i("dam2", task)
        _tasks.add(TaskModel(task = task))
    }*/

    fun onTaskCreated() {
        onDialogClose()
        _tasks.add(TaskModel(task = _myTaskText.value ?: ""))
        _myTaskText.value = ""
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onItemRemove(taskModel: TaskModel) {
        //Así no es posible por el uso de let con copy para modificar el checkbox...
        //_tasks.remove(taskModel)

        //Debemos buscar en la lista por el id
        val task = _tasks.find { it.id == taskModel.id }
        _tasks.remove(task)
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        val index = _tasks.indexOf(taskModel)

        //Lo hacemos así para que se recomponga sin problemas en la vista...
        _tasks[index] = _tasks[index].let { it.copy(selected = !it.selected) }
    }

    fun onTaskTextChanged(taskText: String) {
        _myTaskText.value = taskText
    }

}