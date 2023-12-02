package com.example.vocalist.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vocalist.R
import com.example.vocalist.ui.nav.NavigationItem
import com.example.vocalist.ui.nav.Screen
import com.example.vocalist.ui.theme.MyVocalistComposeAppTheme
import com.example.vocalist.view.detail.DetailScreen
import com.example.vocalist.view.favorite.FavoriteScreen
import com.example.vocalist.view.main.MainScreen
import com.example.vocalist.view.profile.Profile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocalistApp(
    navHostController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == Screen.Main.route || currentRoute == Screen.Favorite.route || currentRoute == Screen.Profile.route) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        when (currentRoute) {
                            Screen.Main.route -> Text(stringResource(R.string.main_screen))
                            Screen.Favorite.route -> Text(stringResource(R.string.my_favorite))
                            Screen.Profile.route -> Text(stringResource(R.string.my_profile))
                        }
                    },
                )
            }
        },
        bottomBar = {
            if (currentRoute == Screen.Main.route || currentRoute == Screen.Favorite.route || currentRoute == Screen.Profile.route) {
                BottomBar(navHostController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = Screen.Main.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Main.route) {
                MainScreen { vocalistId ->
                    navHostController.navigate(Screen.Detail.createRoute(vocalistId))
                }
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(navigateToDetail = { vocalistId ->
                    navHostController.navigate(Screen.Detail.createRoute(vocalistId))
                })
            }
            composable(Screen.Profile.route) {
                Profile()
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("vocalistId") { type = NavType.LongType })
            ) {
                val id = it.arguments?.getLong("vocalistId") ?: -1L
                DetailScreen(
                    vocalistId = id,
                    navigateBack = {
                        navHostController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(R.string.favo),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = stringResource(R.string.home),
                icon = Icons.Default.Home,
                screen = Screen.Main
            ),
            NavigationItem(
                title = stringResource(R.string.profile),
                icon = Icons.Default.Person,
                screen = Screen.Profile
            )
        )
        navigationItem.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navHostController.navigate(item.screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }, icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = { Text(item.title)},)
        }
    }
}

@Preview
@Composable
fun VocalistAppPreview() {
    MyVocalistComposeAppTheme {
        VocalistApp()
    }
}