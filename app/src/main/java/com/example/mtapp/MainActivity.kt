package com.example.mtapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import com.example.mtapp.ui.StageSyncApp
import com.example.mtapp.ui.theme.MTAPPTheme

private const val TAG = "MyMainActivity"

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate Called")
        setContent {
            MTAPPTheme {
                val layoutDirection = LocalLayoutDirection.current
                Surface(
                    modifier = Modifier
//                        .padding(
//                            start = WindowInsets.safeDrawing.asPaddingValues()
//                                .calculateStartPadding(layoutDirection),
//                            end = WindowInsets.safeDrawing.asPaddingValues()
//                                .calculateEndPadding(layoutDirection)

                        //  )
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize = calculateWindowSizeClass(this)
                    StageSyncApp(
                        // windowSize = windowSize.widthSizeClass
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StageSyncAppCompactPreview() {
    MTAPPTheme {
        Surface {
            StageSyncApp(
                //windowSize = WindowWidthSizeClass.Compact
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun StageSyncAppMediumPreview() {
    MTAPPTheme {
        Surface {
            StageSyncApp(
                //windowSize = WindowWidthSizeClass.Medium
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun StageSyncAppExpandedPreview() {
    MTAPPTheme {
        Surface {
            StageSyncApp(
                //    windowSize = WindowWidthSizeClass.Expanded
            )
        }
    }
}