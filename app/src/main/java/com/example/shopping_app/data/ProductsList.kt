package com.example.shopping_app.data

import androidx.annotation.DrawableRes
import com.example.shopping_app.R

data class ProductsList(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes val image: Int,
    val price: String
)

val popularProductsList = listOf(
    ProductsList(
        1,
        "Half Shoulder shirt",
        "Blue color half shoulder shirt ",
        R.drawable.blueshirt,
        "₹ 600"
    ),
    ProductsList(
        2,
        "Flare dress",
        "Floral Print Fit & Flare Dress",
        R.drawable.dress_1,
        "₹ 775"
    ),
    ProductsList(
        3,
        "Antheaa",
        "Chiffon Fit & Flare Dress",
        R.drawable.dress_3,
        "₹ 1200"
    ),
    ProductsList(
        4,
        "Dholiwal Fashion ",
        "Women Gown Dark Blue",
        R.drawable.dress_9,
        "₹ 500"
    ),
    ProductsList(
        5,
        "Dholiwal Fashion ",
        "Women Emboidery light blue dress",
        R.drawable.dress_4,
        "₹ 2999"
    ),
    ProductsList(
        6,
        "Pant n lites  ",
        "Women Gown White Dress",
        R.drawable.dress_5,
        "₹ 2500"
    ),
    ProductsList(
        7,
        "Antheaa",
        "Chiffon Fit & Flare Dress",
        R.drawable.dress_6,
        "₹ 1300"
    ),
    ProductsList(
        8,
        "Sharara suit",
        "Glamorous mustard yellow color semi stitched",
        R.drawable.dress_10,
        "₹ 2599"
    ),
    ProductsList(
        9,
        "Sharara suit",
        "Teal Green Printed A-Line Dress with Shrug",
        R.drawable.dress_7,
        "₹2,799"
    ),
    ProductsList(
        10,
        "Sharara suit",
        "Teal Green Printed A-Line Dress with Shrug",
        R.drawable.dress_8,
        "₹2,799"
    ),
    ProductsList(
        11,
        "Watch",
        "Golden color watch",
        R.drawable.golden_watch,
        "₹3500"
    ),
    ProductsList(
        12,
        "Skinny Fit ",
        "Women Blue Skinny Fit High Rise Jeans",
        R.drawable.jeans_1,
        "₹1849"
    ),
    ProductsList(
        13,
        " Casual Wear",
        "Women's Casual Wear Stretchable Jeans",
        R.drawable.jeans_2,
        "₹1200"
    ),
    ProductsList(
        14,
        "Cargo Jeans",
        " Cotton Blend Blue Color Women Cargo Jeans",
        R.drawable.jeans_3,
        "₹2,199"
    ),
    ProductsList(
        15,
        "Sandals",
        "Brown color flat sandals",
        R.drawable.sandal_1,
        "₹500"
    ),
    ProductsList(
        16,
        "Flipflops",
        "Blue color floral flat sandals",
        R.drawable.sandal_2,
        "₹600"
    ),
    ProductsList(
        17,
        "Cotton shirt",
        "Blue color checks cotton shirt",
        R.drawable.shirt,
        "₹1600"
    ),
    ProductsList(
        18,
        "Organzo shirt",
        "Gray color printed shirt",
        R.drawable.shirt_2,
        "₹2600"
    ),
    ProductsList(
        19,
        "T shirt",
        "Olive green plain tshirt",
        R.drawable.tshirt_2,
        "₹200"
    ),
    ProductsList(
        20,
        "Lambard ",
        "Lambard White Sneakers for Men",
        R.drawable.shoes_1,
        "₹1200"
    ),
    ProductsList(
        21,
        "Bacca Bucci  ",
         "White Sneakers and Sports Shoes",
        R.drawable.shoes_2,
        "₹1299"
    ),
    ProductsList(
        22,
        "Cushioned Men",
        " Superior Grip Cushioned Men Running Shoes",
        R.drawable.shoes_6,
        "₹1599"
    ),
    ProductsList(
        23,
        " Casual Shoes",
        "Men Synthetic Leather Casual Shoes",
        R.drawable.shoes_3,
        "₹500"
    ),
    ProductsList(
        24,
        "High Waist Wide ",
        "Men Synthetic Leather Casual Shoes",
        R.drawable.jeans_4,
        "₹599"
    ),
    ProductsList(
        25,
        "High Waist Wide ",
        "Men Synthetic Leather Casual Shoes",
        R.drawable.jeans_4,
        "₹599"
    ),
    ProductsList(
        26,
        "OUTRYT",
        "Women High-Rise Mid-Wash Jeans",
        R.drawable.jeans_5,
        "₹1599"
    ),
    ProductsList(
        27,
        "Denim Jeans",
        "Women Denim Black Cargo Jeans",
        R.drawable.jeans_7,
        "₹2,299"
    ),
    ProductsList(
        28,
        "ASIAN",
        "Textured Running Sports Shoes",
        R.drawable.shoes_4,
        "₹1,419"
    ),
    ProductsList(
        29,
        " Sneakers",
        "Men Sneakers Shoes",
        R.drawable.shoes_5,
        "₹419"
    )

)
