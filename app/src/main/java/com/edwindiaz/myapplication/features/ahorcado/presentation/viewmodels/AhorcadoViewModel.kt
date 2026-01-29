//AhorcadoViewModel.kt
package com.edwindiaz.myapplication.features.ahorcado.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.edwindiaz.myapplication.features.ahorcado.domain.usecase.GetRandomWordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AhorcadoViewModel(
    private val getRandomWordUseCase: GetRandomWordUseCase = GetRandomWordUseCase()
) : ViewModel() {
    private val _palabraSecreta = MutableStateFlow(getRandomWordUseCase())
    val palabraSecreta = _palabraSecreta.asStateFlow()

    private val _letrasAdivinadas = MutableStateFlow(mutableSetOf<Char>())
    val letrasAdivinadas = _letrasAdivinadas.asStateFlow()

    private val _letraIngresada = MutableStateFlow("")
    val letraIngresada = _letraIngresada.asStateFlow()

    private val _intentosRestantes = MutableStateFlow(6)
    val intentosRestantes = _intentosRestantes.asStateFlow()

    private val _mensaje = MutableStateFlow("")
    val mensaje = _mensaje.asStateFlow()

    fun actualizarLetra(letra: String) {
        _letraIngresada.value = letra
    }

    fun intentarAdivinar() {
        if (_letraIngresada.value.isEmpty()) {
            _mensaje.value = "Ingresa una letra"
            return
        }

        val letra = _letraIngresada.value.uppercase().first()

        if (_letrasAdivinadas.value.contains(letra)) {
            _mensaje.value = "Ya adivinaste esta letra"
            return
        }

        _letrasAdivinadas.value.add(letra)

        if (_palabraSecreta.value.contains(letra)) {
            _mensaje.value = "¡Correcto! La letra $letra está en la palabra"
        } else {
            _intentosRestantes.value--
            _mensaje.value = "Incorrecto. Te quedan ${_intentosRestantes.value} intentos"
        }

        _letraIngresada.value = ""

        verificarEstadoJuego()
    }

    private fun verificarEstadoJuego() {
        if (_intentosRestantes.value == 0) {
            _mensaje.value = "¡Perdiste! La palabra era: ${_palabraSecreta.value}"
            return
        }

        val palabraCompleta = _palabraSecreta.value.all { letra ->
            _letrasAdivinadas.value.contains(letra) || letra == ' '
        }

        if (palabraCompleta) {
            _mensaje.value = "¡Ganaste! Adivinaste la palabra: ${_palabraSecreta.value}"
        }
    }

    fun reiniciarJuego() {
        _letrasAdivinadas.value.clear()
        _intentosRestantes.value = 6
        _letraIngresada.value = ""
        _palabraSecreta.value = getRandomWordUseCase()
        _mensaje.value = "Juego reiniciado. ¡Adivina la palabra!"
    }
}