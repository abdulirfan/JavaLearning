package org.alifoundation.app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Green = Color(0xFF245C49)
private val Ink = Color(0xFF1A2D27)
private val Muted = Color(0xFF64736D)
private val Sand = Color(0xFFF5F3EC)
private val White = Color(0xFFFFFEFA)
private val Gold = Color(0xFFE7C982)
private const val SITE = "https://ali-foundation.org/"
private const val ABOUT = "https://ali-foundation.org/about/"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = lightColorScheme(
                primary = Green,
                background = Sand,
                surface = White,
                onSurface = Ink,
                onBackground = Ink
            )) {
                AliFoundationApp()
            }
        }
    }
}

private enum class Tab(val label: String, val icon: ImageVector) {
    Home("Home", Icons.Filled.Home),
    Programs("Programs", Icons.Filled.MenuBook),
    Support("Support", Icons.Filled.VolunteerActivism),
    About("About", Icons.Filled.Info)
}

@Composable
private fun AliFoundationApp() {
    var current by remember { mutableStateOf(Tab.Home) }
    Scaffold(
        containerColor = Sand,
        bottomBar = {
            NavigationBar(containerColor = White) {
                Tab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = current == tab,
                        onClick = { current = tab },
                        icon = { Icon(tab.icon, contentDescription = tab.label) },
                        label = { Text(tab.label, fontSize = 11.sp) }
                    )
                }
            }
        }
    ) { inset ->
        Box(Modifier.fillMaxSize().padding(inset)) {
            when (current) {
                Tab.Home -> HomeScreen(onExplore = { current = Tab.Programs })
                Tab.Programs -> ProgramsScreen()
                Tab.Support -> SupportScreen()
                Tab.About -> AboutScreen()
            }
        }
    }
}

@Composable
private fun Page(content: @Composable () -> Unit) {
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BrandHeader()
        content()
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun BrandHeader() {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(
            Modifier.size(48.dp).clip(RoundedCornerShape(15.dp)).background(Green),
            contentAlignment = Alignment.Center
        ) {
            Text("A", color = Color.White, fontSize = 29.sp, fontWeight = FontWeight.ExtraBold)
        }
        Column {
            Text("ALI FOUNDATION", color = Ink, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 1.sp)
            Text("Caring is sharing", color = Muted, fontSize = 13.sp)
        }
    }
}

@Composable
private fun HomeScreen(onExplore: () -> Unit) {
    val context = LocalContext.current
    Page {
        Box(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(26.dp))
                .background(Brush.linearGradient(listOf(Color(0xFF173F35), Green, Color(0xFF397B5D))))
                .padding(24.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Text("CARING IS SHARING", color = Gold, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
                Text("Education opens doors to a better future.", color = Color.White, fontSize = 29.sp, lineHeight = 36.sp, fontWeight = FontWeight.Bold)
                Text(
                    "Supporting young people through education, opportunity and civic leadership.",
                    color = Color(0xFFE4EEE8), fontSize = 15.sp, lineHeight = 23.sp
                )
                Button(
                    onClick = onExplore,
                    colors = ButtonDefaults.buttonColors(containerColor = Gold, contentColor = Ink),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Explore our programs", fontWeight = FontWeight.Bold) }
            }
        }
        Text("What we work toward", color = Ink, fontSize = 23.sp, fontWeight = FontWeight.Bold)
        Text("Opportunity should not depend on background.", color = Muted, fontSize = 14.sp)
        Feature(Icons.Filled.School, "Education access", "Helping young people access learning and build stronger futures.")
        Feature(Icons.Filled.Groups, "Civic leadership", "Developing leadership skills and community participation.")
        Feature(Icons.Filled.FavoriteBorder, "Fairness and inclusion", "Promoting respect, equal opportunity and collaboration.")
        Card(colors = CardDefaults.cardColors(containerColor = White), shape = RoundedCornerShape(20.dp)) {
            Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Be part of the change", color = Ink, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Explore the Foundation's work and confirm current opportunities through the official website.", color = Muted, fontSize = 14.sp, lineHeight = 21.sp)
                OutlinedButton(onClick = { openSite(context, SITE) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Visit official website")
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.Filled.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
private fun ProgramsScreen() {
    Page {
        Text("Our programs", color = Ink, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text("Check the official website for current program details and eligibility.", color = Muted, fontSize = 14.sp, lineHeight = 21.sp)
        Program(Icons.Filled.School, "01", "Merit scholarships", "Educational opportunities and support for students.")
        Program(Icons.Filled.Groups, "02", "Civic leadership", "Skills and opportunities for constructive community participation.")
        Program(Icons.Filled.VolunteerActivism, "03", "Community outreach", "Connecting people and partners to widen access to opportunity.")
        Program(Icons.Filled.MenuBook, "04", "Financial literacy", "Practical knowledge to support informed financial decisions.")
    }
}

@Composable
private fun SupportScreen() {
    val context = LocalContext.current
    Page {
        Text("Get involved", color = Ink, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text("For scholarships, volunteering, partnerships or contributions, use the Foundation's official channels.", color = Muted, fontSize = 15.sp, lineHeight = 22.sp)
        Action("Education opportunities", "Check the official website for scholarship updates and eligibility.", "Visit official site") { openSite(context, ABOUT) }
        Action("Partner or volunteer", "Find current information about community outreach and leadership initiatives.", "Learn more") { openSite(context, SITE) }
        Action("Support the mission", "Confirm official donation methods directly. This app does not process payments.", "Official website") { openSite(context, SITE) }
    }
}

@Composable
private fun AboutScreen() {
    val context = LocalContext.current
    Page {
        Text("About Ali Foundation", color = Ink, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(
            "The education-focused Ali Foundation works to widen access to education, encourage leadership and support community opportunity.",
            color = Ink, fontSize = 16.sp, lineHeight = 25.sp
        )
        Card(colors = CardDefaults.cardColors(containerColor = White), shape = RoundedCornerShape(20.dp)) {
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Our values", color = Ink, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                listOf("Equal opportunity", "Accessibility and fairness", "Respect across cultures", "Community involvement", "Integrity and collaboration").forEach {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Box(Modifier.size(7.dp).clip(CircleShape).background(Gold))
                        Text(it, color = Muted, fontSize = 14.sp)
                    }
                }
            }
        }
        Text("Confirm this is the intended Ali Foundation before publishing. Program details and contact information should be verified against official sources.", color = Muted, fontSize = 12.sp, lineHeight = 18.sp)
        Button(onClick = { openSite(context, ABOUT) }, modifier = Modifier.fillMaxWidth()) {
            Text("Read the full story")
            Spacer(Modifier.width(8.dp))
            Icon(Icons.Filled.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun Feature(icon: ImageVector, title: String, detail: String) {
    Row(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(17.dp)).background(White).padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(13.dp), verticalAlignment = Alignment.Top
    ) {
        Box(Modifier.size(42.dp).clip(CircleShape).background(Color(0xFFE8F0E9)), contentAlignment = Alignment.Center) {
            Icon(icon, contentDescription = null, tint = Green, modifier = Modifier.size(22.dp))
        }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(title, color = Ink, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(detail, color = Muted, fontSize = 13.sp, lineHeight = 19.sp)
        }
    }
}

@Composable
private fun Program(icon: ImageVector, number: String, title: String, detail: String) {
    Card(colors = CardDefaults.cardColors(containerColor = White), shape = RoundedCornerShape(19.dp)) {
        Row(Modifier.fillMaxWidth().padding(17.dp), horizontalArrangement = Arrangement.spacedBy(13.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = Green, modifier = Modifier.size(30.dp))
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(number, color = Green, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Text(title, color = Ink, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Text(detail, color = Muted, fontSize = 14.sp, lineHeight = 20.sp)
            }
        }
    }
}

@Composable
private fun Action(title: String, detail: String, label: String, onClick: () -> Unit) {
    Card(colors = CardDefaults.cardColors(containerColor = White), shape = RoundedCornerShape(19.dp)) {
        Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(title, color = Ink, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(detail, color = Muted, fontSize = 14.sp, lineHeight = 21.sp)
            OutlinedButton(onClick = onClick) {
                Text(label)
                Spacer(Modifier.width(8.dp))
                Icon(Icons.Filled.OpenInNew, contentDescription = null, modifier = Modifier.size(15.dp))
            }
        }
    }
}

private fun openSite(context: Context, url: String) {
    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}
