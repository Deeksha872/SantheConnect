package com.mindmatrix.santheconnect.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mindmatrix.santheconnect.R
import com.mindmatrix.santheconnect.data.SampleRepo
import com.mindmatrix.santheconnect.data.TravelerPost

@Composable
fun FeedbackScreen() {

    val ctx = LocalContext.current

    var name by remember { mutableStateOf("") }
    var place by remember { mutableStateOf("") }
    var msg by remember { mutableStateOf("") }
    var rating by remember { mutableFloatStateOf(4f) }

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

        Text(
            text = stringResource(R.string.feedback),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = place,
                    onValueChange = { place = it },
                    label = { Text("Place") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = msg,
                    onValueChange = { msg = it },
                    label = { Text("Your feedback") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Rating: ${"%.1f".format(rating)}",
                    fontWeight = FontWeight.Medium
                )

                Slider(
                    value = rating,
                    onValueChange = { rating = it },
                    valueRange = 1f..5f,
                    steps = 7,
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF2E7D32),
                        activeTrackColor = Color(0xFF2E7D32)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {

                        SampleRepo.posts.add(
                            0,
                            TravelerPost(
                                id = System.currentTimeMillis().toString(),
                                author = name.ifBlank { "Traveler" },
                                place = place.ifBlank { "Unknown Place" },
                                message = msg
                            )
                        )

                        Toast.makeText(
                            ctx,
                            "Thank you, ${name.ifBlank { "Traveler" }}!",
                            Toast.LENGTH_SHORT
                        ).show()

                        name = ""
                        place = ""
                        msg = ""
                        rating = 4f
                    },

                    enabled = msg.isNotBlank(),

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(14.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2E7D32)
                    )
                ) {

                    Text(stringResource(R.string.submit))
                }
            }
        }
    }
}