package com.composeexpert.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.composeexpert.ui.screens.CharacterDetailScreen
import com.composeexpert.ui.screens.CharactersScreen
import com.composeexpert.ui.screens.ComicDetailScreen
import com.composeexpert.ui.screens.ComicsScreen
import com.composeexpert.ui.screens.EventDetailScreen
import com.composeexpert.ui.screens.EventsScreen

@ExperimentalMaterial3Api
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTERS.route,
    ) {
        charactersNav(navController)
        comicsNav(navController)
        eventsNav(navController)
    }
}

private fun NavGraphBuilder.charactersNav(navController: NavHostController) {
    navigation(
        startDestination = NavItem.ContentType(Feature.CHARACTERS).route,
        route = Feature.CHARACTERS.route,
    ) {
        composable(NavItem.ContentType(Feature.CHARACTERS)) {
            CharactersScreen(
                onClick = { character ->
                    navController.navigate(
                        NavItem.ContentTypeDetail(Feature.CHARACTERS).createRoute(character.id)
                    )
                }
            )
        }

        composable(NavItem.ContentTypeDetail(Feature.CHARACTERS)) { backStackEntry ->
            CharacterDetailScreen(
                characterId = backStackEntry.findArg(NavArg.ItemId),
                onBack = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.comicsNav(navController: NavController) {
    navigation(
        startDestination = NavItem.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavItem.ContentType(Feature.COMICS)) {
            ComicsScreen(
                onClick = { comic ->
                    navController.navigate(
                        NavItem.ContentTypeDetail(Feature.COMICS).createRoute(comic.id)
                    )
                }
            )
        }
        composable(NavItem.ContentTypeDetail(Feature.COMICS)) {
            val id = it.findArg<Int>(NavArg.ItemId)
            ComicDetailScreen(
                comicId = id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.eventsNav(navController: NavController) {
    navigation(
        startDestination = NavItem.ContentType(Feature.EVENTS).route,
        route = Feature.EVENTS.route
    ) {
        composable(NavItem.ContentType(Feature.EVENTS)) {
            EventsScreen(
                onClick = { event ->
                    navController.navigate(
                        NavItem.ContentTypeDetail(Feature.EVENTS).createRoute(event.id)
                    )
                }
            )
        }
        composable(NavItem.ContentTypeDetail(Feature.EVENTS)) {
            val id = it.findArg<Int>(NavArg.ItemId)
            EventDetailScreen(
                eventId = id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = navItem.route,
        arguments = navItem.args,
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}

