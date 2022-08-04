package com.example.pets_project.navigation.model

import com.example.pets_project.R

sealed class BottomBarItem(
    val route: String,
    val itemIconId: Int,
    val itemIconSelectedId: Int,
    val label: String
) {
    object ListTab : BottomBarItem(
        route = listBottomBarName,
        itemIconId = R.drawable.ic_list_bottom_bar_unselected,
        itemIconSelectedId = R.drawable.ic_list_bottom_bar_selected,
        label = "Объявления"
    )
    object AddTab : BottomBarItem(
        route = addBottomBarName,
        itemIconId = R.drawable.ic_add_bottom_bar_unselected,
        itemIconSelectedId = R.drawable.ic_add_bottom_bar_selected,
        label = "Создать объявления"
    )
    object ProfileTab : BottomBarItem(
        route = profileBottomBarName,
        itemIconId = R.drawable.ic_profile_bottom_bar_unselected,
        itemIconSelectedId = R.drawable.ic_profile_bottom_bar_selected,
        label = "Профиль"
    )
}
