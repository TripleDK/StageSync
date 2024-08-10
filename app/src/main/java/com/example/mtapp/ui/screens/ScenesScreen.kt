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
import com.example.mtapp.Models.Scene
import com.example.mtapp.Models.Show
import com.example.mtapp.R
import com.example.mtapp.data.DataSource
import com.example.mtapp.ui.theme.MTAPPTheme

@Composable
fun ScenesListContent(
    scenes: List<Scene>,
    onSceneClicked: (Scene) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier
    ) {
        items(items = scenes) {
            SceneListItem(
                scene = it,
                onClick = { onSceneClicked(it) }
            )
        }
    }
}

@Composable
fun SceneListItem(
    scene: Scene,
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
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        ) {
            Text(
                text = stringResource(scene.name)
            )
        }
    }
}

@Preview
@Composable
fun SceneListItemPreview() {
    MTAPPTheme {
        SceneListItem(
            scene = DataSource.shows[0].scenes[0],
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScenesListContentPreview() {
    ScenesListContent(
        scenes = DataSource.shows[0].scenes,
        onSceneClicked = {})
}
