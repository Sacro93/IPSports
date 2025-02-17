package com.example.ipsports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ipsports.View.Reusable.ButtonPrimary
import com.example.ipsports.View.theme.Color.IpSportsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IpSportsTheme {

                ButtonPrimary(
                    text = "Iniciar Sesión",
                    onClick = {}
                )

                }
            }
        }
    }


