package com.example.tuku

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun CartScreen(cartViewModel: CartViewModel) {
    val cartItems = cartViewModel.cartItems // Access cart items from ViewModel

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "My Cart (${cartItems.size} items)",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            color = MaterialTheme.colors.primary
        )
        LazyColumn {
            items(cartItems) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Display image of the item
                        Image(
                            painter = painterResource(id = item.image),
                            contentDescription = item.title,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.subtitle1,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "$${item.price}",
                                style = MaterialTheme.typography.body2,
                                color = Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    // Handle decrement logic here
                                    if (item.quantity > 1) {
                                        item.quantity -= 1
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Remove,
                                    contentDescription = "Decrease quantity",
                                    tint = MaterialTheme.colors.primary
                                )
                            }
                            Text(
                                text = item.quantity.toString(),
                                style = MaterialTheme.typography.body1
                            )
                            IconButton(
                                onClick = {
                                    // Handle increment logic here
                                    item.quantity += 1
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Increase quantity",
                                    tint = MaterialTheme.colors.primary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
