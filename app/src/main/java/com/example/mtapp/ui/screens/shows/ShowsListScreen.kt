package com.example.mtapp.ui.screens.shows

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mtapp.R
import com.example.mtapp.data.Show
import com.example.mtapp.ui.AppViewModelProvider

import com.example.mtapp.ui.theme.MTAPPTheme

@Composable
fun ShowsListContent(
    onShowClicked: (Int) -> Unit,
    onAddShowClicked: () -> Unit,
    viewModel: ShowListViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val showListUiState by viewModel.showListUiState.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier
    ) {
        items(items = showListUiState.showList) {
            ShowListItem(
                show = it,
                onClick = { onShowClicked(it.id) }
            )
        }
    }
    AddShowButton(
        onClick = onAddShowClicked
    )
}

@Composable
fun ShowListItem(
    show: Show,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(
                text = show.name
            )
        }
    }
}

@Composable
fun AddShowButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = onClick,
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Show")
        }
    }
}

@Preview
@Composable
fun ShowListItemPreview() {
    MTAPPTheme {
        ShowListItem(
            show = Show(
                name = "Wizard of Oz"
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowsListContentPreview() {
    ShowsListContent(
//        shows = listOf(
//            Show(
//                name = "Wizard of Oz"
//            )
//        ),
        onShowClicked = {},
        onAddShowClicked = {}
    )
}
