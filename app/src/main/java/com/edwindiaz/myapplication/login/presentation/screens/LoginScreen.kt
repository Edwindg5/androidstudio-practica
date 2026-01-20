package com.edwindiaz.myapplication.login.presentation.screens


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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.edwindiaz.myapplication.R


@Composable
fun LoginScreen(){
    var username by remember { mutableStateOf(value = "") }
    var password by remember { mutableStateOf(value = "") }

    Column(
        modifier = Modifier.fillMaxSize()
        .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Surface() {
            Text(text = "Bienvenido")
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.images        ),
                contentDescription = "Logo empresa"
            )
            Spacer(modifier = Modifier.height(50.dp))
            TextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("username") }
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("password") }
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                onClick = {}
            ) {
                Text(text = "Iniciar sesión")
            }
        }
        Text(text = "@mi empresa")
    }
}

@Preview
@Composable
fun  LoginScreenPreview(){
    LoginScreen()
}

