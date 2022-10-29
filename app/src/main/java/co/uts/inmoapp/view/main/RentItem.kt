package co.uts.inmoapp.view.main

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import co.uts.inmoapp.R
import co.uts.inmoapp.ui.theme.black
import co.uts.inmoapp.ui.theme.greenBlack
import co.uts.inmoapp.ui.theme.greenLight
import co.uts.inmoapp.ui.theme.white
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFFFF,
    showBackground = true
)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF1F1F1F,
    showBackground = true
)
@Composable
private fun Preview() {
    RentItem()
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun RentItem() {
    val isDark = isSystemInDarkTheme()
    LazyColumn(modifier = Modifier.background(if (isSystemInDarkTheme()) black else white)) {
        item {

            val images = listOf(
                R.drawable.example,
                R.drawable.example2,
                R.drawable.example3,
                R.drawable.example4,
                R.drawable.example5
            )
            HorizontalPager(
                count = 5,
                modifier = Modifier
                    .height(300.dp)
            ) { page ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    backgroundColor = if (isSystemInDarkTheme()) white else black,
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 20.dp)
                ) {
                    Image(
                        painter = painterResource(images[page]),
                        contentDescription = "imagen inmuheble",
                        contentScale = ContentScale.Inside,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
            }
        }
        item {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(140.dp)
                        .border(
                            1.dp,
                            if (isSystemInDarkTheme()) white else black,
                            shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                        )
                        .padding(top = 8.dp, bottom = 13.dp)
                ) {
                    Text(
                        text = "Area contruida",
                        fontSize = 17.sp,
                        color = if (isSystemInDarkTheme()) white else black
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_medida),
                            contentDescription = "medida",
                            tint = if (isSystemInDarkTheme()) white else black,
                            modifier = Modifier
                                .width(25.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "81m",
                            fontSize = 16.sp,
                            color = if (isSystemInDarkTheme()) white else black
                        )
                        Text(
                            text = "2",
                            fontSize = 12.sp,
                            color = if (isSystemInDarkTheme()) white else black
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(120.dp)
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            val y = size.height - strokeWidth / 2
                            val y2 = strokeWidth / 2

                            drawLine(
                                if (isDark) white else black,
                                Offset(0f, y),
                                Offset(size.width, y),
                                strokeWidth
                            )
                            drawLine(
                                if (isDark) white else black,
                                Offset(0f, y2),
                                Offset(size.width, y2),
                                strokeWidth
                            )
                        }
                        .padding(top = 8.dp, bottom = 13.dp)
                ) {
                    Text(
                        text = "Habitaciones",
                        fontSize = 17.sp,
                        color = if (isSystemInDarkTheme()) white else black
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_habitacion),
                            contentDescription = "habitaciones",
                            tint = if (isSystemInDarkTheme()) white else black,
                            modifier = Modifier
                                .width(25.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "3",
                            fontSize = 16.sp,
                            color = if (isSystemInDarkTheme()) white else black
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(100.dp)
                        .border(
                            1.dp,
                            if (isSystemInDarkTheme()) white else black,
                            shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
                        )
                        .padding(top = 8.dp, bottom = 13.dp)
                ) {
                    Text(
                        text = "Baños",
                        fontSize = 17.sp,
                        color = if (isSystemInDarkTheme()) white else black
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bath),
                            contentDescription = "baños",
                            tint = if (isSystemInDarkTheme()) white else black,
                            modifier = Modifier
                                .width(25.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "2",
                            fontSize = 16.sp,
                            color = if (isSystemInDarkTheme()) white else black
                        )
                    }
                }
            }
        }
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Apartamento",
                    textAlign = TextAlign.Start,
                    fontSize = 35.sp,
                    color = greenLight,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Luminoso apartamento en el barrio lagos del cacique, cada " +
                            "habitación tiene closet, cocina semi-integral, sala comedora," +
                            " balcón, zona de ropas, parqueadero descubierto. El conjunto " +
                            "consta de zona común, salón social, gym, parque infantil, sauna," +
                            " piscina, bbq, ascensor y vigilancia 24 hrs. Cerca a zona " +
                            "comercial, excelente ubicación.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    color = if (isSystemInDarkTheme()) white else black,
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(50.dp))
            Contact()
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}


@Composable
private fun Contact() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            shape = CutCornerShape(20.dp),
            backgroundColor = if (isSystemInDarkTheme()) greenBlack else greenLight,
            modifier = Modifier
                .size(250.dp, 280.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Propietario",
                    fontSize = 25.sp,
                    color = if (isSystemInDarkTheme()) white else black
                )
                Divider(
                    color = if (isSystemInDarkTheme()) black else white,
                    thickness = 1.dp,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_contact),
                    contentDescription = "Contacto",
                    tint = if (isSystemInDarkTheme()) white else black,
                    modifier = Modifier
                        .size(120.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (isSystemInDarkTheme())black else white
                    ),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .size(190.dp, 55.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_telefono),
                        contentDescription = "ic_telefono",
                        tint = if (isSystemInDarkTheme())white else black,
                        modifier = Modifier
                            .size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Comunicame\ncon el", textAlign = TextAlign.Center, color = if (isSystemInDarkTheme())white else black)
                }
            }
        }
    }
}