package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class PruebaCompose : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 1. Estado específico para el Snackbar en Material 3
            val snackbarHostState = remember { SnackbarHostState() }

            // 2. Scope de corrutinas para poder mostrar el Snackbar
            val scope = rememberCoroutineScope()

            // 3. Obtener el contexto de forma segura para Compose
            val context = LocalContext.current

            var textFieldState by remember { mutableStateOf("") }

            var sizeState by remember { mutableStateOf(400.dp) }

            val size by animateDpAsState(
                targetValue = sizeState,
                //Animacion que incrementa gradualmente hasta el valor indicado
                tween (
                    durationMillis = 3000,
                    delayMillis = 300,
                    easing = LinearOutSlowInEasing
                )
                //Animacion que incrementa gradualmente hasta el valor indicado,
                //pero rebota en el valor deseado
                /*
                spring(
                    Spring.DampingRatioHighBouncy
                )*/

                //Animacion que puede ajustar distintas velocidades
                //a medida que pasa el tiempo
                /*keyframes {
                    durationMillis = 5000
                    sizeState at 0 with LinearEasing
                    sizeState * 1.5f at 1000 with FastOutLinearInEasing
                    sizeState * 2f at 5000
                }*/
            )

            Scaffold(

                modifier = Modifier
                    .fillMaxSize(),
                // Se asigna el host del Snackbar
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                topBar = {
                    TopAppBar(
                        title = { Text("Prueba Compose") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    )
                }
            ) {
                paddingValues -> // 4. Recibir los PaddingValues del Scaffold
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues) // Aplicar el padding obligatorio
                        .padding(horizontal = 30.dp)
                        .background(Color.Blue)
                ) {
                    TextField(
                        value = textFieldState,
                        label = { Text("Enter your name") },
                        onValueChange = { textFieldState = it },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .border(5.dp, Color.Red, MaterialTheme.shapes.small)
                            .padding(8.dp),
                    ) {
                        Button(onClick = {
                            // Lanzar la corrutina para ejecutar la función suspendida
                            scope.launch {
                                snackbarHostState.showSnackbar("Hola $textFieldState")
                            }
                        }) {
                            Text("Saludo")
                        }

                        Spacer(modifier = Modifier.padding(16.dp))

                        Button(onClick = {
                            // Usar el contexto obtenido de LocalContext
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        }) {
                            Text("Volver")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    val infiniteTransition = rememberInfiniteTransition()
                    val color by infiniteTransition.animateColor(
                        initialValue = Color.Red,
                        targetValue = Color.Green,
                        animationSpec = infiniteRepeatable(
                            tween(durationMillis = 2000),
                            repeatMode = RepeatMode.Reverse
                        )
                    )

                    Box(
                        modifier = Modifier
                            .size(size)
                            .background(color),
                        contentAlignment = Alignment.Center
                    ) {
                        Column (
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Button(onClick = {
                                sizeState += 50.dp
                            }) {
                                Text("Incrementar")
                            }
                            Spacer(modifier = Modifier.padding(16.dp))
                            Button(onClick = {
                                sizeState -= 50.dp
                            }) {
                                Text("Reducir")
                            }
                        }
                    }
                    Button(
                        modifier = Modifier.padding(top = 20.dp),
                        onClick = {
                        val intent = Intent(context, PruebaCompose2::class.java)
                        context.startActivity(intent)
                    }) {
                        Text("Compose 2")
                    }

                }
            }
        }
    }
}