package com.devstro.demofilms.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devstro.demofilms.feature.filmdetail.FilmDetailScreen
import com.devstro.demofilms.feature.home.HomeScreen

@Composable
fun FilmNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = FilmRoute.HOME,
        modifier = modifier
    ) {
        homeNav(navController)
        filmDetailNav()
    }
}

fun NavGraphBuilder.homeNav(navController: NavHostController) {
    composable(FilmRoute.HOME) {
        HomeScreen(onSelectedFilm = { id ->
            navController.navigate(
                FilmRoute.FILM_DETAIL.replace("{${FilmNavParam.FILM_ID}}", "$id")
            )
        })
    }
}

fun NavGraphBuilder.filmDetailNav() {
    composable(
        route = FilmRoute.FILM_DETAIL,
        arguments = listOf(
            navArgument(FilmNavParam.FILM_ID) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        backStackEntry.arguments?.getInt(FilmNavParam.FILM_ID)?.let {
            FilmDetailScreen(it)
        }
    }
}
