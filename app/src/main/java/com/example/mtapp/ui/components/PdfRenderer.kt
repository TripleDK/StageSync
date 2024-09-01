package com.example.mtapp.ui.components

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.util.Log
import android.widget.ImageView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.launch
import java.io.File
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PdfViewer(
    pdfFilePath: String,
    onTurnFirstPage: () -> Unit = {},
    onTurnLastPage: () -> Unit = {},
    initialPage: Int = 0,
    startPage: Int = 0,
    lastPage: Int = -1,
    modifier: Modifier = Modifier
) {
    val pdfRendererHelper = remember {
        PdfRendererHelper(pdfFilePath)
    }
    val pageCount = pdfRendererHelper.getPageCount()
    var actualLastPage by remember { mutableIntStateOf(if (lastPage == -1) pageCount - 1 else lastPage) }
    var currentPage by remember { mutableIntStateOf(initialPage) }
    val state = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    var sharedDragOffset by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(initialPage, lastPage) {
        currentPage = initialPage
        actualLastPage = if (lastPage == -1) pageCount - 1 else lastPage
        // Ensure that we scroll such that the currentPage is centered
        coroutineScope.launch {
            state.scrollToItem(currentPage - startPage)
        }
    }

    Box() {
        LazyRow(
            state = state,
            userScrollEnabled = false,
            modifier = Modifier.fillMaxHeight()
        ) {


            itemsIndexed((startPage..actualLastPage).toList()) { _, pageIndex ->
                PdfPageView(
                    pdfFilePath = pdfFilePath,
                    pageIndex = pageIndex,
                    currentPage = currentPage,
                    isMainPage = currentPage == pageIndex,
                    sharedDragOffset = sharedDragOffset,
                    onSwipeLeft = {
                        currentPage--
                        if (currentPage < startPage) {
                            currentPage = startPage
                            onTurnFirstPage()
                        }
                        coroutineScope.launch {
//                            Log.v(
//                                "MyMainActivity",
//                                "Swipe left to $currentPage, first visible item: ${state.firstVisibleItemIndex}"
//                            )
                            state.animateScrollToItem(currentPage - startPage)
//                            Log.v(
//                                "MyMainActivity",
//                                "New page $currentPage, first visible item: ${state.firstVisibleItemIndex}"
//                            )
                        }
                    },
                    onSwipeRight = {
                        currentPage++
                        if (currentPage > actualLastPage) {
                            currentPage = actualLastPage
                            onTurnLastPage()
                        }
                        coroutineScope.launch {
//                            Log.v(
//                                "MyMainActivity",
//                                "Swipe right to $currentPage, first visible item: ${state.firstVisibleItemIndex} "
//                            )
                            state.animateScrollToItem(currentPage - startPage)
//                            Log.v(
//                                "MyMainActivity",
//                                "New page $currentPage, first visible item: ${state.firstVisibleItemIndex}"
//                            )
                        }
                    },
                    onDragOffsetChange = { newOffset ->
                        sharedDragOffset = newOffset
                    },
                    modifier = Modifier
                        .fillParentMaxSize()
                )
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                pdfRendererHelper.close()
            }
        }
//        Text(
//            text = "\ncurrentPage: $currentPage",
//            modifier = modifier
//        )
    }

}

@Composable
fun FullScreenPdfViewer(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    pdfFilePath: String // Pass the path to your PDF
) {
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // PDF Viewer content
            PdfViewer(pdfFilePath) // Your PDF viewer composable

            // Dismiss button
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close"
                )
            }
        }
    }
}


@Composable
fun PdfPageView(
    pdfFilePath: String,
    pageIndex: Int,
    currentPage: Int,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit,
    sharedDragOffset: Float,
    isMainPage: Boolean,
    onDragOffsetChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val contentWidth = 800
    val contentHeight = 1200

    val swipeWidth = 400
    val swipeThreshold = 200

    var isZoomedOut by remember { mutableStateOf(true) }

    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    val transformableState = rememberTransformableState { zoomChange, offsetChange, _ ->
        // Limit zoom to zoom-in only
        scale = min(max(scale * zoomChange, 1f), 3f)
        offset += offsetChange
        isZoomedOut = scale <= 1f
    }

    var upCounter by remember { mutableStateOf(0) }

//    LaunchedEffect(isMainPage) {
//        Log.v(
//            "MainActivity",
//            "Recomposition: isMainPage = $isMainPage,  pageIndex: $pageIndex, currentPage = $currentPage"
//        )
//    }

    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    Box(
        modifier = modifier
            .pointerInput(isMainPage) {
                Log.v(
                    "MainActivity",
                    "Input on $pageIndex,  isMainPage: $isMainPage, currentPage: $currentPage"
                )
                ///Zoom and pan gestures
                if (isMainPage) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale = (scale * zoom).coerceIn(1f, 3f)
                        isZoomedOut = scale <= 1f
                        // Calculate max offsets to prevent panning beyond the content bounds
                        val maxOffsetX = (contentWidth * scale - contentWidth) / 2
                        val maxOffsetY = (contentHeight * scale - contentHeight) / 2

                        offset = if (isZoomedOut) {
                            Offset(
                                x = (offset.x + pan.x * scale).coerceIn(
                                    -maxOffsetX - swipeWidth,
                                    maxOffsetX + swipeWidth
                                ),
                                y = offset.y
                            )
                        } else {
                            Offset(
                                x = (offset.x + pan.x * scale).coerceIn(
                                    -maxOffsetX,
                                    maxOffsetX
                                ),
                                y = (offset.y + pan.y * scale).coerceIn(-maxOffsetY, maxOffsetY)
                            )
                        }
                        if (isMainPage || isZoomedOut)
                            onDragOffsetChange(offset.x)
                    }
                }
            }
            .pointerInput(isMainPage) {
                ///Detect when gesture is off
                forEachGesture {
                    awaitPointerEventScope {
                        awaitFirstDown(requireUnconsumed = false)
                        do {
                            val event = awaitPointerEvent()
                            val canceled = event.changes.any { it.consumed.positionChange }
                        } while (!canceled && event.changes.any { it.pressed })
                        if (isZoomedOut) {
                            onDragOffsetChange(0f)
                            if (offset.x > swipeThreshold)
                                onSwipeLeft()
                            else if (offset.x < -swipeThreshold)
                                onSwipeRight()
                            offset = Offset.Zero
                        }
                        upCounter++
                        Log.v("MyMainActivity", "Up!")
                    }
                }
            }
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                translationX = sharedDragOffset,
                translationY = offset.y
            )
    ) {
//        Text(
//            text = "isMainPage: $isMainPage" +
//                    "upCounter: $upCounter, pageIndex: $pageIndex",
//            modifier = modifier
//        )
        DisposableEffect(pdfFilePath, pageIndex) {
            val pdfRendererHelper = PdfRendererHelper(pdfFilePath)

            bitmap = pdfRendererHelper.renderPage(pageIndex, contentWidth, contentHeight)
            onDispose {
                pdfRendererHelper.close()
            }
        }



        bitmap?.let { bmp ->
            AndroidView(
                modifier = modifier,
                factory = { context ->
                    ImageView(context).apply {
                        setImageBitmap(bmp)
                    }
                },
                update = { view ->
                    view.setImageBitmap(bmp)
                }
            )
        }
    }
}

class PdfRendererHelper(pdfFilePath: String) {
    private val pdfFile: File = File(pdfFilePath)
    private val fileDescriptor: ParcelFileDescriptor =
        ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY)
    private val pdfRenderer: PdfRenderer = PdfRenderer(fileDescriptor)

    fun renderPage(pageIndex: Int, width: Int, height: Int): Bitmap {
        val page = pdfRenderer.openPage(pageIndex)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        page.close()
        return bitmap
    }

    fun getPageCount(): Int {
        return pdfRenderer.pageCount
    }

    fun close() {
        pdfRenderer.close()
        fileDescriptor.close()
    }
}


