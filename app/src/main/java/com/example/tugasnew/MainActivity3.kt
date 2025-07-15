package com.example.tugasnew

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tugasnew.ui.theme.TugasNewTheme

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TugasNewTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = { tombolbeli2(navController) }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            Greeting3(
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
fun Greeting3(name: String, modifier: Modifier = Modifier) {

        Column (
            modifier = Modifier.padding(vertical = 40.dp).verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val context = LocalContext.current
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF7E7E7E),
                    modifier = Modifier
                        .size(48.dp)
                        .offset(x = -6.dp)
                        .clickable {
                            val intent = Intent(context, MainActivity2::class.java)
                            context.startActivity(intent)
                        }
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Order",
                    fontSize = 25.sp,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.offset(x = -22.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Delivery Address",
                fontSize = 20.sp,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(13.dp))
            Text(
                text = "Jl. Rajawali",
                fontSize = 17.sp,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(0.dp))
            Text(
                text = "Kampung Air Raja KM. 14 Tanjungpinang",
                fontSize = 13.sp,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, Color(0xFF888888)),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .height(30.dp)
                        .width(135.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Ngedit",
                            tint = Color.Black,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Edit Address",
                            color = Color(0xFF9D9C9C),
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, Color(0xFF888888)),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .height(30.dp)
                        .width(115.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.StickyNote2,
                            contentDescription = "Ngedit",
                            tint = Color.Black,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Add Note",
                            color = Color(0xFF9D9C9C),
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            //lol1
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .height(1.dp)
                    .offset(y = -10.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xFFB4B4B4).copy(alpha = 0.2f))
                        )
                    )
            )

            Spacer(modifier = Modifier.height(10.dp))
            Card(
                shape = RectangleShape,
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(65.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.poppp),
                            contentDescription = "Promo Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Hot Americano",
                            fontSize = 20.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Lorem Ipsu",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 0.dp, vertical = 0.dp)
                                .background(Color(0xFFA69F9F), shape = RoundedCornerShape(50.dp))
                                .padding(horizontal = 5.dp, vertical = 5.dp)
                                .clickable{},
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "nambah",
                                tint = Color(0xFF000000),
                                modifier = Modifier
                                    .size(10.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "1",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 0.dp, vertical = 0.dp)
                                .background(Color(0xFFA69F9F), shape = RoundedCornerShape(50.dp))
                                .padding(horizontal = 5.dp, vertical = 5.dp)
                                .clickable{},
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Icon(
                                imageVector = Icons.Default.Minimize,
                                contentDescription = "minus",
                                tint = Color(0xFF000000),
                                modifier = Modifier
                                    .size(10.dp)
                                    .offset(y = -3.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            //lol2
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .offset(y = -10.dp)
                    .height(4.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xFFFFAB69)) //.copy(alpha = 1f))
                        )
                    )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(1.dp, Color(0xFFADABAB)),
                shape = RoundedCornerShape(15.dp),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Discount,
                        contentDescription = "dc",
                        tint = Color(0xFFDCAF60),
                        modifier = Modifier.size(17.dp).offset(x = 25.dp)
                    )
                    Text(
                        text = "1 Discount is Applies",
                        color = Color(0xFF9D9C9C),
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.weight(1f).offset(x = -50.dp),
                        textAlign = TextAlign.Center
                    )
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "kanan",
                        tint = Color(0xFF696767),
                        modifier = Modifier.size(28.dp).offset(x = -10.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "Payment Summary",
                fontSize = 18.sp,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Price",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Gray
                )
                Text(
                    text = "Rp 18000",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Delivery Fee",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Gray
                )
                Text(
                    text = "Rp 7000",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.offset(x = -10.dp)
                )
            }

            Text(
                text = "assadsadsd\n2wdasdas\nwdwdwd\ndwwdwdw\nwdwdwdw\ndwdwdwdw\ndwdwdwdw\ndwdwdwdwdwdw\ndwdwdwdwdwdw\ndwdwdwdwdwd\ndwdwdwdwdwdwd\ndwdwdwdwdwd",
                fontSize = 18.sp,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Transparent,
                modifier = Modifier.offset(x = -10.dp)
            )
        }
}

@Composable
fun tombolbeli2(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 40.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .offset(y = 5.dp)
                .height(3.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Gray.copy(alpha = 0.2f))
                    )
                )
        )
        Card(
            shape = RectangleShape,
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.AccountBalanceWallet,
                    contentDescription = "Back",
                    tint = Color(0xFFFF9D47),
                    modifier = Modifier
                        .size(38.dp)
                        .clickable { }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Cash/Wallet",
                        fontSize = 20.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Rp 25.000",
                        fontSize = 10.sp,
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Spacer(modifier = Modifier.width(160.dp))
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = "Back",
                    tint = Color(0xFF000000),
                    modifier = Modifier
                        .size(38.dp)
                        .clickable { }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .offset(y = -18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val context = LocalContext.current
            Button(
                onClick = {
                    val intent = Intent(context, Maps::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE67E22)),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .width(220.dp)
            ) {
                Text(text = "Order", color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun PreviewGreeting3() {
    Greeting3("edu")
}