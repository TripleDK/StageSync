package com.example.mtapp.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mtapp.R

@Composable
fun FormattedPriceLabel(subtotal: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.subtotal_price, subtotal),
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    optionsDisplayNames: List<Int?>,
    optionsValues: List<String?>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    require(optionsDisplayNames.size == optionsValues.size) { "Visible options and actual values must have the same size." }
    Log.v("test", "updating dropdownmenu")

    var expanded by remember { mutableStateOf(false) }
    val selectedOptionDisplayName =
        optionsDisplayNames.getOrNull(optionsValues.indexOf(selectedOption))

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .widthIn(min = 100.dp, max = 150.dp)
                .background(MaterialTheme.colorScheme.surface)
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)))
                .padding(start = 2.dp, end = 0.dp) // Padding for the text field
                .clip(RoundedCornerShape(4.dp))
                .menuAnchor()
        ) {
            Row(
                modifier = Modifier
                    // .background(Color.Yellow)
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = selectedOptionDisplayName?.let { stringResource(it) } ?: "",
                    onValueChange = {},
                    enabled = false,
                    singleLine = true,
                    interactionSource = remember { MutableInteractionSource() },
                    modifier = Modifier
                        // .weight(1f) // Take up remaining space
                        .padding(0.dp) // Padding inside the text field
                ) { innerTextField ->
                    TextFieldDefaults.DecorationBox(
                        value = selectedOptionDisplayName?.let { stringResource(it) } ?: "",
                        visualTransformation = VisualTransformation.None,
                        innerTextField = innerTextField,
                        singleLine = true,
                        enabled = false,
                        interactionSource = remember { MutableInteractionSource() },
                        contentPadding = PaddingValues(2.dp),

                        )
                }
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Dropdown",
                )
            }
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { /*TODO*/ }
        ) {
            optionsDisplayNames.forEachIndexed { index, optionDisplayName ->
                DropdownMenuItem(
                    onClick = {
                        optionsValues[index]?.let { onOptionSelected(it) }
                        expanded = false
                    },
                    text = { Text(stringResource(optionDisplayName!!)) })
            }
        }
    }
}


@Preview(showBackground = true, heightDp = 200)
@Composable
fun DropdownPreview() {
    DropdownMenu(
        optionsDisplayNames = listOf(1, 2),
        optionsValues = listOf("value", "value2"),
        selectedOption = "value",
        onOptionSelected = { }
    )
}