//AhorcadoScreen.kt
package com.edwindiaz.myapplication.features.ahorcado.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edwindiaz.myapplication.features.ahorcado.presentation.viewmodels.AhorcadoViewModel

@Composable
fun AhorcadoScreen(viewModel: AhorcadoViewModel = viewModel()) {
    val palabraSecreta by viewModel.palabraSecreta.collectAsStateWithLifecycle()
    val letrasAdivinadas by viewModel.letrasAdivinadas.collectAsStateWithLifecycle()
    val letraIngresada by viewModel.letraIngresada.collectAsStateWithLifecycle()
    val intentosRestantes by viewModel.intentosRestantes.collectAsStateWithLifecycle()
    val mensaje by viewModel.mensaje.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "JUEGO DEL AHORCADO",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Intentos restantes",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = intentosRestantes.toString(),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (intentosRestantes > 3) Color.Green else Color.Red
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Palabra a adivinar",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    palabraSecreta.forEach { letra ->
                        val esVisible = letrasAdivinadas.contains(letra) || letra == ' '

                        Card(
                            modifier = Modifier
                                .width(40.dp)
                                .height(50.dp),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                if (esVisible) {
                                    Text(
                                        text = letra.toString(),
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                } else {
                                    Text(
                                        text = "_",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Letras adivinadas",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = letrasAdivinadas.sorted().joinToString(" "),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = letraIngresada,
            onValueChange = { viewModel.actualizarLetra(it) },
            placeholder = { Text("Ingresa una letra") },
            singleLine = true
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.intentarAdivinar() }
            ) {
                Text("Adivinar")
            }

            Button(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.reiniciarJuego() }
            ) {
                Text("Reiniciar")
            }
        }

        if (mensaje.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (mensaje.contains("¡Ganaste!") || mensaje.contains("¡Correcto!")) {
                            Color.Green.copy(alpha = 0.2f)
                        } else if (mensaje.contains("¡Perdiste!") || mensaje.contains("Incorrecto")) {
                            Color.Red.copy(alpha = 0.2f)
                        } else {
                            Color.Blue.copy(alpha = 0.2f)
                        }
                    ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = mensaje,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AhorcadoScreenPreview() {
    AhorcadoScreen()
}