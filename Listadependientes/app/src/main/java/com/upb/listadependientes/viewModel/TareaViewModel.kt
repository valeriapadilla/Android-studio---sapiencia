package com.upb.listadependientes.viewModel

import androidx.lifecycle.ViewModel
import com.upb.listadependientes.domain.Tarea
import com.upb.listadependientes.model.TaskLocalDataSource

class TareaViewModel(val fuenteDatos: TaskLocalDataSource = TaskLocalDataSource()) : ViewModel() {

    val tareas = fuenteDatos.tareasFlow

    fun agregarTarea(tarea: Tarea){
        fuenteDatos.guardarTarea(tarea)
    }

    fun eliminarTarea(id: String){
        //buscar cual es la tarea con ese id
        val tareaId = fuenteDatos.obtenerTareaPorId(id)
        tareaId?.let { fuenteDatos.borrarTarea(tareaId) }
    }

    fun completarTarea(tarea: Tarea){
        fuenteDatos.actualizarTarea(
            tarea.copy(
                estado = !tarea.estado
            )
        )

    }
}