package com.dam23_24.ejemploroom.addtasks.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dam23_24.ejemploroom.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
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

    fun onTaskCreated() {
        onDialogClose()
        //Log.i("dam2", _myTaskText.value ?: "")
        _tasks.add(TaskModel(task = _myTaskText.value ?: ""))
        _myTaskText.value = ""
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onTaskTextChanged(taskText: String) {
        _myTaskText.value = taskText
    }

    fun onItemRemove(taskModel: TaskModel) {
        //No podemos usar directamente _tasks.remove(taskModel) porque no es posible por el uso de let con copy para modificar el checkbox...
        //Para hacerlo correctamente, debemos previamente buscar la tarea en la lista por el id y después eliminarla
        val task = _tasks.find { it.id == taskModel.id }
        _tasks.remove(task)
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        val index = _tasks.indexOf(taskModel)

        //Si se modifica directamente _tasks no se recompone en el LazyColumn
        //Para solucionarlo y que se recomponga sin problemas en la vista, lo hacemos con un let...
        _tasks[index] = _tasks[index].let { it.copy(selected = !it.selected) }
    }

}