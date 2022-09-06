package co.uts.inmoapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import co.uts.inmoapp.ui.theme.greenLight
import co.uts.inmoapp.ui.theme.white

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    LoadingComp(isLoading = true)
}

@Composable
fun LoadingComp(isLoading: Boolean) {
    if (isLoading) {
        Dialog(onDismissRequest = {}) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        if (isSystemInDarkTheme()) Color.Black else white,
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                CircularProgressIndicator(
                    color = greenLight,
                    strokeWidth = 5.dp,
                    modifier = Modifier
                        .size(60.dp)
                )
            }
        }
    }
}