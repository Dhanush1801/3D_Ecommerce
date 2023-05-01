package com.example.shopnow.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shopnow.model.Product
import com.example.shopnow.model.products

@Composable
fun ProductList(){
    LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp), contentPadding = PaddingValues(10.dp)){
   items(items = products) { product ->
       ProductCard(product = product){

       }
   }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(product: Product, onClick: () -> Unit){
    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .height(130.dp), onClick = onClick ) {
        Row(Modifier.padding(10.dp)) {
            Image(modifier = Modifier.weight(5f), painter = painterResource(id = product.imageId), contentDescription = "")
            Spacer(modifier = Modifier.size(15.dp))
            Column(modifier = Modifier
                .weight(5f)
                .fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                Text(text ="₹${product.price}")
                Row() {
                    RatingBar(rating = product.rating,Modifier.height(20.dp))
                    Text(text = "(${product.ratingCount})")
                }
                Text(modifier = Modifier.padding(vertical = 2.dp), text = product.delivery, style = MaterialTheme.typography.labelSmall.copy(
                    Color.DarkGray))

            }
        }


    }
}

@Preview
@Composable 
fun ProductCardPreview(){
    ProductCard(product = products.first(), onClick = {})
}
@Preview
@Composable
fun ProductListPreview(){
    ProductList()
}