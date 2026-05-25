package com.wayne.assistant.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WayneApp(viewModel: WayneViewModel) {
    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF020617), Color(0xFF0F172A), Color(0xFF000000))
                )
            )
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "W.A.Y.N.E.",
                color = Color(0xFF38BDF8),
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Wireless AI Your Neural Engine",
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(28.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.08f))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Status", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(state.statusText, color = Color.White.copy(alpha = 0.85f))
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = state.apiKey,
                        onValueChange = viewModel::updateApiKey,
                        label = { Text("Grok API Key") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = viewModel::saveApiKey,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save API Key")
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.08f))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Wake Word", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = state.wakeWord,
                        onValueChange = viewModel::updateWakeWord,
                        label = { Text("Wake word") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = viewModel::saveWakeWord,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save Wake Word")
                    }
                }
            }
        }
    }
}
