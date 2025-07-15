package com.example.tugasnew

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.tugasnew.ui.theme.TugasNewTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TugasNewTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = { tombolbeli(navController) }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            Greeting2(
                                name = "Android",
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    val rotation = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    var targetSize by remember { mutableStateOf(230.dp) }
    val boxSize by animateDpAsState(
        targetValue = targetSize,
        animationSpec = tween(durationMillis = 500)
    )

    Column (
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 40.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val context = LocalContext.current
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFF7E7E7E),
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    }
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Detail",
                fontSize = 32.sp,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            // Ikon Favorit
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color(0xFF7E7E7E),
                modifier = Modifier
                    .size(48.dp)
                    .clickable { }
            )

        }
        Spacer(modifier = Modifier.height(55.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(boxSize)
                .clip(RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pipip),
                contentDescription = "Promo Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        rotationZ = rotation.value
                }
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Hot Americano",
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            shape = RectangleShape,
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {

                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState()),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Lorem",
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            listOf(
                                "S" to 150.dp,
                                "M" to 200.dp,
                                "L" to 250.dp
                            ).forEach { (label, size) ->
                                Box(
                                    modifier = Modifier
                                        .size(35.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFD4B98A))
                                        .clickable {
                                            targetSize = size
                                            scope.launch {
                                                rotation.snapTo(0f)
                                                rotation.animateTo(
                                                    targetValue = 360f,
                                                    animationSpec = tween(durationMillis = 800, easing = LinearEasing)
                                                )
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = label,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star",
                            tint = Color(0xFFFFC107), // Warna kuning bintang
                            modifier = Modifier.size(20.dp)
                        )

                        Text(
                            text = "4.8",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 4.dp)
                        )

                        Text(
                            text = "(230)",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }

//                Spacer(modifier = Modifier.width(12.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    listOf(
                        Icons.Filled.LocalShipping,
                        Icons.Filled.LocalCafe,
                        Icons.Filled.ShoppingBag
                    ).forEach { icon ->
                        Card(
                            modifier = Modifier.size(40.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(Color(0xFFE7E7E7)) // Warna latar belakang ikon
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    tint = Color(0xFFE67E22), // Warna ikon oranye
                                    modifier = Modifier.size(24.dp).then(if (icon == Icons.Filled.LocalShipping) Modifier.graphicsLayer { scaleX = -1f } else Modifier)
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(
            color = Color(0xFF888585),
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Description",
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleMedium,
//            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Facilis iste explicabo consequuntur ipsum iure debitis, aperiam...",
            fontSize = 15.sp,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray
        )
    }
}

@Composable
fun tombolbeli(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = -48.dp)
                .height(3.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Gray.copy(alpha = 0.2f))
                    )
                )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .offset(y = (-48).dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Price",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    text = "Rp 18.000",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE67E22)
                )
            }
            val context = LocalContext.current
            Button(
                onClick = {
                    val intent = Intent(context, MainActivity3::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE67E22)),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .height(50.dp)
                    .width(220.dp)
            ) {
                Text(text = "Buy Now", color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun PreviewGreeting2() {
    Greeting2(name = "Jetpack Compose")
}