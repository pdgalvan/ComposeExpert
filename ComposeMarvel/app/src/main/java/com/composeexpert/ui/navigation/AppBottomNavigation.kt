package com.composeexpert.ui.navigation


import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun AppBottomNavigation(
    currentRoute: String,
    onNavItemClick: (NavItem) -> Unit,
    bottomNavOptions: List<NavItem>,
) {
    NavigationBar {
        bottomNavOptions.forEach { item ->
            val title = stringResource(id = item.title)
            NavigationBarItem(
                selected = currentRoute == item.navCommand.route,
                onClick = {
                    onNavItemClick(item)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = title,
                    )
                },
                label = { Text(text = title) }
            )
        }
    }
}
