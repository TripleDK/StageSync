//package com.example.mtapp.ui.screens.rehearsalscreen
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.gestures.detectDragGestures
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxWithConstraints
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.heightIn
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.widthIn
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.PersonAddAlt1
//import androidx.compose.material3.FloatingActionButton
//import androidx.compose.material3.Icon
//import androidx.compose.material3.ListItem
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.derivedStateOf
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.layout.onGloballyPositioned
//import androidx.compose.ui.layout.positionInWindow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.unit.IntSize
//import androidx.compose.ui.unit.dp
//import com.example.mtapp.Models.ChoreoPosition
//import com.example.mtapp.Models.KeyFrame
//import com.example.mtapp.Models.Show
//import com.example.mtapp.Models.ShowCharacter
//import com.example.mtapp.ui.MediaPlayerViewModel
//import kotlin.math.roundToInt
//import kotlin.random.Random
//
//@Composable
//fun ChoreoScreenContainer(
//    show: Show,
//    mediaPlayerViewModel: MediaPlayerViewModel,
//    modifier: Modifier = Modifier
//) {
//    var showAddCharacterPanel by remember { mutableStateOf(false) }
//
//    var choreoPositions: List<ChoreoPosition> by remember { mutableStateOf(listOf<ChoreoPosition>()) }
//    var charactersAdded: List<ShowCharacter> by remember { mutableStateOf(listOf<ShowCharacter>()) }
//    val availableCharacters: List<ShowCharacter> by remember {
//        derivedStateOf {
//            show.characters.filterNot { characterInUse ->
//                charactersAdded.contains(characterInUse)
//            }
//        }
//    }
//
//    var draggableCharacter by remember { mutableStateOf<ShowCharacter?>(null) }
//    var dragOffset by remember { mutableStateOf(Offset.Zero) }
//    var parentBoxOffset by remember { mutableStateOf(Offset.Zero) }
//    var parentBoxSize by remember { mutableStateOf(IntSize.Zero) }
//    var characterListHeight by remember { mutableStateOf(0) }
//
//    Box(modifier = modifier
//        .fillMaxSize()
//        .onGloballyPositioned { coordinates ->
//            parentBoxOffset = coordinates.positionInWindow()
//            parentBoxSize = coordinates.size
//        }
//    )
//    {
//        ChoreoScreen(
//            people = choreoPositions,
//            mediaPlayerViewModel = mediaPlayerViewModel
//        )
//
//        if (showAddCharacterPanel) {
//            AddCharacterPanel(
//                characters = availableCharacters,
//                onDismiss = { showAddCharacterPanel = false },
//                onCreateNewCharacter = { character, offset, listHeight ->
//                    draggableCharacter = character
//                    dragOffset = offset - parentBoxOffset
//                    characterListHeight = listHeight
//                },
//                onDragNewCharacter = { offset ->
//                    dragOffset += offset
//                },
//                onDropNewCharacter = { character ->
//                    //Check we are within bounds of the rehearsal screen
//                    if (dragOffset.y > 0 && dragOffset.y < parentBoxSize.height - characterListHeight) {
//                        val ranPosX1 = Random.nextFloat()
//                        val ranPosY1 = Random.nextFloat()
//                        charactersAdded += character
//                        choreoPositions += ChoreoPosition(
//                            character = character,
//                            keyFrames = listOf(
//                                KeyFrame(
//                                    time = 0,
//                                    relativePositionX = dragOffset.x / parentBoxSize.width,
//                                    relativePositionY = dragOffset.y / parentBoxSize.height
//                                ),
//                                KeyFrame(
//                                    time = 1000,
//                                    relativePositionX = ranPosX1,
//                                    relativePositionY = ranPosY1
//                                ),
//                                KeyFrame(
//                                    time = 3000,
//                                    relativePositionX = ranPosX1,
//                                    relativePositionY = ranPosY1
//                                ),
//                                KeyFrame(
//                                    time = 4000,
//                                    relativePositionX = dragOffset.x / parentBoxSize.width,
//                                    relativePositionY = dragOffset.y / parentBoxSize.height
//                                ),
//                                KeyFrame(
//                                    time = 6000,
//                                    relativePositionX = dragOffset.x / parentBoxSize.width,
//                                    relativePositionY = dragOffset.y / parentBoxSize.height
//                                ),
//                                KeyFrame(
//                                    time = 8000,
//                                    relativePositionX = Random.nextFloat(),
//                                    relativePositionY = Random.nextFloat()
//                                )
//                            )
//                        )
//                    }
//                    draggableCharacter = null
//                }
//            )
//        } else {
//            AddCharacterButton(
//                onClick = {
//                    showAddCharacterPanel = true
//                }
//            )
//        }
//
//        if (draggableCharacter != null) {
//            DraggableCharacter(
//                character = draggableCharacter!!,
//                offset = dragOffset
//            )
//
//        }
//    }
//}
//
//
//@Composable
//fun AddCharacterButton(onClick: () -> Unit) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        contentAlignment = Alignment.BottomEnd
//    ) {
//        FloatingActionButton(
//            onClick = onClick,
//            shape = CircleShape,
//            containerColor = MaterialTheme.colorScheme.primary
//        ) {
//            Icon(Icons.Default.PersonAddAlt1, contentDescription = "Add Character")
//        }
//    }
//}
//
//@Composable
//fun AddCharacterPanel(
//    characters: List<ShowCharacter>,
//    onCreateNewCharacter: (ShowCharacter, Offset, Int) -> Unit,
//    onDragNewCharacter: (Offset) -> Unit,
//    onDropNewCharacter: (ShowCharacter) -> Unit,
//    onDismiss: () -> Unit,
//) {
//    var size by remember { mutableStateOf(IntSize.Zero) }
//    BoxWithConstraints(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Black.copy(alpha = 0.5f))
//            .clickable(onClick = onDismiss)
//    ) {
//        LazyColumn(
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .background(Color.White)
//                .fillMaxWidth()
//                .clickable(
//                    interactionSource = remember { MutableInteractionSource() },
//                    indication = null
//                ) {
//                    // Prevent clicks inside the list from dismissing the overlay
//                }
//                .heightIn(max = maxHeight / 4)
//                .onGloballyPositioned { layoutCoordinates ->
//                    size = layoutCoordinates.size
//                }
//        ) {
//            items(items = characters + ShowCharacter(name = "Ensemble")) { character ->
//                var listItemGlobalOffset = Offset.Zero
//                Surface(
//                    modifier = Modifier
//                        .padding(vertical = 8.dp)
//                        .clip(RoundedCornerShape(16.dp))
//                        .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(16.dp)),
//                    color = MaterialTheme.colorScheme.surface,
//                    shape = RoundedCornerShape(16.dp)
//                ) {
//                    ListItem(
//                        headlineContent = { Text(character.name) },
//                        modifier = Modifier
//                            .widthIn(max = constraints.maxWidth.dp / 8)
//                            .align(Alignment.CenterStart)
//
//                            .onGloballyPositioned { coordinates ->
//                                listItemGlobalOffset = coordinates.positionInWindow()
//                            }
//                            .pointerInput(character) {
//                                detectDragGestures(
//                                    onDragStart = { offset ->
//                                        val screenPosition = offset + listItemGlobalOffset
//                                        onCreateNewCharacter(character, screenPosition, size.height)
//                                    },
//                                    onDrag = { _, dragAmount ->
//                                        onDragNewCharacter(dragAmount)
//                                    },
//                                    onDragEnd = {
//                                        onDropNewCharacter(character)
//                                    }
//                                )
//                            }
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun DraggableCharacter(
//    character: ShowCharacter,
//    offset: Offset,
//    modifier: Modifier = Modifier,
//) {
//
//    Box(
//        modifier = modifier
//            .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
//            .background(Color.Yellow, shape = CircleShape)
//            .padding(8.dp)
//    ) {
//        Text(character.name)
//    }
//}
//
//
//@Preview(showBackground = true, heightDp = 800)
//@Composable
//fun AddCharacterPanelPreview() {
//    AddCharacterPanel(
//        characters = listOf(
//            ShowCharacter(name = "test1"),
//            ShowCharacter(name = "test2"),
//            ShowCharacter(name = "test3"),
//            ShowCharacter(name = "test4")
//        ),
//        onCreateNewCharacter = { _, _, _ -> },
//        onDragNewCharacter = {},
//        onDropNewCharacter = {},
//        onDismiss = {}
//
//    )
//}