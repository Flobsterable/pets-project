package com.example.pets_project.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class TextUI {
    data class Resource(@StringRes val resId: Int) : TextUI()
    data class ResourceParams(
        @StringRes val resId: Int,
        val args: List<Any>
    ) : TextUI()
    data class Simple(val text: String) : TextUI()

    @Composable
    fun asString(): String {
        return when (this) {
            is Resource -> stringResource(id = resId)
            is ResourceParams -> stringResource(id = resId, args)
            is Simple -> text
        }
    }
}
