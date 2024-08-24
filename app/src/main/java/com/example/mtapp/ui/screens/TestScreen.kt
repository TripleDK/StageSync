package com.example.mtapp.ui.screens

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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mtapp.Models.AudioObject
import com.example.mtapp.Models.Song
import com.example.mtapp.R
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun FullWidthBarAndCenteredContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar - stretches full width
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp) // Adjust height as needed
                .background(Color.Gray) // Adjust color as needed
        )

        Spacer(modifier = Modifier.height(8.dp)) // Space between the bar and the centered element

        // Centered Element below the bar
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
                .background(Color.Blue) // Adjust color as needed
                .padding(16.dp) // Adjust padding as needed
        ) {
            // Content for the centered element
            // Example: Text("Centered Element")
        }

        Spacer(modifier = Modifier.height(16.dp)) // Space before the five elements

        // Five elements
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp) // Adjust height as needed
        ) {
            // Left Box aligned to the start
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
                    .align(Alignment.CenterStart)
            )

            // Right Box aligned to the end
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color.Magenta)
                    .align(Alignment.CenterEnd)
            )

            // Middle Boxes centered horizontally
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .wrapContentWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Second Box
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.Green)
                )

                // Third Box (centered)
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Blue)
                )

                // Fourth Box
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Yellow)
                )
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 200)
@Composable
fun FullWidthBarAndCenteredContentPreview() {
    FullWidthBarAndCenteredContent()
}