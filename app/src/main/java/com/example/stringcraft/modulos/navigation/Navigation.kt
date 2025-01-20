package com.example.stringcraft.modulos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stringcraft.modulos.Calculate.CalculateScreen
import com.example.stringcraft.modulos.splash.Home.HomeScreen
import com.example.stringcraft.modulos.splash.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun NavigationRoot(){
   //Nav host composable
   val navController = rememberNavController()

   //Nav graph
   NavHost(navController = navController, startDestination = ScreenRoot.SplashScreen.route){
      composable(ScreenRoot.SplashScreen.route){

         LaunchedEffect(key1 = null) {
             delay(timeMillis = 3000)
             navController.popBackStack()
             navController.navigate(ScreenRoot.HomeScreen.route)
         }
         SplashScreen()
      }

      composable(ScreenRoot.HomeScreen.route){
         HomeScreen(navController)
      }

      composable(ScreenRoot.CalculateScreen.route){
         CalculateScreen()
      }
   }
}

sealed class ScreenRoot(val route: String){
   object SplashScreen : ScreenRoot("Splash")
   object HomeScreen : ScreenRoot("Home")
   object CalculateScreen : ScreenRoot("Calculate")
}