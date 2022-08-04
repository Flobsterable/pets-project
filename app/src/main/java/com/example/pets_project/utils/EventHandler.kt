package com.example.pets_project.utils

interface EventHandler<E> {
    fun obtainEvent(event: E)
}
