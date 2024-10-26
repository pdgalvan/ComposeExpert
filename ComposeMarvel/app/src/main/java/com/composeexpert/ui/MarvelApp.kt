package com.composeexpert.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.composeexpert.ui.navigation.AppBarIcon
import com.composeexpert.ui.navigation.AppBottomNavigation
import com.composeexpert.ui.navigation.DrawerContent
import com.composeexpert.ui.navigation.Navigation
import com.composeexpert.ui.theme.ComposeExpertTheme
import com.example.composeexpert.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelApp(appState: MarvelAppState = rememberMarvelAppState()) {
    val scrollState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(scrollState)

    MarvelScreen {
        ModalNavigationDrawer(
            drawerState = appState.drawerState,
            drawerContent = {
                DrawerContent(
                    drawerOptions = MarvelAppState.DRAWER_OPTIONS,
                    selectedIndex = appState.drawerSelectedIndex,
                    onOptionsClick = { appState.onMenuOptionsClick(it) }
                )
            },

            ) {
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(R.string.app_name)) },
                        navigationIcon = {
                            if (appState.showBackNavigation) {
                                AppBarIcon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    onClick = appState::onBackClick,
                                )
                            } else {
                                AppBarIcon(
                                    imageVector = Icons.Default.Home,
                                    onClick = { appState.onMenuClick() },
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                },
                bottomBar = {
                    if (appState.showBottomNavigation) {
                        AppBottomNavigation(
                            bottomNavOptions = MarvelAppState.BOTTOM_NAV_OPTIONS,
                            currentRoute = appState.currentRoute,
                            onNavItemClick = { appState.onNavItemClick(it) }
                        )
                    }
                },
            ) { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    Navigation(appState.navController)
                }
            }
        }
    }
}


@Composable
fun MarvelScreen(content: @Composable () -> Unit) {
    ComposeExpertTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}
