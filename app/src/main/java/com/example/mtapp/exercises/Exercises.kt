package com.example.mtapp.exercises//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.mtapp.R
//
//@Composable
//fun ArticleText(
//    articleHeader: String,
//    articleIntro: String,
//    articleBody: String
//) {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier
//    ) {
//        Text(
//            text = articleHeader,
//            fontSize = 24.sp,
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .padding(16.dp)
//        )
//        Text(
//            text = articleIntro,
//            textAlign = TextAlign.Justify,
//            modifier = Modifier
//                .padding(16.dp)
//                .align(alignment = Alignment.CenterHorizontally)
//        )
//        Text(
//            text = articleBody,
//            textAlign = TextAlign.Justify,
//            fontSize = 16.sp,
//            modifier = Modifier
//                .padding(16.dp)
//                .align(alignment = Alignment.CenterHorizontally)
//        )
//    }
//}
//
//@Composable
//fun TutorialPage(
//    articleHeader: String,
//    articleIntro: String,
//    articleBody: String,
//    modifier: Modifier = Modifier
//) {
//    val image = painterResource(id = R.drawable.bg_compose_background)
//    Column(modifier) {
//        Image(
//            painter = image,
//            contentDescription = null,
//            contentScale = ContentScale.Fit,
//            modifier = Modifier
//                .fillMaxWidth()
//        )
//        ArticleText(
//            articleHeader,
//            articleIntro,
//            articleBody
//        )
//    }
//}
//
//@Composable
//fun FullArticle() {
//    TutorialPage(
//        articleHeader = stringResource(R.string.mainscreen_header),
//        articleIntro = stringResource(R.string.article_intro),
//        articleBody = stringResource(R.string.article_text)
//    )
//}


//@Composable
//fun TaskComplete() {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        val image = painterResource(id = R.drawable.ic_task_completed)
//        Image(painter = image, contentDescription = null)
//        Text(
//            text = stringResource(R.string.all_tasks_completed),
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier
//                .padding(0.dp, 24.dp, 8.dp)
//        )
//        Text(
//            text = stringResource(R.string.encouragement),
//            fontSize = 16.sp
//        )
//    }
//}


//
//@Composable
//fun AllQuadrants() {
//    Column(Modifier.fillMaxWidth()) {
//        Row(modifier = Modifier.weight(1f)) {
//            Quadrant(
//                header = "Text composable",
//                body = "Displays text and follows the recommended Material Design guidelines.",
//                bgColor = Color(0xFFEADDFF),
//                modifier = Modifier.weight(1f)
//            )
//            Quadrant(
//                header = "Image composable",
//                body = "Creates a composable that lays out and draws a given Painter class object.",
//                bgColor = Color(0xFFD0BCFF),
//                modifier = Modifier.weight(1f)
//            )
//        }
//        Row(modifier = Modifier.weight(1f)) {
//            Quadrant(
//                header = "Row composable",
//                body = "A layout composable that places its children in a horizontal sequence.",
//                bgColor = Color(0xFFB69DF8),
//                modifier = Modifier.weight(1f)
//            )
//            Quadrant(
//                header = "Column composable",
//                body = "A layout composable that places its children in a vertical sequence.",
//                bgColor = Color(0xFFF6EDFF),
//                modifier = Modifier.weight(1f)
//            )
//        }
//    }
//}
//
//@Composable
//fun Quadrant(header: String, body: String, bgColor: Color, modifier: Modifier = Modifier) {
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .background(bgColor)
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = header,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//        Text(
//            text = body,
//            textAlign = TextAlign.Justify
//        )
//    }
//}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LemonSqueezerApp() {
//    ImageAndText()
//}
//
//
//@Composable
//fun ImageAndText(
//    modifier: Modifier = Modifier
//        .fillMaxSize()
//        .wrapContentSize(Alignment.Center)
//) {
//    var currentStep by remember { mutableStateOf(1) }
//    var lemonSqueezeLeft by remember { mutableStateOf(4) }
//    val imageResource = when (currentStep) {
//        1 -> R.drawable.lemon_tree
//        2 -> R.drawable.lemon_squeeze
//        3 -> R.drawable.lemon_drink
//        else -> R.drawable.lemon_restart
//    }
//    var instruction = when (currentStep) {
//        1 ->  R.string.tap_the_lemon_tree
//        2 -> R.string.keep_tapping
//        3 -> R.string.tap_to_drink
//        else -> R.string.tap_to_start_again
//    }
//    var imgContentDescription = when (currentStep) {
//        1 -> R.string.lemon_tree
//        2 -> R.string.lemon
//        3 -> R.string.glass_of_lemonade
//        else -> R.string.empty_glass
//    }
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//    ) {
//        Image(
//            painter = painterResource(id = imageResource),
//            contentDescription = stringResource(imgContentDescription),
//
//            modifier = Modifier
//                .clip(RoundedCornerShape(16.dp))
//                .background(Color(175,205,175))
//                .clickable {
//                    if(currentStep == 2)
//                        lemonSqueezeLeft--
//
//                    if(currentStep != 2  || lemonSqueezeLeft == 0)
//                        currentStep += 1;
//                    if(currentStep > 4) {
//                        currentStep = 1
//                        lemonSqueezeLeft = (2..4).random()
//                    }
//                }
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(stringResource(instruction), fontSize = 18.sp)
//    }
//}


//@Composable
//fun TipTimeLayout() {
//    var roundUp by remember { mutableStateOf(false) }
//    var amountInput by remember { mutableStateOf("") }
//    var tipInput by remember { mutableStateOf("") }
//
//    val amount = amountInput.toDoubleOrNull() ?: 0.0
//    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
//    val tip = calculateTip(amount, tipPercent, roundUp)
//
//    Column(
//        modifier = Modifier
//            .statusBarsPadding()
//            .padding(horizontal = 40.dp)
//            .verticalScroll(rememberScrollState())
//            .safeDrawingPadding(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = stringResource(R.string.calculate_tip),
//            modifier = Modifier
//                .padding(bottom = 16.dp, top = 40.dp)
//                .align(alignment = Alignment.Start)
//        )
//        EditNumberField(
//            value = amountInput,
//            onValueChange = { amountInput = it },
//            label = R.string.bill_amount,
//            modifier = Modifier
//                .padding(bottom = 32.dp)
//                .fillMaxWidth(),
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Number,
//                imeAction = ImeAction.Next
//            ),
//            leadingIcon = R.drawable.money
//        )
//        EditNumberField(
//            value = tipInput,
//            onValueChange = { tipInput = it },
//            label = R.string.how_was_the_service,
//            leadingIcon = R.drawable.percent,
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Number,
//                imeAction = ImeAction.Done
//            ),
//            modifier = Modifier
//                .padding(bottom = 32.dp)
//                .fillMaxWidth()
//        )
//        RoundTheTipRow(
//            roundUp = roundUp,
//            onRoundUpChanged = { roundUp = it },
//            modifier = Modifier.padding(bottom = 32.dp)
//        )
//        Text(
//            text = stringResource(R.string.tip_amount, tip),
//            style = MaterialTheme.typography.displaySmall
//        )
//        Spacer(modifier = Modifier.height(150.dp))
//
//    }
//
//
//    stringResource(R.string.tip_app_name)
//
//}
//
//@Composable
//fun EditNumberField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    @StringRes label: Int,
//    keyboardOptions: KeyboardOptions,
//    @DrawableRes leadingIcon: Int,
//    modifier: Modifier = Modifier
//) {
//    TextField(
//        value = value,
//        onValueChange = onValueChange,
//        label = { Text(stringResource(label)) },
//        singleLine = true,
//        keyboardOptions = keyboardOptions,
//        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
//        modifier = modifier
//    )
//}
//
//@Composable
//fun RoundTheTipRow(
//    roundUp: Boolean,
//    onRoundUpChanged: (Boolean) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .size(48.dp),
//        verticalAlignment = Alignment.CenterVertically
//
//    ) {
//        Text(stringResource(R.string.round_up_tip))
//        Switch(
//            modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentWidth(Alignment.End),
//            checked = roundUp,
//            onCheckedChange = onRoundUpChanged
//        )
//
//    }
//}
//
//@VisibleForTesting
//internal fun calculateTip(
//    amount: Double,
//    tipPercent: Double = 15.0,
//    roundUp: Boolean
//): String {
//    var tip = tipPercent / 100 * amount
//    if (roundUp) {
//        tip = kotlin.math.ceil(tip)
//    }
//    return NumberFormat.getCurrencyInstance().format(tip)
//}
//
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun TipTimeLayoutPreview() {
//    MTAPPTheme {
//        TipTimeLayout()
//    }
//}


//@Composable
//fun ArtworkShowcaseApp() {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(20.dp)
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.mtbackground),
//                contentDescription = null,
//                modifier = Modifier
//                    .padding(20.dp)
//            )
//        }
//        Row(
//            modifier = Modifier
//                .padding(20.dp)
//        ) {
//            Column() {
//                Row() {
//                    Text("Title")
//                }
//                Row() {
//                    Text("Artist (Year)")
//                }
//            }
//        }
//        Row() {
//            Button(onClick = {}) { Text("Previous") }
//            Button(onClick = {}) { Text("Next") }
//        }
//    }
//}
//
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ArtworkShowcaseAppPreview() {
//    MTAPPTheme {
//        ArtworkShowcaseApp()
//    }
//}
//}
//


//@Composable
//fun AffirmationsApp() {
//    AffirmationList(
//        affirmationList = Datasource().loadAffirmations()
//    )
//}
//
//@Composable
//fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
//    Card(modifier = modifier) {
//        Column {
//            Image(
//                painter = painterResource(affirmation.imageResourceId),
//                contentDescription = stringResource(affirmation.stringResourceId),
//                modifier = modifier
//                    .fillMaxWidth()
//                    .height(194.dp),
//                contentScale = ContentScale.Crop
//            )
//            Text(
//                stringResource(affirmation.stringResourceId),
//                modifier = Modifier
//                    .padding(16.dp),
//                style = MaterialTheme.typography.headlineSmall
//            )
//        }
//    }
//}
//
//@Composable
//fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
//    LazyColumn(modifier = modifier) {
//        items(affirmationList) { affirmation ->
//            AffirmationCard(
//                affirmation = affirmation,
//                Modifier.padding(8.dp)
//            )
//        }
//    }
//}
//
//@Preview
//@Composable
//fun AffirmationCardPreview() {
//    AffirmationCard(affirmation = Affirmation(R.string.affirmation1, R.drawable.image1))
//}


//@Composable
//fun TopicGridItem(topic: Topic, modifier: Modifier) {
//    Box(
//        modifier = modifier
//            .clip(RoundedCornerShape(8.dp))
//            .background(Color.LightGray)
//    ) {
//        Row() {
//            Image(
//                painter = painterResource(topic.logo),
//                contentDescription = stringResource(topic.title),
//                modifier = Modifier
//                    .height(68.dp)
//                    .width(68.dp)
//            )
//            Column(
//                modifier = Modifier
//                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
//            ) {
//                Text(
//                    stringResource(topic.title),
//                    style = MaterialTheme.typography.bodyMedium,
//                    overflow = TextOverflow.Ellipsis,
//                    maxLines = 1,
//                    modifier = Modifier
//                        .padding(0.dp, 0.dp, 0.dp, 8.dp)
//                )
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Image(
//                        painter = painterResource(R.drawable.ic_grain),
//                        contentDescription = null,
//                        Modifier
//                            .padding(end = 8.dp)
//                    )
//                    Text(
//                        topic.courseNumber.toString(),
//                        style = MaterialTheme.typography.labelMedium,
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun TopicsGrid(topicList: List<Topic>) {
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        modifier = Modifier.padding(8.dp)
//    ) {
//        items(topicList) { topic ->
//            TopicGridItem(topic = topic, modifier = Modifier)
//        }
//    }
//}
//
//@Composable
//fun CoursesApp() {
//    TopicsGrid(
//        topicList = Datasource().topics
//    )
//}
//
//@Preview(showBackground = true, showSystemUi = false)
//@Composable
//fun CoursesAppPreview() {
//    // TopicGridItem(Topic(R.string.photography, 321, R.drawable.photography), Modifier)
//    CoursesApp()
//}

//
//@Composable
//fun WoofApp() {
//    Scaffold(
//        topBar = { WoofTopAppBar() }
//    ) { it ->
//        LazyColumn(contentPadding = it) {
//            items(Datasource().dogs) {
//                DogItem(
//                    dog = it,
//                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
//                )
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun WoofTopAppBar(modifier: Modifier = Modifier) {
//    CenterAlignedTopAppBar(
//        title = {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    modifier = Modifier
//                        .size(dimensionResource(R.dimen.image_size))
//                        .padding(dimensionResource(id = R.dimen.padding_small)),
//                    painter = painterResource(R.drawable.ic_woof_logo),
//                    contentDescription = null
//                )
//                Text(
//                    text = "Woof",
//                    style = MaterialTheme.typography.displayLarge
//                )
//            }
//        },
//        modifier = modifier
//    )
//}
//
//@Composable
//fun DogItem(
//    dog: Dog,
//    modifier: Modifier = Modifier
//) {
//    var expanded by remember { mutableStateOf(false) }
//    val color by animateColorAsState(
//        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
//        else MaterialTheme.colorScheme.primaryContainer, label = ""
//    )
//
//    Card(modifier = modifier) {
//        Column(
//            modifier = Modifier
//                .animateContentSize(
//                    animationSpec = spring(
//                        dampingRatio = Spring.DampingRatioNoBouncy,
//                        stiffness = Spring.StiffnessMedium
//                    )
//                )
//                .background(color = color)
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(dimensionResource(R.dimen.padding_small))
//            ) {
//                DogIcon(dog.photo)
//                DogInformation(dog.name, dog.age, Modifier.padding(start = 8.dp))
//                Spacer(modifier = Modifier.weight(1f))
//                DogItemButton(
//                    expanded = expanded,
//                    onClick = { expanded = !expanded }
//                )
//            }
//            if (expanded) {
//                DogHobby(
//                    dogHobby = dog.hobbies,
//                    modifier = Modifier.padding(
//                        start = dimensionResource(R.dimen.padding_medium),
//                        top = dimensionResource(R.dimen.padding_small),
//                        end = dimensionResource(R.dimen.padding_medium),
//                        bottom = dimensionResource(R.dimen.padding_medium)
//                    )
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun DogItemButton(
//    expanded: Boolean,
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    IconButton(
//        onClick = onClick,
//        modifier = modifier
//    ) {
//        Icon(
//            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
//            contentDescription = stringResource(R.string.expand_button_content_description),
//            tint = MaterialTheme.colorScheme.secondary
//        )
//
//    }
//}
//
//@Composable
//fun DogHobby(
//    @StringRes dogHobby: Int,
//    modifier: Modifier = Modifier
//) {
//    Column(modifier = modifier) {
//        Text(
//            text = stringResource(R.string.about),
//            style = MaterialTheme.typography.labelSmall
//        )
//        Text(
//            text = stringResource(dogHobby),
//            style = MaterialTheme.typography.bodyLarge
//        )
//    }
//}
//
//@Composable
//fun DogInformation(
//    @StringRes name: Int,
//    age: Int,
//    modifier: Modifier = Modifier
//) {
//    Column(modifier = modifier) {
//        Text(
//            text = stringResource(name),
//            style = MaterialTheme.typography.displayMedium,
//            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
//        )
//        Text(
//            text = stringResource(R.string.years_old, age),
//            style = MaterialTheme.typography.bodyLarge
//        )
//    }
//}
//
//@Composable
//fun DogIcon(
//    @DrawableRes photo: Int,
//    modifier: Modifier = Modifier
//) {
//    Image(
//        modifier = modifier
//            .size(dimensionResource(R.dimen.image_size))
//            .clip(MaterialTheme.shapes.small),
//        painter = painterResource(photo),
//        contentScale = ContentScale.Crop,
//        contentDescription = null
//    )
//}
//
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun WoofAppPreview() {
//    WoofTheme(darkTheme = false) {
//        WoofApp()
//    }
//}
//
//@Preview
//@Composable
//fun WoofDarkThemePreview() {
//    WoofTheme(darkTheme = true) {
//        WoofApp()
//    }
//}


//@Composable
//private fun DessertClickerApp(
//    gameViewModel: DessertClickerViewModel = viewModel()
//) {
//    val gameUiState by gameViewModel.uiState.collectAsState()
//    Scaffold(
//        topBar = {
//            val intentContext = LocalContext.current
//            val layoutDirection = LocalLayoutDirection.current
//            DessertClickerAppBar(
//                onShareButtonClicked = {
//                    gameViewModel.shareSoldDessertsInformation(
//                        intentContext = intentContext
//                    )
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(
//                        start = WindowInsets.safeDrawing
//                            .asPaddingValues()
//                            .calculateStartPadding(layoutDirection),
//                        end = WindowInsets.safeDrawing
//                            .asPaddingValues()
//                            .calculateEndPadding(layoutDirection),
//                    )
//                    .background(MaterialTheme.colorScheme.primary)
//            )
//        }
//    ) { contentPadding ->
//        DessertClickerScreen(
//            onDessertClicked = {
//                gameViewModel.onDessertClicked()
//            },
//            revenue = gameUiState.revenue,
//            dessertsSold = gameUiState.dessertsSold,
//            dessertImageId = gameUiState.currentDessertImageId,
//            modifier = Modifier.padding(contentPadding)
//        )
//    }
//}
//
//@Composable
//private fun DessertClickerAppBar(
//    onShareButtonClicked: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Row(
//        modifier = modifier,
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        Text(
//            text = stringResource(R.string.app_name),
//            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium)),
//            color = MaterialTheme.colorScheme.onPrimary,
//            style = MaterialTheme.typography.titleLarge,
//        )
//        IconButton(
//            onClick = onShareButtonClicked,
//            modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_medium)),
//        ) {
//            Icon(
//                imageVector = Icons.Filled.Share,
//                contentDescription = stringResource(R.string.share),
//                tint = MaterialTheme.colorScheme.onPrimary
//            )
//        }
//    }
//}
//
//@Composable
//fun DessertClickerScreen(
//    revenue: Int,
//    dessertsSold: Int,
//    @DrawableRes dessertImageId: Int,
//    onDessertClicked: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Box(modifier = modifier) {
//        Image(
//            painter = painterResource(R.drawable.bakery_back),
//            contentDescription = null,
//            contentScale = ContentScale.Crop
//        )
//        Column {
//            Box(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth(),
//            ) {
//                Image(
//                    painter = painterResource(dessertImageId),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .width(dimensionResource(R.dimen.image_size))
//                        .height(dimensionResource(R.dimen.image_size))
//                        .align(Alignment.Center)
//                        .clickable { onDessertClicked() },
//                    contentScale = ContentScale.Crop,
//                )
//            }
//            TransactionInfo(
//                revenue = revenue,
//                dessertsSold = dessertsSold,
//                modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
//            )
//        }
//    }
//}
//
//@Composable
//private fun TransactionInfo(
//    revenue: Int,
//    dessertsSold: Int,
//    modifier: Modifier = Modifier
//) {
//    Column(modifier = modifier) {
//        DessertsSoldInfo(
//            dessertsSold = dessertsSold,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(dimensionResource(R.dimen.padding_medium))
//        )
//        RevenueInfo(
//            revenue = revenue,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(dimensionResource(R.dimen.padding_medium))
//        )
//    }
//}
//
//@Composable
//private fun DessertsSoldInfo(dessertsSold: Int, modifier: Modifier = Modifier) {
//    Row(
//        modifier = modifier,
//        horizontalArrangement = Arrangement.SpaceBetween,
//    ) {
//        Text(
//            text = stringResource(R.string.dessert_sold),
//            style = MaterialTheme.typography.titleLarge,
//            color = MaterialTheme.colorScheme.onSecondaryContainer
//        )
//        Text(
//            text = dessertsSold.toString(),
//            style = MaterialTheme.typography.titleLarge,
//            color = MaterialTheme.colorScheme.onSecondaryContainer
//        )
//    }
//}
//
//@Composable
//private fun RevenueInfo(revenue: Int, modifier: Modifier = Modifier) {
//    Row(
//        modifier = modifier,
//        horizontalArrangement = Arrangement.SpaceBetween,
//    ) {
//        Text(
//            text = stringResource(R.string.total_revenue),
//            style = MaterialTheme.typography.headlineMedium,
//            color = MaterialTheme.colorScheme.onSecondaryContainer
//        )
//        Text(
//            text = "$${revenue}",
//            textAlign = TextAlign.Right,
//            style = MaterialTheme.typography.headlineMedium,
//            color = MaterialTheme.colorScheme.onSecondaryContainer
//        )
//    }
//}
//
//@Preview
//@Composable
//fun MyDessertClickerAppPreview() {
//    DessertClickerTheme {
//        DessertClickerApp()
//    }
//}