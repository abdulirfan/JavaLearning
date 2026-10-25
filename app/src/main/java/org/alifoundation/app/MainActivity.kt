package org.alifoundation.app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
        Image(
            painter = painterResource(R.drawable.ali_foundation_logo),
            contentDescription = "Ali Foundation official logo",
            modifier = Modifier.width(54.dp).height(60.dp),
            contentScale = ContentScale.Fit
        )
        Column {
            Text("ALI FOUNDATION", color = Ink, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 1.sp)
            Text("Together we move, together we grow", color = Muted, fontSize = 11.sp)
        }
    }
}

@Composable
private fun HomeScreen(onExplore: () -> Unit) {
    val context = LocalContext.current
    Page {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(R.drawable.ali_foundation_logo),
                contentDescription = "Ali Foundation emblem and motto",
                modifier = Modifier.width(180.dp).height(200.dp),
                contentScale = ContentScale.Fit
            )
        }
        Box(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(26.dp))
                .background(Brush.linearGradient(listOf(Color(0xFF173F35), Green, Color(0xFF397B5D))))
                .padding(24.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Text("SERVICE • PROGRESS • DEVELOPMENT", color = Gold, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                Text("Together we move, together we grow.", color = Color.White, fontSize = 28.sp, lineHeight = 35.sp, fontWeight = FontWeight.Bold)
                Text(
                    "Working together to strengthen education, health, welfare and society.",
                    color = Color(0xFFE4EEE8), fontSize = 15.sp, lineHeight = 23.sp
                )
                Button(
                    onClick = onExplore,
                    colors = ButtonDefaults.buttonColors(containerColor = Gold, contentColor = Ink),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Explore our programs", fontWeight = FontWeight.Bold) }
            }
        }
        Text("Our focus areas", color = Ink, fontSize = 23.sp, fontWeight = FontWeight.Bold)
        Text("Community progress begins when people work together.", color = Muted, fontSize = 14.sp)
        Feature(Icons.Filled.School, "Education", "Supporting learning and opportunities for a stronger future.")
        Feature(Icons.Filled.FavoriteBorder, "Health and welfare", "Encouraging wellbeing, care and support for the community.")
        Feature(Icons.Filled.Groups, "Society and development", "Promoting unity, service, leadership and community participation.")
        Card(colors = CardDefaults.cardColors(containerColor = White), shape = RoundedCornerShape(20.dp)) {
            Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Be part of the change", color = Ink, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Explore the Foundation's current work and opportunities through its official website.", color = Muted, fontSize = 14.sp, lineHeight = 21.sp)
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
        Program(Icons.Filled.School, "01", "Education", "Learning opportunities and support for students.")
        Program(Icons.Filled.FavoriteBorder, "02", "Health and welfare", "Activities that support wellbeing and community care.")
        Program(Icons.Filled.Groups, "03", "Community development", "Connecting people and partners to strengthen local communities.")
        Program(Icons.Filled.VolunteerActivism, "04", "Service and outreach", "Encouraging people to contribute through volunteering and collaboration.")
    }
}

@Composable
private fun SupportScreen() {
    val context = LocalContext.current
    Page {
        Text("Get involved", color = Ink, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text("For volunteering, partnerships or contributions, use the Foundation's official channels.", color = Muted, fontSize = 15.sp, lineHeight = 22.sp)
        Action("Education and programs", "Check the official website for current program information and eligibility.", "Visit official site") { openSite(context, ABOUT) }
        Action("Partner or volunteer", "Find current information about outreach and community initiatives.", "Learn more") { openSite(context, SITE) }
        Action("Support the mission", "Confirm official contribution methods directly. This app does not process payments.", "Official website") { openSite(context, SITE) }
    }
}

@Composable
private fun AboutScreen() {
    val context = LocalContext.current
    Page {
        Text("About Ali Foundation", color = Ink, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(R.drawable.ali_foundation_logo),
                contentDescription = "Official Ali Foundation logo",
                modifier = Modifier.width(220.dp).height(244.dp),
                contentScale = ContentScale.Fit
            )
        }
        Text(
            "Together we move, together we grow. The Foundation's emblem reflects its stated values of unity, service, progress and development, with education, health, welfare and society at its core.",
            color = Ink, fontSize = 16.sp, lineHeight = 25.sp
        )
        Card(colors = CardDefaults.cardColors(containerColor = White), shape = RoundedCornerShape(20.dp)) {
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Our values", color = Ink, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                listOf("Unity", "Service", "Progress", "Development", "Education", "Health and welfare").forEach {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Box(Modifier.size(7.dp).clip(CircleShape).background(Gold))
                        Text(it, color = Muted, fontSize = 14.sp)
                    }
                }
            }
        }
        Text("Confirm current program details and contact information through official Foundation channels.", color = Muted, fontSize = 12.sp, lineHeight = 18.sp)
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
