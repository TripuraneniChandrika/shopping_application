package com.example.shopping_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shopping_app.R
import com.example.shopping_app.data.Cart



@Composable
fun CheckoutScreen(navHostController: NavHostController) {
    val cartItems by remember { derivedStateOf { CartManager.cartItems } }
    val totalAmount by remember { derivedStateOf { CartManager.getTotalAmount() } }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
        ) {
            IconButton(onClick = {
                navHostController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "My Cart",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.alkalami))
            )
            Spacer(modifier = Modifier.height(15.dp))

            if (cartItems.isEmpty()) {
                Text(
                    text = "Your cart is empty",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(Font(R.font.alkalami)),
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    textAlign = TextAlign.Center
                )
            } else {
                LazyColumn {
                    items(cartItems) { item ->
                        ShoppingItems(
                            data = item,
                            quantity = { updatedItem ->
                                val index = cartItems.indexOfFirst { it.id == updatedItem.id }
                                if (index != -1) {
                                    cartItems[index] = updatedItem
                                    CartManager.cartItems[index] = updatedItem
                                }
                            },
                            remove = { itemToRemove ->
                                CartManager.removeItems(itemToRemove)
                            }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(15.dp))
                        Amount(totalAmount) {
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Amount(
    totalAmount: Double,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Total",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.alkalami)),
                            color = Color.Black
                        )
                    )
                    Text(
                        text = "₹${"%.2f".format(totalAmount)}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.alkalami)),
                            color = Color.Black
                        )
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = onClick,
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Proceed to payment",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.alkalami)),
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ShoppingItems(
    data: Cart,
    quantity: (Cart) -> Unit,
    remove: (Cart) -> Unit
) {
    var qty by remember { mutableStateOf(data.qty) }

    // Updates the qty state when data.qty changes
    LaunchedEffect(data.id) {
        qty = data.qty
    }
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = data.icon),
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(80.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = data.title,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400,
                            color = Color.Black
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = { remove(data) },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .background(Color.White)
                            .border(width = 1.dp, color = Color.LightGray)
                            .size(30.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }
                Text(
                    text = "${qty} Qty",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                ) {
                    Row(
                        modifier = Modifier.weight(1f)
                    ) {
                        ProductDecreaseButton {
                            if (qty > 0) {
                                qty--
                                if (qty == 0) {
                                    remove(data)
                                } else {
                                    quantity(data.copy(qty = qty))
                                }
                            }
                        }
                        Text(
                            text = "$qty",
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .align(Alignment.CenterVertically)
                        )
                        ProductCountButton {
                            qty++
                            quantity(data.copy(qty = qty))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckoutScreenPreview() {
    CheckoutScreen(rememberNavController())
}
