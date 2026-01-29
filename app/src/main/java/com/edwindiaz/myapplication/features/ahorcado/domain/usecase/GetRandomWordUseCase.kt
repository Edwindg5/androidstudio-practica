//GetRandomWordUseCase.kt
package com.edwindiaz.myapplication.features.ahorcado.domain.usecase

class GetRandomWordUseCase {
    private val palabras = listOf(
        "PROGRAMACION",
        "ANDROID",
        "KOTLIN",
        "COMPOSE",
        "COMPUTADORA",
        "TELEFONO",
        "INTERNET"
    )

    operator fun invoke(): String {
        return palabras.random()
    }
}