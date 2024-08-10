package com.example.mtapp

import androidx.compose.material3.Surface
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.compose.foundation.layout.fillMaxSize
import com.example.mtapp.ui.theme.MTAPPTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import androidx.compose.ui.Modifier
import java.text.NumberFormat


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.mtapp", appContext.packageName)
    }
}

//class TipUITests {
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun calculate_20_percent_tip() {
//        composeTestRule.setContent() {
//            Surface(modifier = Modifier.fillMaxSize()) {
//                MTAPPTheme {
//                    TipTimeLayout()
//                }
//            }
//        }
//        composeTestRule.onNodeWithText("Bill Amount").performTextInput("10")
//        composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")
//        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
//        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
//            "No node with this text was found."
//        )
//
//    }
//}

