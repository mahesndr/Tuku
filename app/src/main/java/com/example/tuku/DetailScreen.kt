package com.example.tuku

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    navController: NavController,
    itemId: Int?,
    cartViewModel: CartViewModel,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val item = sampleItems.find { it.id == itemId }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    item?.let {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text("Item Detail", color = Color.White) },
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.padding(8.dp) // Padding kecil untuk jarak ikon
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_back_arrow),
                                contentDescription = "Back",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp) // Ukuran ikon disesuaikan
                            )
                        }
                    },
                    backgroundColor = Color(0xFF6200EA),
                    elevation = 4.dp
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Item Image
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(4.dp, RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    elevation = 8.dp
                ) {
                    Image(
                        painter = painterResource(it.image),
                        contentDescription = it.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                // Bagian Detail Item
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF6200EA)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Price: Rp ${it.price}", // Menampilkan harga item
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.SemiBold),
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = it.description,
                        style = MaterialTheme.typography.body1,
                        color = Color.Gray
                    )
                }


                // Buttons Section
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                cartViewModel.addItemToCart(it)
                                scaffoldState.snackbarHostState.showSnackbar("Item added to cart")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cart),
                            contentDescription = "Add to Cart",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Add to Cart", color = Color.White)
                    }

                    Button(
                        onClick = { navController.navigate("cart") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1976D2))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cart),
                            contentDescription = "View Cart",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("View Cart", color = Color.White)
                    }

                    Button(
                        onClick = {
                            val shareIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "Check out this item: ${it.title}\n${it.description}"
                                )
                                type = "text/plain"
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share via"))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6200EA))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = "Share",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Share", color = Color.White)
                    }
                }
            }
        }
    } ?: Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Item not found", color = Color.Red)
    }
}
