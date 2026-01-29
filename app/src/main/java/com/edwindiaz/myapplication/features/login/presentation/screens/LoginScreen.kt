package com.edwindiaz.myapplication.features.login.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edwindiaz.myapplication.R
import com.edwindiaz.myapplication.features.login.presentation.viewmodels.LoginViewModel


@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {

    val usernameState by viewModel.username.collectAsStateWithLifecycle()
    val passwordState by viewModel.password.collectAsStateWithLifecycle()
    val messageState by viewModel.message.collectAsStateWithLifecycle()


    val showMessage = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Surface() {
            Text(text = "Bienvenido")
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.images),
                contentDescription = "Logo empresa"
            )
            Spacer(modifier = Modifier.height(50.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                value = usernameState,
                onValueChange = {  },
                placeholder = { Text("username") }
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                value = passwordState,
                onValueChange = {},
                placeholder = { Text("password") }
            )

            Spacer(modifier = Modifier.height(20.dp))


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                onClick = {  }
            ) {
                Text(text = "Iniciar sesión")
            }

            if (showMessage.value && messageState.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = messageState,
                    color = if (messageState.contains("exitoso")) Color.Green else Color.Red
                )
            }
        }
        Text(text = "@mi empresa")
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}