package com.app.hellojetpackcompose

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
            Todoo lo que se agregué en setContent se va
            a pintar en la activity
         */
        setContent {
            personalData("Cristian")
        }
    }

    /*
    Se deben declarar las funciones con @Composable,
    para que android sepa que es de Jetpack Compose
     */
    @Composable
    private fun personalData(name: String) {
        MaterialTheme() {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.coffee),
                    contentDescription = "Mi Avatar"
                    // Modifier.height(100.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Mi nombre es $name", style = MaterialTheme.typography.h6)
                Text(text = "Soy programador")
                Text(text = "Encuentrame")
            }
        }

    }

    @Preview
    @Composable
    fun previewPersonalData() {
        personalData(name = "Cristian Real A")
    }
}
