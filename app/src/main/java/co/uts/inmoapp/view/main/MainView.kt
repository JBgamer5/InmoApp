package co.uts.inmoapp.view.main

import android.content.ClipData.Item
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.uts.inmoapp.R
import co.uts.inmoapp.ui.theme.greenBlack
import co.uts.inmoapp.ui.theme.greenLight
import co.uts.inmoapp.ui.theme.white

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MainView()
}

@Composable
fun MainView() {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(25.dp),
        contentPadding = PaddingValues(top = 30.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(15) {
            ItemView()
        }
    }
}

@Composable
private fun ItemView() {
    Card(
        backgroundColor = greenBlack,
        shape = RoundedCornerShape(14.dp),
        elevation = 8.dp,
        modifier = Modifier.size(360.dp, 170.dp)
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
                Text(text = "Apartamento", fontSize = 24.sp, color = greenLight)
                Row {
                    Text(text = "2", color = greenLight)
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bath),
                        contentDescription = "baños",
                        tint = greenLight,
                        modifier = Modifier
                            .size(17.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "3", color = greenLight)
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_habitacion),
                        contentDescription = "habitacion",
                        tint = greenLight,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "52m", color = greenLight)
                    Text(text = "2", color = greenLight, fontSize = 9.sp)
                }
                Text(text = "Cl 17 #2W - 80", fontSize = 18.sp, color = white)
            }
        }
    }
}