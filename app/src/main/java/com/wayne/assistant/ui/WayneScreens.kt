package com.wayne.assistant.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val WayneBlack = Color(0xFF050505)
private val WaynePanel = Color(0xFF101010)
private val WayneGold = Color(0xFFFFC857)
private val WayneAmber = Color(0xFFFFA000)
private val WayneText = Color(0xFFF5F5F5)
private val WayneMuted = Color(0xFF9E9E9E)

@Composable
fun WayneApp() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        containerColor = WayneBlack,
        bottomBar = {
            WayneBottomBar(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF020202), Color(0xFF12100A), Color(0xFF050505))
                    )
                )
        ) {
            when (selectedTab) {
                0 -> HomeScreen()
                1 -> ChatScreen()
                2 -> MemoryScreen()
                3 -> GlassesScreen()
                4 -> SettingsScreen()
            }
        }
    }
}

@Composable
private fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(14.dp))
            Text("W.A.Y.N.E", color = WayneGold, fontSize = 34.sp, fontWeight = FontWeight.Bold)
            Text("Wireless AI Your Neural Engine", color = WayneMuted, fontSize = 14.sp)
        }
        item { HudCore() }
        item {
            WayneCard(title = "SYSTEM STATUS", icon = Icons.Default.Memory) {
                StatusRow("Neural Engine", "Online")
                StatusRow("Wake Word", "Listening")
                StatusRow("Memory Bank", "Ready")
                StatusRow("Meta Glasses", "Awaiting Bluetooth")
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                MetricTile("AUTO", "Memory", Modifier.weight(1f))
                MetricTile("GROK", "AI Core", Modifier.weight(1f))
                MetricTile("HUD", "Active", Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun ChatScreen() {
    var text by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf(
            "W.A.Y.N.E online. How can I assist you, Zack?",
            "Voice, memory, glasses, and chat modules are ready."
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("CHAT", color = WayneGold, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text("Talk or type directly to W.A.Y.N.E", color = WayneMuted)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(messages) { msg -> ChatBubble(msg) }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Message W.A.Y.N.E") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = WayneText,
                    unfocusedTextColor = WayneText,
                    focusedBorderColor = WayneGold,
                    unfocusedBorderColor = Color.DarkGray,
                    cursorColor = WayneGold
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = {
                if (text.isNotBlank()) {
                    messages.add(text)
                    text = ""
                }
            }) {
                Icon(Icons.Default.Send, contentDescription = null, tint = WayneGold)
            }
        }
    }
}

@Composable
private fun MemoryScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item {
            Text("MEMORY BANK", color = WayneGold, fontSize = 26.sp, fontWeight = FontWeight.Bold)
            Text("Automatic and manual knowledge storage", color = WayneMuted)
        }
        item { MemoryCard("AUTO MEMORY", "Learns facts from conversations automatically.", Icons.Default.AutoAwesome) }
        item { MemoryCard("MANUAL MEMORY", "Save important facts you specifically tell W.A.Y.N.E to remember.", Icons.Default.Bookmark) }
        item { MemoryCard("WORK KNOWLEDGE", "Routes, truck numbers, time conversions, dispatch patterns, and personal workflows.", Icons.Default.Work) }
    }
}

@Composable
private fun GlassesScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item {
            Text("META GLASSES", color = WayneGold, fontSize = 26.sp, fontWeight = FontWeight.Bold)
            Text("Bluetooth audio routing and hands-free assistant mode", color = WayneMuted)
        }
        item {
            WayneCard(title = "CONNECTION", icon = Icons.Default.Bluetooth) {
                StatusRow("Ray-Ban Meta", "Not Connected")
                StatusRow("Audio Route", "Phone Speaker")
                StatusRow("Mic Access", "Permission Required")
            }
        }
        item {
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = WayneGold, contentColor = Color.Black),
                shape = RoundedCornerShape(16.dp)
            ) { Text("SCAN FOR GLASSES", fontWeight = FontWeight.Bold) }
        }
    }
}

@Composable
private fun SettingsScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item {
            Text("SETTINGS", color = WayneGold, fontSize = 26.sp, fontWeight = FontWeight.Bold)
            Text("Configure W.A.Y.N.E systems", color = WayneMuted)
        }
        item { SettingRow("Wake Word", "Hey Wayne", Icons.Default.RecordVoiceOver) }
        item { SettingRow("AI Provider", "Grok / xAI", Icons.Default.Psychology) }
        item { SettingRow("Background Mode", "Enabled", Icons.Default.PowerSettingsNew) }
        item { SettingRow("Theme", "Black + Gold HUD", Icons.Default.Palette) }
    }
}

@Composable
private fun HudCore() {
    val infinite = rememberInfiniteTransition(label = "hud")
    val rotation by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(animation = tween(6500, easing = LinearEasing)),
        label = "rotation"
    )

    Box(modifier = Modifier.size(245.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize().rotate(rotation)) {
            val stroke = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Round)
            drawCircle(color = WayneGold.copy(alpha = .18f), style = Stroke(width = 2.dp.toPx()))
            drawArc(color = WayneGold, startAngle = 20f, sweepAngle = 95f, useCenter = false, style = stroke)
            drawArc(color = WayneAmber, startAngle = 170f, sweepAngle = 65f, useCenter = false, style = stroke)
            drawArc(color = WayneGold.copy(alpha = .55f), startAngle = 285f, sweepAngle = 40f, useCenter = false, style = stroke)
            drawLine(WayneGold.copy(alpha = .45f), Offset(size.width / 2, 0f), Offset(size.width / 2, 36f), strokeWidth = 3.dp.toPx())
            drawLine(WayneGold.copy(alpha = .45f), Offset(size.width / 2, size.height), Offset(size.width / 2, size.height - 36f), strokeWidth = 3.dp.toPx())
        }
        Box(modifier = Modifier.size(128.dp).blur(18.dp).background(WayneGold.copy(alpha = .12f), CircleShape))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = WayneGold, modifier = Modifier.size(44.dp))
            Text("ONLINE", color = WayneGold, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("HUD CORE", color = WayneMuted, fontSize = 12.sp)
        }
    }
}

@Composable
private fun WayneBottomBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf(
        Icons.Default.Home to "Home",
        Icons.Default.Chat to "Chat",
        Icons.Default.Memory to "Memory",
        Icons.Default.Smartphone to "Glasses",
        Icons.Default.Settings to "Settings"
    )
    NavigationBar(containerColor = Color(0xFF080808), tonalElevation = 0.dp) {
        tabs.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                icon = { Icon(item.first, contentDescription = item.second) },
                label = { Text(item.second, fontSize = 10.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = WayneGold,
                    selectedTextColor = WayneGold,
                    unselectedIconColor = WayneMuted,
                    unselectedTextColor = WayneMuted,
                    indicatorColor = WayneGold.copy(alpha = .12f)
                )
            )
        }
    }
}

@Composable
private fun WayneCard(title: String, icon: ImageVector, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(WaynePanel.copy(alpha = .94f), RoundedCornerShape(22.dp))
            .border(1.dp, WayneGold.copy(alpha = .25f), RoundedCornerShape(22.dp))
            .padding(18.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = WayneGold)
            Spacer(modifier = Modifier.width(10.dp))
            Text(title, color = WayneGold, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(14.dp))
        content()
    }
}

@Composable
private fun StatusRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = WayneMuted)
        Text(value, color = WayneText, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun MetricTile(value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(WaynePanel, RoundedCornerShape(18.dp))
            .border(1.dp, WayneGold.copy(alpha = .18f), RoundedCornerShape(18.dp))
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(value, color = WayneGold, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(label, color = WayneMuted, fontSize = 12.sp, textAlign = TextAlign.Center)
    }
}

@Composable
private fun ChatBubble(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(WaynePanel, RoundedCornerShape(18.dp))
            .border(1.dp, WayneGold.copy(alpha = .14f), RoundedCornerShape(18.dp))
            .padding(14.dp)
    ) {
        Text(message, color = WayneText)
    }
}

@Composable
private fun MemoryCard(title: String, body: String, icon: ImageVector) {
    WayneCard(title = title, icon = icon) {
        Text(body, color = WayneText, lineHeight = 20.sp)
    }
}

@Composable
private fun SettingRow(title: String, value: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(WaynePanel, RoundedCornerShape(18.dp))
            .border(1.dp, WayneGold.copy(alpha = .18f), RoundedCornerShape(18.dp))
            .clickable { }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = WayneGold)
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = WayneText, fontWeight = FontWeight.SemiBold)
            Text(value, color = WayneMuted, fontSize = 13.sp)
        }
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = WayneMuted)
    }
}
