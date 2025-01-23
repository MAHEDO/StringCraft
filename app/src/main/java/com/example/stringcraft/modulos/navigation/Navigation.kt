package com.example.stringcraft.modulos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stringcraft.modulos.About.AboutScreen
import com.example.stringcraft.modulos.Calculate.CalculateScreen
import com.example.stringcraft.modulos.Calculate.ResultsScreen
import com.example.stringcraft.modulos.Help.HelpScreen
import com.example.stringcraft.modulos.splash.Home.HomeScreen

@Composable
fun NavigationRoot(){
   //Nav host composable
   val navController = rememberNavController()

   //Nav graph
   NavHost(navController = navController, startDestination = ScreenRoot.HomeScreen.route){
      composable(ScreenRoot.HomeScreen.route){
         HomeScreen(navController)
      }

      composable(ScreenRoot.CalculateScreen.route){
         CalculateScreen(navController)
      }

      composable(
         route = ScreenRoot.ResultsScreen.route,
           arguments = listOf(
              navArgument("Scale") { type = NavType.StringType; defaultValue = "defaultScale" },
              navArgument("frets") { type = NavType.StringType; defaultValue = "defaultFrets" }
           )
         ) { backStackEntry ->
         val scale = backStackEntry.arguments?.getString("scale") ?: ""
         val frets = backStackEntry.arguments?.getString("frets") ?: ""
         ResultsScreen(navController, scale, frets)
      }

      composable(ScreenRoot.HelpScreen.route){
         HelpScreen(navController)
      }
      composable(ScreenRoot.AboutScreen.route){
         AboutScreen(navController)
      }
   }
}

sealed class ScreenRoot(val route: String) {
   object HomeScreen : ScreenRoot("Home")
   object CalculateScreen : ScreenRoot("Calculate")
   object ResultsScreen : ScreenRoot("Results/{scale}/{frets}") {
      fun createRoute(scale: String, frets: String): String {
         return "Results/$scale/$frets"
      }
   }

   object HelpScreen : ScreenRoot("Help")
   object AboutScreen : ScreenRoot("About")
}