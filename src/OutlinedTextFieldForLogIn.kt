package com.example.portfolio.ui.theme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.portfolio.R

@Composable
fun LoginScreen(){
    var login_text = stringResource(R.string.Login)
    var password_text = stringResource(R.string.Password)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextBoxField(label = login_text, value = email, onValueChange = {email = it}, false)
        Spacer(modifier = Modifier.height(25.dp))
        TextBoxField(label = password_text, value = password, onValueChange = {password = it}, true)
    }
}

@Composable
private fun TextBoxField(label: String, value: String, onValueChange: (String)-> Unit, isPasswordField: Boolean){

    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(label)},
        singleLine = true,
        visualTransformation = if(isPasswordField && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if(isPasswordField){
                IconButton(
                    onClick = {passwordVisible = !passwordVisible}
                ){
                    Icon(
                        Icons.Outlined.Lock,
                        contentDescription = null
                    )
                }
            }
        }
    )
}