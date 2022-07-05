package com.example.pets_project.ui.screens.login.model

interface EventHandler<E> {
    fun obtainEvent(event: E)
}