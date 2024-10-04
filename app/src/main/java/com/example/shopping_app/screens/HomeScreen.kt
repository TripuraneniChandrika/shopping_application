package com.example.shopping_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shopping_app.R
import com.example.shopping_app.data.Cart
import com.example.shopping_app.data.ProductsList
import com.example.shopping_app.data.popularProductsList

@Composable
fun HomeScreen(navHostController: NavHostController) {
    val cartItems by remember { derivedStateOf { CartManager.cartItems } }
   // val productCounts = remember { mutableStateOf(mutableMapOf<ProductsList, Int>()) }
    val selectedProducts = remember { mutableStateOf(mutableMapOf<ProductsList, Int>()) }


    Box(modifier = Modifier
        .padding(20.dp)
        .fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
            Header()
            Spacer(modifier = Modifier.height(10.dp))
            textField(text = "", onValueChanged = {  })
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Popular Trends",
                fontFamily = FontFamily(Font(R.font.alkalami)),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            ProductGrid(
                products = popularProductsList,
                selectedProducts = selectedProducts,
                navHostController = navHostController
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
                .background(Color.Transparent)
        ) {
            AddtoCart(
                navHostController = navHostController,
                count = 0,
                product = ProductsList(id = 0, name = "", description = "", price = "", image = 0),
                onClick = {
                    selectedProducts.value.forEach { (product, count) ->
                        CartManager.addProductToCart(product, count)
                    }
                    navHostController.navigate("checkout")
                }
            )
        }
    }
}

@Composable
fun ProductGrid(
    products: List<ProductsList>,
    selectedProducts: MutableState<MutableMap<ProductsList, Int>>,
    navHostController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(5.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(products) { product ->
            val count by remember {
                derivedStateOf {
                    CartManager.cartItems.find { it.id == product.id }?.qty ?: 0
                }
            }
            ProductItem(
                product = product,
                count = count,
                onCountChange = { newCount ->
                    if (newCount > 0) {
                        CartManager.addProductToCart(product, newCount)
                    } else {
                        CartManager.removeItems(
                            Cart(
                                id = product.id,
                                icon = product.image,
                                title = product.name,
                                qty = count,
                                price = product.price
                            )
                        )
                    }
                },
                onClick = { }
            )

        }
    }
}

@Composable
fun ProductItem(
    product: ProductsList,
    count: Int,
    onCountChange: (Int) -> Unit,
    onClick: () -> Unit
) {
    var localCount by remember { mutableStateOf(count) }

    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White),
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = product.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.alkalami)),
                    color = Color.Black
                )
                Text(
                    text = product.description,
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.alkalami)),
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = product.price,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.alkalami)),
                    modifier = Modifier.fillMaxWidth()
                )
                ProductCountAdjuster(count = localCount, onCountChange = {
                    localCount = it
                    onCountChange(it)
                })
            }
        }
    }
}


@Composable
fun ProductCountAdjuster(count: Int, onCountChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        ProductDecreaseButton {
            if (count > 0) onCountChange(count - 1)
        }
        Text(
            text = "$count",
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .align(Alignment.CenterVertically)
        )
        ProductCountButton {
            onCountChange(count + 1)
        }
    }
}

@Composable
fun AddtoCart(
    navHostController: NavHostController,
    count: Int,
    onClick: () -> Unit,
    product: ProductsList,
    isEnabled: Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Button(
            onClick = {
                if (isEnabled) onClick()
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            enabled = isEnabled
        ) {
            Text(
                text = "Add to Cart",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.alkalami)),
                    color = Color.White
                )
            )
        }
    }
}

@Composable
fun Header(onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Explore the Latest Arrivals",
            fontFamily = FontFamily(Font(R.font.arizonia)),
            fontSize = 25.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.Gray
            )
        }
        IconButton(
            onClick = { onClick() },
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Notification Icon",
                tint = Color.Gray
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textField(text: String, onValueChanged: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = onValueChanged,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(16.dp)),
        placeholder = {
            Text(
                text = "Search by Product, Brand & More",
                fontFamily = FontFamily(Font(R.font.akaya_kanadaka)),
                fontSize = 15.sp
            )
        },
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}