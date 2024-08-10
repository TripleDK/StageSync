package com.example.mtapp.ui.theme.exercises

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mtapp.Models.exercises.MenuItem
import com.example.mtapp.Models.exercises.MenuItem.AccompanimentItem
import com.example.mtapp.R
import com.example.mtapp.data.local.exercises.Datasource

@Composable
fun AccompanimentMenuScreen(
    options: List<AccompanimentItem>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChangd: (AccompanimentItem) -> Unit,
    modifier: Modifier = Modifier
) {
    BaseMenuScreen(
        options = options,
        onNextButtonClicked = onNextButtonClicked,
        onCancelButtonClicked = onCancelButtonClicked,
        onSelectionChanged = onSelectionChangd as (MenuItem) -> Unit,
        modifier = modifier
    )
}

@Preview
@Composable
fun AccompanimentMenuPreview() {
    AccompanimentMenuScreen(
        options = Datasource().accompanimentMenuItems,
        onCancelButtonClicked = { },
        onNextButtonClicked = { },
        onSelectionChangd = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState())
    )
}