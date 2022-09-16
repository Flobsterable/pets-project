package com.example.pets_project.ui.screens.main.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pets_project.navigation.model.BottomBarItem
import com.example.pets_project.utils.StringCallback
import com.example.pets_project.ui.theme.bottomBarButton
import com.example.pets_project.ui.theme.bottomBarButtonSelected
import com.example.pets_project.ui.theme.primary

@Composable
fun MainScreenBottomBar(
    currentRoute: String?,
    onItemClick: StringCallback
) {
    val items = listOf<BottomBarItem>(
        BottomBarItem.ListTab,
        BottomBarItem.AddTab,
        BottomBarItem.ProfileTab
    )

    BottomAppBar(
        modifier = Modifier.height(56.dp),
        contentColor = Color.White,
        backgroundColor = primary
    ) {

        items.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                alwaysShowLabel = true,
                onClick = { onItemClick.invoke(item.route) },
                icon = {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        when (currentRoute) {
                            item.route -> {
                                Icon(
                                    painterResource(id = item.itemIconSelectedId),
                                    contentDescription = item.route
                                )
                                Text(
                                    text = item.label.asString(),
                                    style = bottomBarButtonSelected
                                )
                            }
                            else -> {
                                Icon(
                                    painterResource(id = item.itemIconId),
                                    contentDescription = item.route
                                )
                                Text(
                                    text = item.label.asString(),
                                    style = bottomBarButton
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}
