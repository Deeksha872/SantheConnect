package com.mindmatrix.santheconnect.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.*
import com.mindmatrix.santheconnect.R
import com.mindmatrix.santheconnect.ui.screens.*

sealed class Dest(
    val route: String,
    val labelRes: Int,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {

    data object Home :
        Dest("home", R.string.home, Icons.Filled.Home)

    data object Nearby :
        Dest("nearby", R.string.nearby, Icons.Filled.Place)

    data object Calendar :
        Dest("calendar", R.string.calendar, Icons.Filled.DateRange)

    data object Wall :
        Dest("wall", R.string.wall, Icons.Filled.Forum)

    data object Feedback :
        Dest("feedback", R.string.feedback, Icons.Filled.Feedback)
}

private val items = listOf(
    Dest.Home,
    Dest.Nearby,
    Dest.Calendar,
    Dest.Wall,
    Dest.Feedback
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SantheNavGraph() {

    val navController = rememberNavController()

    Scaffold(

        bottomBar = {

            NavigationBar {

                val currentDest =
                    navController.currentBackStackEntryAsState().value?.destination

                items.forEach { d ->

                    NavigationBarItem(

                        selected = when (d.route) {

                            Dest.Nearby.route -> {
                                currentDest?.route?.startsWith("nearby") == true
                            }

                            else -> {
                                currentDest?.hierarchy?.any {
                                    it.route == d.route
                                } == true
                            }
                        },

                        onClick = {

                            navController.navigate(d.route) {

                                popUpTo(navController.graph.startDestinationId)

                                launchSingleTop = true
                            }
                        },

                        icon = {
                            Icon(
                                imageVector = d.icon,
                                contentDescription = null
                            )
                        },

                        label = {
                            Text(
                                text = stringResource(d.labelRes)
                            )
                        }
                    )
                }
            }
        }

    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Dest.Home.route,
            modifier = Modifier.padding(padding)
        ) {

            composable(Dest.Home.route) {

                HomeScreen(
                    onCategoryClick = { category ->

                        navController.navigate(
                            "nearby/$category"
                        )
                    }
                )
            }

            composable("nearby/{category}") { backStackEntry ->

                val category =
                    backStackEntry.arguments
                        ?.getString("category")
                        ?: "All"

                NearbyScreen(
                    selectedCategory = category
                )
            }

            composable(Dest.Nearby.route) {

                NearbyScreen(
                    selectedCategory = "All"
                )
            }

            composable(Dest.Calendar.route) {

                CalendarScreen()
            }

            composable(Dest.Wall.route) {

                WallScreen()
            }

            composable(Dest.Feedback.route) {

                FeedbackScreen()
            }
        }
    }
}