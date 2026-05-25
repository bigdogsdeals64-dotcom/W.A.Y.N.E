package com.wayne.assistant.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ForcedWayneHudApp()
        }
    }
}

private val HudBlack = Color(0xFF030303)
private val HudPanel = Color(0xFF101010)
private val HudGold = Color(0xFFFFC857)
private val HudAmber = Color(0xFFFFA000)
private val HudText = Color(0xFFF5F5F5)
private val HudMuted = Color(0xFF9E9E9E)

@Composable
private fun ForcedWayneHudApp() {
    Scaffold(
        containerColor = HudBlack,
        bottomBar = { HudBottomBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF010101), Color(0xFF151107), Color(0xFF030303))
                    )
                )
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text("W.A.Y.N.E", color = HudGold, fontSize = 36.sp, fontWeight = FontWeight.Black, letterSpacing = 3.sp)
            Text("Wireless AI Your Neural Engine", color = HudMuted, fontSize = 14.sp)
            HudCore()
            HudCard("SYSTEM STATUS") {
                HudRow("Neural Engine", "ONLINE")
                HudRow("Interface", "HUD ACTIVE")
                HudRow("Memory Bank", "READY")
                HudRow("Meta Glasses", "STANDBY")
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                HudTile("AI", "CORE", Modifier.weight(1f))
                HudTile("VOICE", "READY", Modifier.weight(1f))
                HudTile("HUD", "LIVE", Modifier.weight(1f))
            }
            HudCard("CONFIRMATION") {
                Text(
                    "If you can see this black and gold HUD screen, the APK is finally running the new W.A.Y.N.E activity instead of the old purple setup screen.",
                    color = HudText,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
private fun HudCore() {
    val infinite = rememberInfiniteTransition(label = "forced_hud_core")
    val rotation by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(6000, easing = LinearEasing)),
        label = "rotation"
    )
    Box(modifier = Modifier.size(220.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize().rotate(rotation)) {
            val stroke = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Round)
            drawCircle(HudGold.copy(alpha = .20f), style = Stroke(width = 2.dp.toPx()))
            drawArc(HudGold, 20f, 110f, false, style = stroke)
            drawArc(HudAmber, 180f, 75f, false, style = stroke)
            drawArc(HudGold.copy(alpha = .55f), 290f, 45f, false, style = stroke)
            drawLine(HudGold.copy(alpha = .5f), Offset(size.width / 2, 0f), Offset(size.width / 2, 35f), strokeWidth = 3.dp.toPx())
        }
        Box(modifier = Modifier.size(118.dp).background(HudGold.copy(alpha = .12f), CircleShape))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = HudGold, modifier = Modifier.size(48.dp))
            Text("ONLINE", color = HudGold, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("HUD CORE", color = HudMuted, fontSize = 12.sp)
        }
    }
}

@Composable
private fun HudCard(title: String, content: @Composable Column.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().border(1.dp, HudGold.copy(alpha = .25f), RoundedCornerShape(22.dp)),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = HudPanel.copy(alpha = .95f))
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(title, color = HudGold, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
private fun HudRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = HudMuted)
        Text(value, color = HudText, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun HudTile(value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(HudPanel, RoundedCornerShape(18.dp)).border(1.dp, HudGold.copy(alpha = .2f), RoundedCornerShape(18.dp)).padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(value, color = HudGold, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(label, color = HudMuted, fontSize = 11.sp, textAlign = TextAlign.Center)
    }
}

@Composable
private fun HudBottomBar() {
    NavigationBar(containerColor = Color(0xFF070707)) {
        val items = listOf(Icons.Default.Home, Icons.Default.Chat, Icons.Default.Memory, Icons.Default.Smartphone, Icons.Default.Settings)
        items.forEachIndexed { index, icon ->
            NavigationBarItem(
                selected = index == 0,
                onClick = {},
                icon = { Icon(icon, contentDescription = null) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = HudGold,
                    unselectedIconColor = HudMuted,
                    indicatorColor = HudGold.copy(alpha = .12f)
                )
            )
        }
    }
}
