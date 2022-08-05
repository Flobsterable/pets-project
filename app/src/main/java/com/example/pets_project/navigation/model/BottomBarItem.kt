package com.example.pets_project.navigation.model

import com.example.pets_project.R
import com.example.pets_project.utils.TextUI

sealed class BottomBarItem(
    val route: String,
    val itemIconId: Int,
    val itemIconSelectedId: Int,
    val label: TextUI
) {
    object ListTab : BottomBarItem(
        route = listBottomBarName,
        itemIconId = R.drawable.ic_list_bottom_bar_unselected,
        itemIconSelectedId = R.drawable.ic_list_bottom_bar_selected,
        label = TextUI.Resource(R.string.bn_list)
    )
    object AddTab : BottomBarItem(
        route = addBottomBarName,
        itemIconId = R.drawable.ic_add_bottom_bar_unselected,
        itemIconSelectedId = R.drawable.ic_add_bottom_bar_selected,
        label = TextUI.Resource(R.string.bn_add)
    )
    object ProfileTab : BottomBarItem(
        route = profileBottomBarName,
        itemIconId = R.drawable.ic_profile_bottom_bar_unselected,
        itemIconSelectedId = R.drawable.ic_profile_bottom_bar_selected,
        label = TextUI.Resource(R.string.bn_profile)
    )
}
