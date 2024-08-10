package com.example.mtapp.ui.theme.exercises

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mtapp.Models.exercises.MenuItem
import com.example.mtapp.Models.exercises.MenuItem.EntreeItem
import com.example.mtapp.R
import com.example.mtapp.data.local.exercises.Datasource

@Composable
fun EntreeMenuScreen(
    options: List<EntreeItem>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (EntreeItem) -> Unit,
    modifier: Modifier = Modifier
) {
    BaseMenuScreen(
        options = options,
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        onSelectionChanged = onSelectionChanged as (MenuItem) -> Unit,
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EntreeMenuPreview() {
    LunchTrayTheme(darkTheme = true) {
        EntreeMenuScreen(
            options = Datasource().entreeMenuItems,
            onCancelButtonClicked = {},
            onNextButtonClicked = { },
            onSelectionChanged = {},
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .verticalScroll(rememberScrollState())
        )
    }
}