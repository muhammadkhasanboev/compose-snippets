package com.example.tadiworkers.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.tadiworkers.R
import com.example.tadiworkers.ui.theme.custom_color

@Composable
fun LoginScreen(){
    //text for label in OutlinedTextBox
    val loginText = stringResource(R.string.login_text)
    val passwordText = stringResource(R.string.password_text)

    //for getting content of the textbox
    var login by rememberSaveable() { mutableStateOf("") }
    var password by rememberSaveable() { mutableStateOf("") }

    //for imeAction
    val loginFocus = remember { FocusRequester() }
    val passwordFocus = remember { FocusRequester() }

    fun handleNext() {
        when {
            login.isBlank() -> loginFocus.requestFocus()
            password.isBlank() -> passwordFocus.requestFocus()
            else -> {
                // All filled → call backend
                /*TODO
                add backend call, to send two strings
                 */
            }
        }
    }

    //background color given composable
    CustomBackgroundColor(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = custom_color
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Box(
            modifier = Modifier
                .fillMaxSize(0.5f),
            contentAlignment = Alignment.Center
        ){
            CustomBackgroundImage()
        }
        Spacer(modifier = Modifier.height(25.dp))
        TextBox(
            label = loginText,
            value = login,
            onValueChange = { login = it },
            isPassword = false,
            imeAction = ImeAction.Next,
            onImeAction = { handleNext() },
            focusRequester = loginFocus
        )
        TextBox(
            label = passwordText,
            value = password,
            onValueChange = { password = it },
            isPassword = true,
            imeAction = ImeAction.Done,
            onImeAction = { handleNext() },
            focusRequester = passwordFocus
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                handleNext()
            }
        ) {
            Text(stringResource(R.string.login_button))
        }
    }
}

@Composable
fun TextBox(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean,
    imeAction: ImeAction,
    onImeAction: ()-> Unit,
    focusRequester: FocusRequester
){
    var passwordVisible by rememberSaveable() { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(label)},
        singleLine = true,
        modifier = Modifier.focusRequester(focusRequester),
        visualTransformation = if(isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if(isPassword){
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(
                        Icons.Outlined.Lock,
                        contentDescription = null
                    )
                }
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            unfocusedTextColor = Color.White,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedTrailingIconColor = Color.White,
            unfocusedTrailingIconColor = Color.White,
            cursorColor = Color.White
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onNext = {onImeAction()},
            onDone = {onImeAction()}
        )
    )
}


@Composable
fun CustomBackgroundColor(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ){

    }
}

@Composable
fun CustomBackgroundImage(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ){
        val imageModifier = Modifier.fillMaxSize()
        Image(
            painter = painterResource(R.drawable.face),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}
