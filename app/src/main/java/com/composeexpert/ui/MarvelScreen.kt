package com.composeexpert.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.composeexpert.ui.navigation.AppBarIcon
import com.composeexpert.ui.navigation.AppBottomNavigation
import com.composeexpert.ui.navigation.NavItem
import com.composeexpert.ui.navigation.Navigation
import com.composeexpert.ui.navigation.navigatePoppingUpToStartDestination
import com.composeexpert.ui.theme.ComposeExpertTheme
import com.example.composeexpert.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""
    val showBackNavigation = currentRoute !in NavItem.entries.map { it.navCommand.route }

    MarvelScreen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.app_name)) },
                    navigationIcon = {
                        if (showBackNavigation) {
                            AppBarIcon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                onClick = { navController.popBackStack() },
                                contentDescription = "back"
                            )
                        }
                    }
                )
            },
            bottomBar = {
                AppBottomNavigation(
                    currentRoute = currentRoute,
                    onNavItemClick = { navItem ->
                        navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController)
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
