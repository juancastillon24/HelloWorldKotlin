package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class PruebaCompose : ComponentActivity() {
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

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                // Se asigna el host del Snackbar
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
            ) { paddingValues -> // 4. Recibir los PaddingValues del Scaffold
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues) // Aplicar el padding obligatorio
                        .padding(horizontal = 30.dp)
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
                            .padding(16.dp),
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
                }
            }
        }
    }
}