package com.upb.listadependientes.domain

//aqui va la estructura base de una tarea
data class Tarea (
    val id: String,
    val titulo: String,
    val descripcion: String,
    val estado: Boolean,
    val categoria: Categoria?
)