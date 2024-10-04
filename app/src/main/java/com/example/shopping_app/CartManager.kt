import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.shopping_app.data.Cart
import com.example.shopping_app.data.ProductsList

object CartManager {
    val cartItems: SnapshotStateList<Cart> = mutableStateListOf()

   /* fun addProductToCart(product: ProductsList, count: Int) {
        val existingItem = cartItems.find { it.id == product.id }
        if (existingItem != null) {
            existingItem.qty = existingItem.qty + count
            if (existingItem.qty <= 0) {
                cartItems.remove(existingItem)
            }
        } else {
            cartItems.add(
                Cart(
                    id = product.id,
                    icon = product.image,
                    title = product.name,
                    qty = count,
                    price = product.price
                )
            )
        }
    }
*/
   fun addProductToCart(product: ProductsList, count: Int) {
       val existingItem = cartItems.find { it.id == product.id }
       if (existingItem != null) {
           existingItem.qty = count
           if (existingItem.qty <= 0) {
               cartItems.remove(existingItem)
           }
       } else {
           cartItems.add(
               Cart(
                   id = product.id,
                   icon = product.image,
                   title = product.name,
                   qty = count,
                   price = product.price
               )
           )
       }
   }

   /* fun removeItems(item: Cart) {
        val existingItem = cartItems.find { it.id == item.id }
        if (existingItem != null) {
            cartItems.remove(existingItem)
        }
    }
*/
   fun removeItems(item: Cart) {
       cartItems.removeAll { it.id == item.id }
   }
    fun getTotalAmount(): Double {
        return cartItems.sumOf { it.qty * parsePrice(it.price) }
    }

    private fun parsePrice(price: String): Double {
        val cleanedPrice = price.replace("[^\\d.]".toRegex(), "")
        return cleanedPrice.toDoubleOrNull() ?: 0.0
    }
}

