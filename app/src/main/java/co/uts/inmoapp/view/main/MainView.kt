package co.uts.inmoapp.view.main

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.uts.inmoapp.R
import co.uts.inmoapp.ui.navigation.AppScreens
import co.uts.inmoapp.ui.theme.*

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview() {
    val nav = rememberNavController()
    MainView(nav)
}

@Composable
fun MainView(navController: NavController) {
    val scrollState = rememberLazyListState()
    val scrollIndex by remember { derivedStateOf { scrollState.firstVisibleItemIndex } }
    var lastIndex by remember {
        mutableStateOf(0)
    }
    val isScrollUp = {
        val res = scrollIndex > lastIndex
        lastIndex = scrollIndex
        res
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = if (isSystemInDarkTheme()) white else black,
                modifier = Modifier
                    .size(75.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_inmueble),
                    contentDescription = "add inmueble",
                    tint = if (isSystemInDarkTheme()) black else white,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                cutoutShape = CircleShape,
                backgroundColor = greenLight,
                contentPadding = PaddingValues(start = 15.dp, end = 15.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            navController.popBackStack()
                            navController.navigate(AppScreens.Main.route)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Unspecified),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp
                        ),
                        modifier = Modifier
                            .width(120.dp)
                    ) {
                        Text(text = "INICIO", fontSize = 17.sp)
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Unspecified),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp
                        ),
                        modifier = Modifier
                            .width(120.dp)
                    ) {
                        Text(text = "PERFIL", fontSize = 17.sp)
                    }
                }
            }
        },
        backgroundColor = if (isSystemInDarkTheme()) black else white,
        isFloatingActionButtonDocked = true
    ) { padding ->
        BarTop(isScrollUp())
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp),
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = padding.calculateEndPadding(LayoutDirection.Ltr), top = 100.dp)
        ) {
            items(10) {
                ItemView(navController)
            }
        }
    }
}

@Composable
private fun BarTop(scrollUp: Boolean) {
    val position by animateFloatAsState(if(scrollUp) -100f else 0f)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = greenLight,
            elevation = 8.dp,
            modifier = Modifier
                .padding(top = 20.dp)
                .width(340.dp)
                .height(50.dp)
                .graphicsLayer(translationY = position)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "buscar")
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(text = "Escriba la direccion para buscar") },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = black,
                        placeholderColor = black.copy(alpha = .6f),
                        backgroundColor = Color.Unspecified,
                        focusedIndicatorColor = Color.Unspecified,
                        unfocusedIndicatorColor = Color.Unspecified
                    ),
                    trailingIcon = {
                        if (true) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Clear search"
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun ItemView(navController: NavController) {
    Card(
        backgroundColor = if (isSystemInDarkTheme()) blueLight else greenBlack,
        shape = RoundedCornerShape(14.dp),
        elevation = 8.dp,
        modifier = Modifier
            .size(360.dp, 170.dp)
            .clickable {
                navController.navigate(AppScreens.RentItem.route)
            }
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.example),
                contentDescription = "imagen inmuheble",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .height(160.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Apartamento",
                    fontSize = 24.sp,
                    color = if (isSystemInDarkTheme()) black else greenLight
                )
                Row {
                    Text(text = "2", color = if (isSystemInDarkTheme()) black else greenLight)
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bath),
                        contentDescription = "baños",
                        tint = if (isSystemInDarkTheme()) black else greenLight,
                        modifier = Modifier
                            .size(17.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "3", color = if (isSystemInDarkTheme()) black else greenLight)
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_habitacion),
                        contentDescription = "habitacion",
                        tint = if (isSystemInDarkTheme()) black else greenLight,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "52m", color = if (isSystemInDarkTheme()) black else greenLight)
                    Text(
                        text = "2",
                        color = if (isSystemInDarkTheme()) black else greenLight,
                        fontSize = 9.sp
                    )
                }
                Text(text = "Cl 17 #2W - 80", fontSize = 18.sp, color = white)
            }
        }
    }
}