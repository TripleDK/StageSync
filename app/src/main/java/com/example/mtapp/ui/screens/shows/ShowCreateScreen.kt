package com.example.mtapp.ui.screens.shows

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mtapp.R
import com.example.mtapp.data.Show
import com.example.mtapp.ui.AppViewModelProvider
import com.example.mtapp.ui.examples.item.ItemInputForm
import kotlinx.coroutines.launch


@Composable
fun ShowCreateScreen(
    navigateBack: () -> Unit,
    viewModel: ShowCreateViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(

    ) { innerPadding ->
        ShowCreateBody(
            showUiState = viewModel.showUiState,
            onShowValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveShow()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current)
                )
        )

    }
}

@Composable
fun ShowCreateBody(
    showUiState: ShowFormState,
    onShowValueChange: (ShowDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large))
    ) {
        ShowInputForm(
            showDetails = showUiState.showDetails,
            onValueChange = onShowValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = onSaveClick) {
            Text(text = stringResource(R.string.save_action))
        }
    }
}

@Composable
fun ShowInputForm(
    showDetails: ShowDetails,
    onValueChange: (ShowDetails) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(

    ) {
        OutlinedTextField(
            value = showDetails.name,
            onValueChange = { onValueChange(showDetails.copy(name = it)) },
            label = { Text(stringResource(R.string.show_name_req)) }
        )
    }
}

@Preview
@Composable
private fun ShowCreateScreenPreview() {
    ShowCreateBody(
        showUiState = ShowFormState(
            ShowDetails(
                name = "Wizard of Oz"
            ),
        ),
        onSaveClick = {},
        onShowValueChange = {}
    )
}