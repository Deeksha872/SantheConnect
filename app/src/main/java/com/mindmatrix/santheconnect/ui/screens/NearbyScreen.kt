package com.mindmatrix.santheconnect.ui.screens

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.mindmatrix.santheconnect.R
import com.mindmatrix.santheconnect.data.Place
import com.mindmatrix.santheconnect.data.SampleRepo

private val categories =
    listOf("All", "Food", "Markets", "Stays", "Crafts")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NearbyScreen(selectedCategory: String = "All") {

    val ctx = LocalContext.current

    var query by remember {
        mutableStateOf("")
    }

    var selected by remember {
        mutableStateOf(selectedCategory)
    }

    var currentLocation by remember {
        mutableStateOf<Location?>(null)
    }

    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(ctx)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->

        if (granted) {

            if (
                ContextCompat.checkSelfPermission(
                    ctx,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {

                fusedLocationClient.lastLocation
                    .addOnSuccessListener {
                        currentLocation = it
                    }
            }
        }
    }

    LaunchedEffect(Unit) {

        if (
            ContextCompat.checkSelfPermission(
                ctx,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            fusedLocationClient.lastLocation
                .addOnSuccessListener {
                    currentLocation = it
                }

        } else {

            launcher.launch(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }

    val filtered = SampleRepo.places.filter { place ->

        val categoryMatch =
            selected == "All" ||
                    place.category == selected

        val searchMatch =
            query.isBlank() ||
                    place.name.contains(query, true)

        val nearbyMatch = currentLocation?.let { location ->

            val results = FloatArray(1)

            Location.distanceBetween(
                location.latitude,
                location.longitude,
                place.lat,
                place.lng,
                results
            )

            results[0] <= 50000

        } ?: true

        categoryMatch && searchMatch && nearbyMatch
    }

    val bgGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFFF8E8),
            Color(0xFFE8F5E9)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgGradient)
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },

            placeholder = {
                Text(stringResource(R.string.search_hint))
            },

            leadingIcon = {
                Icon(Icons.Filled.Search, null)
            },

            modifier = Modifier.fillMaxWidth(),

            singleLine = true,

            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            categories.forEach { c ->

                FilterChip(
                    selected = selected == c,

                    onClick = {
                        selected = c
                    },

                    label = {
                        Text(c)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(filtered) { place ->

                PlaceCard(
                    p = place,
                    currentLocation = currentLocation
                )
            }
        }
    }
}

@Composable
private fun PlaceCard(
    p: Place,
    currentLocation: Location?
) {

    val ctx = LocalContext.current

    var distanceText = ""

    currentLocation?.let {

        val results = FloatArray(1)

        Location.distanceBetween(
            it.latitude,
            it.longitude,
            p.lat,
            p.lng,
            results
        )

        val km = results[0] / 1000

        distanceText =
            String.format("%.1f km away", km)
    }

    ElevatedCard(
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = p.name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    Icons.Filled.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(" ${p.rating}")
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = p.category,
                style = MaterialTheme.typography.labelMedium,
                color = Color(0xFF2E7D32)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = p.description,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            if (distanceText.isNotBlank()) {

                Text(
                    text = distanceText,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {

                    val uri = Uri.parse(
                        "geo:${p.lat},${p.lng}?q=${Uri.encode(p.name)}"
                    )

                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        uri
                    ).apply {
                        setPackage("com.google.android.apps.maps")
                    }

                    ctx.startActivity(intent)
                },

                shape = RoundedCornerShape(12.dp)
            ) {

                Icon(
                    Icons.Filled.Map,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    stringResource(R.string.open_in_maps)
                )
            }
        }
    }
}