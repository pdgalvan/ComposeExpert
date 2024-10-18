package com.composeexpert.ui

import android.util.Log
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.composeexpert.ui.navigation.NavItem
import com.composeexpert.ui.navigation.navigatePoppingUpToStartDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberMarvelAppState(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): MarvelAppState = remember(navController, drawerState, coroutineScope) {
    MarvelAppState(
        navController = navController,
        drawerState = drawerState,
        coroutineScope = coroutineScope,
    )
}

data class MarvelAppState(
    val navController: NavHostController,
    val drawerState: DrawerState,
    private val coroutineScope: CoroutineScope,
) {
    companion object {
        val DRAWER_OPTIONS = listOf(NavItem.HOME, NavItem.SETTINGS)
        val BOTTOM_NAV_OPTIONS = listOf(NavItem.CHARACTERS, NavItem.COMICS, NavItem.EVENTS)
    }

    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""

    val showBackNavigation: Boolean
        @Composable get() = !NavItem.entries.map { it.navCommand.route }.contains(currentRoute)

    val showBottomNavigation: Boolean
        @Composable get() = BOTTOM_NAV_OPTIONS.any { currentRoute.contains(it.navCommand.feature.route) }

    val drawerSelectedIndex: Int
        @Composable get() = if (showBottomNavigation) {
            DRAWER_OPTIONS.indexOf(NavItem.HOME)
        } else {
            DRAWER_OPTIONS.indexOfFirst { it.navCommand.route == currentRoute }
        }

    fun onBackClick() {
        navController.popBackStack()
    }

    fun onMenuClick() {
        coroutineScope.launch { drawerState.open() }
    }

    fun onNavItemClick(navItem: NavItem) {
        navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
    }

    fun onMenuOptionsClick(navItem: NavItem) {
        coroutineScope.launch { drawerState.close() }
        onNavItemClick(navItem)
    }

}
