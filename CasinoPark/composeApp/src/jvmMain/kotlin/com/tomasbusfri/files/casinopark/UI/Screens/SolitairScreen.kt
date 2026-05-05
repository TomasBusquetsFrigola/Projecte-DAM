package com.tomasbusfri.files.casinopark.UI.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tomasbusfri.files.casinopark.UI.Colors.Colors

@Composable
fun SolitairScreen(
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Colors.CASINO_TABLE_GREEN
            )
            .padding(16.dp)
            .border(
                width = 3.dp,
                Colors.METALLIC_GOLD
            )
    ) {
        
    }
}

@Preview
@Composable
fun SolitairScreenPreview() {
    SolitairScreen()
}