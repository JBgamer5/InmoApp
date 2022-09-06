package co.uts.inmoapp.view

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.uts.inmoapp.R
import co.uts.inmoapp.ui.components.LoadingComp
import co.uts.inmoapp.ui.theme.black
import co.uts.inmoapp.ui.theme.greenBlack
import co.uts.inmoapp.ui.theme.greenLight
import co.uts.inmoapp.ui.theme.white
import co.uts.inmoapp.viewmodel.LoginViewModel

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    val navController = rememberNavController()
    val viewModel = LoginViewModel()
    LoginView(viewModel, navController)
}

@Composable
fun LoginView(viewModel: LoginViewModel, navController: NavController) {
    val context = LocalContext.current
    val focus = LocalFocusManager.current
    
    LoadingComp(isLoading = viewModel.isLoading)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) black else white)
    ) {
        Spacer(modifier = Modifier.padding(50.dp))
        Surface(
            shape = CircleShape,
            color = white
        ) {
            Image(
                painter = painterResource(R.drawable.ic_login),
                contentDescription = "loginImage",
                modifier = Modifier
                    .size(140.dp)
                    .padding(20.dp)

            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Inicie sesión o",
                color = if (isSystemInDarkTheme()) white else greenBlack,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(6.dp))
            Button(
                onClick = {},
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Unspecified
                ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                )
            ) {
                Text(
                    text = "Crea una cuenta",
                    color = greenLight,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = viewModel.correo,
            onValueChange = { viewModel.correo = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedLabelColor = if (isSystemInDarkTheme()) white.copy(alpha = .7f) else Color.Black.copy(
                    alpha = .4f
                ),
                unfocusedBorderColor = if (isSystemInDarkTheme()) white else Color.Gray,
                focusedLabelColor = greenLight,
                focusedBorderColor = greenLight,
                textColor = if (isSystemInDarkTheme()) white else black
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focus.moveFocus(FocusDirection.Down) }),
            label = { Text(text = "Correo electronico") },
            singleLine = true,
            shape = RectangleShape,
            modifier = Modifier
                .width(300.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedLabelColor = if (isSystemInDarkTheme()) white.copy(alpha = .7f) else Color.Black.copy(
                    alpha = .4f
                ),
                unfocusedBorderColor = if (isSystemInDarkTheme()) white else Color.Gray,
                focusedLabelColor = greenLight,
                focusedBorderColor = greenLight,
                textColor = if (isSystemInDarkTheme()) white else black
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focus.clearFocus() }),
            trailingIcon = {
                val image = if (viewModel.passwordVisibility)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                IconButton(onClick = {
                    viewModel.passwordVisibility = !viewModel.passwordVisibility
                }) {
                    Icon(
                        imageVector = image, "visibility password",
                        tint = if (isSystemInDarkTheme()) white.copy(alpha = .6f) else black.copy(
                            alpha = .6f
                        )
                    )
                }
            },
            label = { Text(text = "Contraseña") },
            singleLine = true,
            shape = RectangleShape,
            visualTransformation = if (viewModel.passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            modifier = Modifier
                .width(300.dp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Button(
            onClick = {
                viewModel.loginWithEmailAndPassword(context, navController)
            },
            modifier = Modifier
                .height(50.dp)
                .width(180.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = greenLight
            )
        ) {
            Text(
                text = "Iniciar sesión",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = if (isSystemInDarkTheme()) black else greenBlack,
            )

        }
    }
}

