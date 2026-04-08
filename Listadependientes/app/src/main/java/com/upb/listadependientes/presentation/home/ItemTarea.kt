package com.upb.listadependientes.presentation.home

import android.graphics.pdf.models.ListItem
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.upb.listadependientes.domain.Tarea

//@Preview(showBackground = true)
@Composable
fun ItemTarea(
    modifier: Modifier = Modifier,
    tarea: Tarea,
    onClickItem: (String) -> Unit,
    onDeleteItem: (String) -> Unit,
    onToggleCompletion:(Tarea) -> Unit
    ){
    Row(
        modifier = modifier.clickable{
            onClickItem(tarea.id)
        }.background(
            color = MaterialTheme.colorScheme.surfaceContainer
        ).padding(
            horizontal = 8.dp
        )
    ){
        Checkbox(
            checked = tarea.estado,
            onCheckedChange = {
                onToggleCompletion(tarea)
            }
        )
    }

}