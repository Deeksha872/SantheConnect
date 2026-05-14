package com.mindmatrix.santheconnect.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mindmatrix.santheconnect.R

@Composable
fun HomeScreen(onCategoryClick: (String) -> Unit) {

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
            .padding(20.dp)
    ) {

        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.tagline),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.DarkGray,
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CategoryCard(
                label = stringResource(R.string.food),
                icon = Icons.Filled.Restaurant,
                cardColor = Color(0xFFFFE0B2),
                modifier = Modifier.weight(1f)
            ) {
                onCategoryClick("Food")
            }

            CategoryCard(
                label = stringResource(R.string.markets),
                icon = Icons.Filled.Storefront,
                cardColor = Color(0xFFC8E6C9),
                modifier = Modifier.weight(1f)
            ) {
                onCategoryClick("Markets")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CategoryCard(
                label = stringResource(R.string.stays),
                icon = Icons.Filled.Bed,
                cardColor = Color(0xFFBBDEFB),
                modifier = Modifier.weight(1f)
            ) {
                onCategoryClick("Stays")
            }

            CategoryCard(
                label = stringResource(R.string.crafts),
                icon = Icons.Filled.Brush,
                cardColor = Color(0xFFF8BBD0),
                modifier = Modifier.weight(1f)
            ) {
                onCategoryClick("Crafts")
            }
        }
    }
}

@Composable
private fun CategoryCard(
    label: String,
    icon: ImageVector,
    cardColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    ElevatedCard(
        onClick = onClick,
        modifier = modifier
            .height(150.dp)
            .shadow(8.dp, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = cardColor
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF2E7D32),
                modifier = Modifier.size(42.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}