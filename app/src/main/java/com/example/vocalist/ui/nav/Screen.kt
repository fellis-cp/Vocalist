package com.example.vocalist.ui.nav

sealed class Screen (val route: String)
{
    object Main : Screen("main")
    object Detail : Screen("main/{vocalistId}") { fun createRoute (vocalistId : Long) = "main/$vocalistId"}
    object Profile : Screen("profile")
    object Favorite : Screen("favorite")
}