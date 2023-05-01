package com.example.shopnow.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.shopnow.R
import com.example.shopnow.model.Product
import com.example.shopnow.model.products

@Composable
fun ProductDetails(product: Product) {
    Column(
        Modifier
            .padding(10.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = painterResource(id = product.imageId),
            contentDescription = ""
        )
        TryInHomeButton {
            val sceneViewerIntent = Intent(Intent.ACTION_VIEW)
            val intentUri =
                Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
                    .appendQueryParameter("file", product.modelURL)
                    .appendQueryParameter("mode", "ar_only")
                    .appendQueryParameter("resizable", "false")
                    .appendQueryParameter("title", "${product.name} - ₹${product.price}")
                    .build()
            sceneViewerIntent.data = intentUri
            sceneViewerIntent.setPackage("com.google.ar.core")
            startActivity(context, sceneViewerIntent, null)
        }
        Column(Modifier.fillMaxWidth()) {
            Text(text = product.name, style = MaterialTheme.typography.headlineLarge)
            Text(text = "₹${product.price}", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = stringResource(id = R.string.product_details),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(text = "${product.longDescription}")
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = stringResource(id = R.string.in_stock),
                style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xff007848))
            )
            Spacer(modifier = Modifier.size(10.dp))
            Row() {
                RatingBar(rating = product.rating, Modifier.height(20.dp))
                Text(text = "(${product.ratingCount})")
            }
            Text(text = "${product.delivery}")
            Spacer(modifier = Modifier.size(10.dp))
            AddToCartButton {

            }
            BuyNowButton {

            }
        }
    }
}

@Preview
@Composable

fun ProductDetailsPreview() {
    ProductDetails(products.first())

}

@Composable
fun TryInHomeButton(onClick: () -> Unit) {
    ElevatedButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_twotone_photo_camera_24),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.size(17.dp))
        Text(text = stringResource(id = R.string.try_in_your_home))

    }
}

@Composable
fun AddToCartButton(onClick: () -> Unit) {
    FilledTonalButton(
        onClick = onClick,
        Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
        Text(text = stringResource(id = R.string.add_to_cart))
    }
}

@Composable
fun BuyNowButton(onClick: () -> Unit) {
    FilledTonalButton(
        onClick = onClick, Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Text(text = stringResource(id = R.string.buy_now))
    }
}