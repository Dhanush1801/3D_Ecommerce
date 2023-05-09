package com.example.shopnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shopnow.model.Product
import com.example.shopnow.model.products
import com.example.shopnow.ui.ProductDetails
import com.example.shopnow.ui.ProductList
import com.example.shopnow.ui.theme.ShopNowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopNowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var currentProduct by remember {
        mutableStateOf(products.last())
    }
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        })
        NavHost(navController = navController, startDestination = "productList") {
            composable("productList") {
                ProductList(onClick = {
                    currentProduct = it
                    navController.navigate("productDetail")
                })
            }
            composable("productDetail") { ProductDetails(currentProduct) }
            /*...*/
        }

    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShopNowTheme {
        HomeScreen("Android")
    }
}