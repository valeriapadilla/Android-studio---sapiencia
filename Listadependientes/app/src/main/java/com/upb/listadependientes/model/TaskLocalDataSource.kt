package com.upb.listadependientes.model


import com.upb.listadependientes.domain.Tarea

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.Flow

//creacion del crud (crear, leer, actualizar, borrar)

class TaskLocalDataSource {
    //crear una lista privada de tareas - de modificacion internamente
    private val _tareasFlow = MutableStateFlow<List<Tarea>>(emptyList())

    //crear una lista publica de tareas - de lectura a nivel externo
    val tareasFlow: Flow<List<Tarea>>
        get() = _tareasFlow

    //los metodos del crud

    //crear - guardar una nueva tarea
    fun guardarTarea(tarea: Tarea){
        //logica para guardar una nueva tarea
        val tareas = _tareasFlow.value.toMutableList()
        tareas.add(tarea)

        _tareasFlow.value = tareas
    }

    //leer - obtener las tareas actuales
    fun obtenerTareas(): List<Tarea>{
        return _tareasFlow.value
    }

    fun obtenerTareaPorId(id: String): Tarea? {
       return _tareasFlow.value.firstOrNull{it.id == id}
    }

    //actualizar una tarea
    fun actualizarTarea(tarea: Tarea) {
        val tareas = _tareasFlow.value.toMutableList()
        val index = tareas.indexOfFirst{ it.id == tarea.id}

        if(index != -1){
            tareas[index] = tarea
            _tareasFlow.value = tareas
        }
    }


    //borrar una tarea
    fun borrarTarea(tarea: Tarea){
        val tareas = _tareasFlow.value.toMutableList()

        tareas.remove(tarea)

        _tareasFlow.value = tareas
    }

    //borrar todas las tareas
    fun borrarTareas(){
        _tareasFlow.value = emptyList()
    }

}