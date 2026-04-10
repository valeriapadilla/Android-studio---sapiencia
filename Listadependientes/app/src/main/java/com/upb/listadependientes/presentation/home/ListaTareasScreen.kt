package com.upb.listadependientes.presentation.home

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Icon
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.upb.listadependientes.viewModel.TareaViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ListaTareasScreen(
    modifier: Modifier = Modifier,
    puente: TareaViewModel,
    onNavigateToAdd: () -> Unit
) {
    //variables
    val tareas by puente.tareas.collectAsState(initial = emptyList())
    val fechaActual = remember {
        val formato = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
        formato.format(Date())
    }

    val tareasCompletas = tareas.count{it.estado==true}
    val tareasPendientes = tareas.count{it.estado==false}


    Scaffold(
        //agregar el boton flotante
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAdd
            ) {
                Icon(Icons.Default.Add, contentDescription="Agregar")
            }
        }
    ) { padding ->

        LazyColumn(
            contentPadding = padding,
            modifier = modifier.padding(8.dp)
        ) {
            //componente de resumen
            item{
                InfoResumen(
                    fecha = fechaActual,
                    tareasCompletas = tareasCompletas,
                    tareasPendientes = tareasPendientes
                )
            }
            //la lista de las tareas
            items(tareas.size){ posicion ->
                ItemTarea(
                    tarea = tareas[posicion],
                    onClickItem = {},
                    onDeleteItem = {
                        puente.eliminarTarea(tareas[posicion].id)
                    },
                    onToggleCompletion = {
                        puente.completarTarea(tareas[posicion])

                    }
                )
            }

        }


    }

}