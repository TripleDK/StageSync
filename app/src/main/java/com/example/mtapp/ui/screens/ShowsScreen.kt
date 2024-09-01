package com.example.mtapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mtapp.Models.Show
import com.example.mtapp.R

import com.example.mtapp.ui.theme.MTAPPTheme

@Composable
fun ShowsListContent(
    shows: List<Show>,
    onShowClicked: (Show) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier
    ) {
        items(items = shows) {
            ShowListItem(
                show = it,
                onClick = { onShowClicked(it) }
            )
        }
    }
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
                text = stringResource(show.name)
            )
        }
    }
}

@Preview
@Composable
fun ShowListItemPreview() {
    MTAPPTheme {
        ShowListItem(
            show = Show(
                name = R.string.wizard_of_oz,
                scenes = emptyList()
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowsListContentPreview() {
    ShowsListContent(
        shows = listOf(
            Show(
                name = R.string.wizard_of_oz,
                scenes = emptyList()
            )
        ),
        onShowClicked = {}
    )
}
