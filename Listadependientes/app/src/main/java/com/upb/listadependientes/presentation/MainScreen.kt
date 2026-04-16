package com.upb.listadependientes.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.upb.listadependientes.presentation.agregar.AgregarScreen
import com.upb.listadependientes.presentation.home.ListaTareasScreen
import com.upb.listadependientes.viewModel.TareaViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
){
    //saber en que pantalla estoy actualmente
    var pantallaActual by remember {
        mutableStateOf("home")
    }

    val viewModel: TareaViewModel = viewModel()

    //el flujo entre el cambio de pantallas
    when(pantallaActual) {
        "home" -> ListaTareasScreen(
            viewModel = viewModel,
            onNavigateToAdd = {
                pantallaActual = "agregar"
            }
        )

        "agregar" -> AgregarScreen(
            viewModel = viewModel,
            onBack = {
                pantallaActual = "home"
            }
        )

    }
}