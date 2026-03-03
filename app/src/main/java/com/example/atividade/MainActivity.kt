package com.example.atividade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Home()
        }
    }
}

@Composable
fun Home() {

    var precoAlcool by remember { mutableStateOf("") }
    var precoGasolina by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Validador de tipo de Combustível",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))
        /// Caixa de insert
        OutlinedTextField(
            value = precoAlcool, /// Variavel que vai receber o insert
            onValueChange = { precoAlcool = it },
            label = { Text("Preço do Etanol (R$/litro)") }
        )

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = precoGasolina,
            onValueChange = { precoGasolina = it },
            label = { Text("Preço da Gasolina (R$/litro)") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            /// Validações de insert
            if (precoAlcool.isEmpty() || precoGasolina.isEmpty()) {
                resultado = "Preencha todos os campos"
                return@Button
            }
            /// Transformar as variaveis para o calculo
            val pAlcool = precoAlcool.toDoubleOrNull()
            val pGasolina = precoGasolina.toDoubleOrNull()

            if (pAlcool == null || pGasolina == null) {
                resultado = "Digite valores válidos"
                return@Button
            }
            /// Validações lógicas
            if (pAlcool <= 0 || pGasolina <= 0) {
                resultado = "Preços devem ser maiores que zero"
                return@Button
            }
            /// Calculo real
            val indice = pAlcool / pGasolina

            resultado = if (indice < 0.70) {
                "ETANOL é mais vantajoso"
            } else {
                "GASOLINA é mais vantajosa"
            }

        }) {
            Text(
                text = "CALCULAR",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = resultado,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )
    }
}