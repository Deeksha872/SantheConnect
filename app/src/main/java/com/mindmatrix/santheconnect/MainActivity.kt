package com.mindmatrix.santheconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mindmatrix.santheconnect.ui.theme.SantheConnectTheme
import com.mindmatrix.santheconnect.navigation.SantheNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SantheConnectTheme {
                SantheNavGraph()
            }
        }
    }
}
