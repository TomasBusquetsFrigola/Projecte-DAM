package com.tomasbusfri.files.appcasino.UI.Screens.Interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tomasbusfri.files.appcasino.UI.Colors.Colors

@Composable
fun MainScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Colors.CASINO_TABLE_GREEN)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .border(3.dp, Colors.METALLIC_GOLD)
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "WELCOME",
                color  = Colors.METALLIC_GOLD,
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .border(3.dp, Colors.METALLIC_GOLD)
                .fillMaxWidth()
                .size(800.dp)
        ) {
            Text(
                "Solitaire"
            )
            Text(
                "Black Jack"
            )
            Text(
                "Poker"
            )
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}