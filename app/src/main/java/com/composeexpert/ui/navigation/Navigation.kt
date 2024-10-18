package com.composeexpert.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.composeexpert.ui.screens.CharacterDetailScreen
import com.composeexpert.ui.screens.CharactersScreen
import com.composeexpert.ui.screens.ComicDetailScreen
import com.composeexpert.ui.screens.ComicsScreen
import com.composeexpert.ui.screens.EventDetailScreen
import com.composeexpert.ui.screens.EventsScreen
import com.composeexpert.ui.screens.SettingsScreen

@ExperimentalMaterial3Api
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTERS.route,
    ) {
        charactersNav(navController)
        comicsNav(navController)
        eventsNav(navController)
        composable(NavCommand.ContentType(Feature.SETTINGS)) {
            SettingsScreen()
        }
    }
}

private fun NavGraphBuilder.charactersNav(navController: NavHostController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.CHARACTERS).route,
        route = Feature.CHARACTERS.route,
    ) {
        composable(NavCommand.ContentType(Feature.CHARACTERS)) {
            CharactersScreen(
                onClick = { character ->
                    navController.navigate(
                        NavCommand.ContentTypeDetail(Feature.CHARACTERS).createRoute(character.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentTypeDetail(Feature.CHARACTERS)) { backStackEntry ->
            val id = backStackEntry.findArg<Int>(NavArg.ItemId)
            CharacterDetailScreen(characterId = id)
        }
    }
}

private fun NavGraphBuilder.comicsNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavCommand.ContentType(Feature.COMICS)) {
            ComicsScreen(
                onClick = { comic ->
                    navController.navigate(
                        NavCommand.ContentTypeDetail(Feature.COMICS).createRoute(comic.id)
                    )
                }
            )
        }
        composable(NavCommand.ContentTypeDetail(Feature.COMICS)) { backStackEntry ->
            val id = backStackEntry.findArg<Int>(NavArg.ItemId)
            ComicDetailScreen(comicId = id)
        }
    }
}

private fun NavGraphBuilder.eventsNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.EVENTS).route,
        route = Feature.EVENTS.route
    ) {
        composable(NavCommand.ContentType(Feature.EVENTS)) {
            EventsScreen(
                onClick = { event ->
                    navController.navigate(
                        NavCommand.ContentTypeDetail(Feature.EVENTS).createRoute(event.id)
                    )
                }
            )
        }
        composable(NavCommand.ContentTypeDetail(Feature.EVENTS)) { backStackEntry ->
            val id = backStackEntry.findArg<Int>(NavArg.ItemId)
            EventDetailScreen(eventId = id)
        }
    }
}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args,
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}

