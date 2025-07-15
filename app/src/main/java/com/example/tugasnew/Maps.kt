package com.example.tugasnew

import com.google.android.gms.location.LocationServices
import android.content.pm.PackageManager
import android.Manifest
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tugasnew.ui.theme.TugasNewTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class Maps : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TugasNewTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    var distanceKm by remember { mutableStateOf(0.0) }
                    var deliveryLocation by remember { mutableStateOf("") }
                    Scaffold(
                        bottomBar = { viewmap(navController,distanceKm,deliveryLocation) }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            Greeting4(
                                name = "Android",
                                modifier = Modifier.padding(innerPadding),
                                onDistanceCalculated = { distanceKm = it },
                                onDeliveryLocationChanged = { deliveryLocation = it }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Greeting4(
    name: String,
    modifier: Modifier = Modifier,
    onDistanceCalculated: (Double) -> Unit,
    onDeliveryLocationChanged: (String) -> Unit
) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    var currentLocation by remember { mutableStateOf<LatLng?>(null) }
    val destinationState = remember { mutableStateOf<LatLng?>(null) }
    val routePoints = remember { mutableStateListOf<LatLng>() }

    val cameraPositionState = rememberCameraPositionState()
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    val defaultLocation = LatLng(-6.200000, 106.816666)
    val apiKey = "AIzaSyA0KXA2Jy6vmFSB0EXIpsLRwk9m0RXBdmk" // <-- Ganti di sini ya

    // Ambil lokasi awal
    LaunchedEffect(locationPermissionState.status) {
        if (!locationPermissionState.status.isGranted) {
            locationPermissionState.launchPermissionRequest()
        } else {
            try {
                val location = fusedLocationClient.lastLocation.await()
                val latLng = location?.let { LatLng(it.latitude, it.longitude) } ?: defaultLocation
                currentLocation = latLng
                cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 16f)
            } catch (e: Exception) {
                currentLocation = defaultLocation
                cameraPositionState.position = CameraPosition.fromLatLngZoom(defaultLocation, 15f)
            }
        }
    }

    // Ambil rute saat tujuan berubah
    LaunchedEffect(currentLocation, destinationState.value) {
        val destination = destinationState.value
        if (currentLocation != null && destination != null) {
            val km = calculateDistance(currentLocation!!, destination)
            onDistanceCalculated(km)

            val address = getAddressFromLatLng(context, destination)
            onDeliveryLocationChanged(address)

            val route = getRoutePoints(currentLocation!!, destination, apiKey)
            Log.d("DEBUG_ROUTE", "Jumlah titik rute: ${route.size}")
            routePoints.clear()
            routePoints.addAll(route)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = { latLng ->
                destinationState.value = latLng
            }
        ) {
            currentLocation?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = "Lokasi Kamu"
                )
            }
            destinationState.value?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = "Tujuan"
                )
            }

            if (routePoints.size >= 2) {
                Polyline(
                    points = routePoints,
                    color = Color.Blue,
                    width = 6f
                )
            }
        }
    }
}



//ambil alamat
fun getAddressFromLatLng(context: Context, latLng: LatLng): String {
    return try {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        addressList?.get(0)?.getAddressLine(0) ?: "Unknown Location"
    } catch (e: Exception) {
        "Unknown Location"
    }
}


//kalkulasi
fun calculateDistance(pt1: LatLng, pt2: LatLng): Double {
    return try {
        val theta = pt1.longitude - pt2.longitude
        var dist = Math.sin(Math.toRadians(pt1.latitude)) * Math.sin(Math.toRadians(pt2.latitude)) +
                Math.cos(Math.toRadians(pt1.latitude)) * Math.cos(Math.toRadians(pt2.latitude)) * Math.cos(Math.toRadians(theta))

        dist = Math.acos(dist)
        dist = Math.toDegrees(dist)
        val distanceMeters = dist * 60 * 1853.1596
        distanceMeters / 1000.0 // konversi ke kilometer
    } catch (e: Exception) {
        println(e.message)
        0.0
    }
}

suspend fun getRoutePoints(origin: LatLng, destination: LatLng, apiKey: String): List<LatLng> {
    return withContext(Dispatchers.IO) {
        try {
            val urlStr = "https://maps.googleapis.com/maps/api/directions/json" +
                    "?origin=${origin.latitude},${origin.longitude}" +
                    "&destination=${destination.latitude},${destination.longitude}" +
                    "&mode=driving&key=$apiKey"

            val url = URL(urlStr)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            val inputStream = connection.inputStream
            val response = inputStream.bufferedReader().use { it.readText() }

            Log.d("ROUTE_RESPONSE", response)

            val json = JSONObject(response)

            val status = json.getString("status")
            if (status != "OK") {
                Log.e("ROUTE_ERROR", "Status not OK: $status")
                return@withContext emptyList()
            }

            val routes = json.getJSONArray("routes")
            val overviewPolyline = routes.getJSONObject(0)
                .getJSONObject("overview_polyline")
                .getString("points")

            return@withContext decodePolyline(overviewPolyline)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("ROUTE_ERROR", e.toString())
            return@withContext emptyList()
        }
    }
}


fun decodePolyline(encoded: String): List<LatLng> {
    val poly = ArrayList<LatLng>()
    var index = 0
    val len = encoded.length
    var lat = 0
    var lng = 0

    while (index < len) {
        var b: Int
        var shift = 0
        var result = 0
        do {
            b = encoded[index++].code - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lat += dlat

        shift = 0
        result = 0
        do {
            b = encoded[index++].code - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lng += dlng

        val latLng = LatLng(lat / 1E5, lng / 1E5)
        poly.add(latLng)
    }
    return poly
}



@Composable
fun viewmap(navController: NavController, distanceKm: Double, deliveryLocation: String) {
    Column(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)).background(Color(
            0xFFFFFFFF
        )
        ).padding(horizontal = 20.dp).padding(vertical = 25.dp).offset(y = (-48).dp),
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
            Row() {
                Text(
                    text = "${"%.2f".format(distanceKm)} Km",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = " dari lokasi anda",
                    fontSize = 20.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Delivery to $deliveryLocation",
                fontSize = 13.sp,
                color = Color.Gray,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .height(3.dp)
//                    .offset(y = -10.dp)
                .background(
                    Color(0xFFFF5722)
                )
        )
        Spacer(modifier = Modifier.height(5.dp))

        Card(
            shape = RectangleShape,
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .border(BorderStroke(1.dp, Color(0xFFADABAB)), shape = RoundedCornerShape(10.dp))
                    .padding(20.dp)

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(55.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(BorderStroke(1.dp, Color(0xFFADABAB)), shape = RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.QrCode,
                            contentDescription = "nambah",
                            tint = Color(0xFFFF5722),
                            modifier = Modifier
                                .size(50.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Delivered Your Order",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "We will deliver your goods to you in the shortest possible time",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(75.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(BorderStroke(1.dp, Color(0xFFADABAB)), shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mavuika),
                    contentDescription = "Promo Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(modifier = Modifier.offset(x = -25.dp)) {
                Text(
                    text = "Mavuika",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Personal Courier",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(BorderStroke(1.dp, Color(0xFFADABAB)), shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "nambah",
                    tint = Color(0xFFFF5722),
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }
    }
}
