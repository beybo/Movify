package com.movify.vistas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movify.database.InfoLista
import com.movify.ui.theme.MovifyTheme
import com.movify.utils.getIconoLista
import info.movito.themoviedbapi.model.MovieDb

@Composable
fun Perfil(
    listas:List<InfoLista>,
    verLista: (InfoLista) -> Unit
){
    Column{
        Text("Tus Listas", fontSize = 30.sp, modifier = Modifier.padding(12.dp))
        LazyColumn{
            items(listas){ lista ->
                CardLista(texto = lista.nombre, onClick = { verLista(lista) }) {
                    Icon(
                        painter = painterResource(getIconoLista(lista.idInfoLista)),
                        contentDescription = null
                    )
                }
            }

        }
    }



}


@Composable
fun CardLista(texto:String,  onClick: ()-> Unit, content:@Composable ()-> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 5.dp)
        .clickable { onClick() },
        elevation = 10.dp,
    ){
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier= Modifier
                .padding(15.dp)
        ){
            Text(texto)
            content()
        }

    }
}


@Composable
fun previewSlot(){
    CardLista("Ver más tarde", {}){
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null /**TO-DO**/
        )
    }
}

@Preview(showBackground = true)
@Composable
fun preview(){
    MovifyTheme() {
        //Perfil()
    }
}