package com.upb.principiosandroid.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upb.principiosandroid.R
import com.upb.principiosandroid.models.Product


//@Preview(showBackground = true) - como tiene una var obligatoria de entrada (producto)
//no puede previsualizar el componente
@Composable
fun CardProduct(
    producto: Product,
    color: Color = MaterialTheme.colorScheme.background, //variable de entrada opcional
    modifier: Modifier = Modifier //es una var de entrada opcional
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 9.dp),
        colors = CardDefaults.cardColors(color),
        elevation = CardDefaults.elevatedCardElevation(30.dp),
        shape = RoundedCornerShape(9.dp),
        onClick = {}
    ) {
        Row(
            modifier = Modifier
                .height(150.dp)
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = producto.imagen),
                contentDescription = "taco descripcion",
                modifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(30.dp)),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    producto.nombre,
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(producto.precio, fontSize = 20.sp)
            }
        }
    }
}

//previsualizar con un producto en especifico

//@Preview(showBackground = true)
//@Composable
//fun CardProductPreview() {
//    val producto1 = Product(
//        imagen = R.drawable.taco,
//        nombre = "Taco rico",
//        precio = "$100",
//    )
//    CardProduct(producto1)
//}

@Preview(showBackground = true)
@Composable
fun CardProductPreview(
    modifier: Modifier = Modifier
) {
    val productos = listOf(
        Product(nombre = "Pizza", precio = "$100", imagen = R.drawable.taco),
        Product(nombre = "Hamburguesa", precio = "$20", imagen = R.drawable.taco),
        Product(nombre = "Taco", precio = "$50", imagen = R.drawable.taco),
        Product(nombre = "Perrito", precio = "$60", imagen = R.drawable.taco),
    )

    LazyColumn (
        modifier = modifier.fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
    ) {
        item {
            Text(
                text = "Android Cafe",
                fontSize = 40.sp,
                //modifier = modifier.padding(20.dp)
            )
        }
        items (productos.size){
            posicion -> CardProduct(productos[posicion])
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun PesosPrueba(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(100.dp)
                .background(color = Color.Red)
        ) {

        }

        Box(
            modifier = Modifier
                .weight(2f)
                .height(100.dp)
                .background(color = Color.Blue)
        ) {

        }

    }
}


