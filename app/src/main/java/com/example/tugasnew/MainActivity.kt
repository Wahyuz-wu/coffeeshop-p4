package com.example.tugasnew

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tugasnew.ui.theme.TugasNewTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TugasNewTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = { BottomNavBar(navController) }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            MyScreen()
                            Greeting(
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
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var textdayu by remember { mutableStateOf("") }
    Column (
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        Text(
            text = "Location",
            color = Color.White,
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = "Tanjungpinang, Kepulauan Riau",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 12.dp, vertical = 20.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    BasicTextField(
                        value = textdayu,
                        onValueChange = { textdayu = it },
                        textStyle = TextStyle(fontSize = 20.sp, color = Color.Black),
                        modifier = Modifier.fillMaxWidth(),
                        decorationBox = { innerTextField ->
                            if (textdayu.isEmpty()) {
                                Text(text = "", color = Color.Gray, fontSize = 20.sp)
                            }
                            innerTextField()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(65.dp)
                    .background(Color(0xFFF3CF8F), shape = RoundedCornerShape(16.dp))
                    .clickable {},
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White,
                    modifier = Modifier.size(34.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFDEA4),

            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 0.dp, height = 210.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Column () {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(horizontal = 25.dp, vertical = (0).dp)
                            .background(Color.Red, shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 4.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Promo",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Buy one get",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 25.dp, vertical = (0).dp),
                        fontSize = 25.sp
                    )
                    Text(
                        text = "one FREE",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 25.dp, vertical = 2.dp),
                        fontSize = 25.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 25.dp, vertical = 0.dp)
                            .background(Color.White, shape = RoundedCornerShape(16.dp))
                            .padding(horizontal = 45.dp, vertical = 10.dp)
                            .clickable{},

                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Learn more"
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.girl),
                    contentDescription = "Promo Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(400.dp)
                        .scale(2.0f)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 0.dp)
                    .background(Color(0xFFD4B98A), shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .clickable{},
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "All Coffee",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 0.dp)
                    .background(Color(0xFFDCDAD6), shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .clickable{},
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Machiato",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 0.dp)
                    .background(Color(0xFFDCDAD6), shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .clickable{},
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Latte",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 0.dp)
                    .background(Color(0xFFDCDAD6), shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .clickable{},
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Americano",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val context = LocalContext.current
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 200.dp, height = 270.dp)
                    .clickable{
                        val intent = Intent(context, MainActivity2::class.java)
                            context.startActivity(intent)
                    },
            ) {
                Spacer(modifier = Modifier.height(35.dp))
                Image(
                    painter = painterResource(id = R.drawable.lattee),
                    contentDescription = "Promo Image",
                    modifier = Modifier
                        .size(150.dp)
                        .offset(x = 25.dp,y = -13.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .scale(1.2f),
                )
                Spacer(modifier = Modifier.height(0.dp))
                Text(
                    text = "Hot Americano",
                    modifier = Modifier
                        .padding(vertical = 0.dp, horizontal = 29.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Lorem, Ipsu",
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(vertical = 0.dp, horizontal = 29.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 200.dp, height = 270.dp)
            ) {
                Spacer(modifier = Modifier.height(35.dp))
                Image(
                    painter = painterResource(id = R.drawable.lattee),
                    contentDescription = "Promo Image",
                    modifier = Modifier
                        .size(150.dp)
                        .offset(x = 25.dp,y = -13.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .scale(1.2f),
                )
                Spacer(modifier = Modifier.height(0.dp))
                Text(
                    text = "Ice Americano",
                    modifier = Modifier
                        .padding(vertical = 0.dp, horizontal = 29.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Lorem, Ipsu",
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(vertical = 0.dp, horizontal = 29.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 200.dp, height = 270.dp)
            ) {
                Spacer(modifier = Modifier.height(35.dp))
                Image(
                    painter = painterResource(id = R.drawable.lattee),
                    contentDescription = "Promo Image",
                    modifier = Modifier
                        .size(150.dp)
                        .offset(x = 25.dp,y = -13.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .scale(1.2f),
                )
                Spacer(modifier = Modifier.height(0.dp))
                Text(
                    text = "Hot Americano",
                    modifier = Modifier
                        .padding(vertical = 0.dp, horizontal = 29.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Lorem, Ipsu",
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(vertical = 0.dp, horizontal = 29.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 200.dp, height = 270.dp)
            ) {
                Spacer(modifier = Modifier.height(35.dp))
                Image(
                    painter = painterResource(id = R.drawable.lattee),
                    contentDescription = "Promo Image",
                    modifier = Modifier
                        .size(150.dp)
                        .offset(x = 25.dp,y = -13.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .scale(1.2f),
                )
                Spacer(modifier = Modifier.height(0.dp))
                Text(
                    text = "Ice Americano",
                    modifier = Modifier
                        .padding(vertical = 0.dp, horizontal = 29.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Lorem, Ipsu",
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(vertical = 0.dp, horizontal = 29.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Composable
fun MyScreen() {
    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFFDCAF60)

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = false
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFDCAF60))
            .padding(180.dp)
    ) {}
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf("Home", "Profile")

    BottomNavigation(
        backgroundColor = Color(0xFFF8F8F8),
        contentColor = Color.Gray,
        modifier = Modifier.offset(y = (-48).dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = if (item == "Home") Icons.Filled.Home else Icons.Filled.Person,
                        contentDescription = item,
                        modifier = Modifier.size(35.dp),
                        tint = if (item == "Home") Color(0xFFE07A5F) else Color.Gray
                    )
                },
                label = {
                    Text(
                        text = item,
                        color = if (item == "Home") Color(0xFFE07A5F) else Color.Gray,
                        fontSize = 12.sp
                    )
                },
                selected = index == 0,
                onClick = { }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TugasNewTheme {
        Greeting("Android")
    }
}