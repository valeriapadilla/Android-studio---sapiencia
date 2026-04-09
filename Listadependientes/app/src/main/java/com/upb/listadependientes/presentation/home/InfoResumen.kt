package com.upb.listadependientes.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PreviewInfoResumen(
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Abril 8 2026",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "1 Tarea pendiente y 2 Realizadas",
            style = MaterialTheme.typography.titleSmall
        )
    }
}


@Composable
fun InfoResumen(
    modifier: Modifier = Modifier,
    fecha: String,
    tareasCompletas: Int,
    tareasPendientes: Int
){
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = fecha,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "$tareasPendientes Tarea pendiente y $tareasCompletas Realizadas",
            style = MaterialTheme.typography.titleSmall
        )
    }
}