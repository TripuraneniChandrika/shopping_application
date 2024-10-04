package com.example.shopping_app.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopping_app.R
import com.example.shopping_app.data.Cart
import com.example.shopping_app.data.ProductsList
import com.example.shopping_app.data.popularProductsList

@Composable
fun DetailScreen(navHostController: NavHostController,productId: String,) {
    val productIdInt = productId.toIntOrNull() ?: return
    val product = popularProductsList.find { it.id == productIdInt }
    var count by remember { mutableStateOf(1) }
    var selectedTab by remember { mutableStateOf(0) }

    product?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = it.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            HeaderSection {
                navHostController.navigateUp()
            }
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxSize()
                    .weight(1f)
            ) {
                item {
                    ProductHeaderSection(product = it, count = count) { newCount ->
                        count = newCount
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Tabsection(selectedTab = selectedTab) { tabIndex ->
                        selectedTab = tabIndex
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TabsContent(selectedTab = selectedTab)
                    Spacer(modifier = Modifier.height(15.dp))
                    RecommendedProducts()
                }
            }
            /*AddtoCart(navHostController) {
                CartManager.cartItems.add(
                    Cart(
                        id = product.id,
                        icon = product.image,
                        title = product.name,
                        qty = count,
                        price = product.price
                    )
                )

            }*/
        }
    }
}

@Composable
fun HeaderSection(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Gray
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = "Share",
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(15.dp))
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Liked",
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(10.dp))
    }
}

@Composable
fun ProductHeaderSection(
    product: ProductsList,
    count: Int,
    onCount: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = product.name,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.alkalami)),
                color = Color.Black
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = product.price,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.alkalami)),
                    color = Color.Black
                ),
                modifier = Modifier.weight(1f)
            )
            ProductDecreaseButton(
                onClick = { if (count > 1) onCount(count - 1) }
            )
            Text(
                text = "$count",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .align(Alignment.CenterVertically)
            )
            ProductCountButton(
                onClick = { onCount(count + 1) }
            )
        }
    }
}

@Composable
fun ProductCountButton(
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.size(35.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Text(
            text = "+",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
    }
}

@Composable
fun ProductDecreaseButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.size(35.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Text(
            text = "-",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
    }
}

@Composable
fun Tabsection(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf("Description", "Rating", "Review")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        tabs.forEachIndexed { index, title ->
            TextButton(
                onClick = { onTabSelected(index) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTab == index) Color.Gray else Color.Transparent
                )
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.alkalami)),
                        color = if (selectedTab == index) Color.White else Color.Black
                    )
                )
            }
        }
    }
}

@Composable
fun TabsContent(selectedTab: Int) {
    val content = listOf(
        "This is the product description. It provides detailed information about the product.",
        "Rating: 4.5",
        "Review: Customer reviews and ratings for this product. Very satisfied customers."
    )
    Text(
        text = content[selectedTab],
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.alkalami)),
            color = Color.Black
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    )
}

///*@Composable
//fun AddtoCart(
//    navHostController: NavHostController,
//    onClick: () -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(10.dp)
//    ) {
//        Button(
//            onClick = {
//                onClick()
//                navHostController.navigate("checkout_screen")
//
//            },
//            modifier = Modifier
//                .height(50.dp)
//                .fillMaxWidth(),
//            shape = RoundedCornerShape(8.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
//        ) {
//            Text(
//                text = "Add to Cart",
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold,
//                    fontFamily = FontFamily(Font(R.font.alkalami)),
//                    color = Color.White
//                )
//            )
//        }
//    }
//}*/



@Composable
fun RecommendedProducts() {
    val recommendedImages = listOf(
        R.drawable.jeans_1,
        R.drawable.dress_5,
        R.drawable.shirt,
        R.drawable.jeans_6,
        R.drawable.sandal_2
    )
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Recommended Products",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.alkalami)),
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow {
            items(recommendedImages) { imageId ->
                Card(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(8.dp)),

                    ) {
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
            }

        }
    }
}