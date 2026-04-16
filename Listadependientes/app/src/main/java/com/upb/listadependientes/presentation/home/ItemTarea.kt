package com.upb.listadependientes.presentation.home

import android.R
import android.graphics.pdf.models.ListItem
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.upb.listadependientes.domain.Categoria
import com.upb.listadependientes.domain.Tarea
import kotlin.toString

//@Preview(showBackground = true)
@Composable
fun ItemTarea(
    modifier: Modifier = Modifier,
    onClickItem:(String) -> Unit,
    onDeleteItem:(String) -> Unit,
    onToggleCompletion:(Tarea) -> Unit,
    task:Tarea,
) {
    Row (
        modifier = modifier
            .clickable {
                onClickItem(task.id)
            }
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
            )
            .padding(horizontal = 8.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = task.estado,
            onCheckedChange = {
                onToggleCompletion(
                    task
                )
            },
        )
        Column (
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(
                4.dp
            ),
            modifier = Modifier.padding(
                8.dp
            ).weight(
                1f
            )
        ){
            Text(
                text = task.titulo,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall.copy(
                    textDecoration = if(task.estado) TextDecoration.LineThrough
                    else TextDecoration.None
                ),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            if(!task.estado){
                task.descripcion?.let {
                    Text(
                        text = it,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                task.categoria?.let {
                    Text(
                        text = it.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Box {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Task",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onDeleteItem(task.id)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemTarea(){
    ItemTarea(
      task = Tarea(
          id = "1",
          titulo = "Lavar platos",
          estado = false,
          descripcion = "Lavarlos antes de las 12 pm",
          categoria = Categoria.PERSONAL
      ),
        onClickItem = {},
    onDeleteItem={},
    onToggleCompletion={}
    )
}