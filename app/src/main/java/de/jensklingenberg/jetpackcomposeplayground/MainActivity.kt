package de.jensklingenberg.jetpackcomposeplayground

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import de.jensklingenberg.jetpackcomposeplayground.mysamples.demo.DemoActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, DemoActivity::class.java))
    }

    @ExperimentalAnimationApi
    @Composable
    fun appNavigator()  {
        val controller = rememberNavController()

        NavHost(navController = controller, startDestination = "pantalla_principal") {
            composable("pantalla_inicio"){
                PantalaInicial(controller)
            }
            composable("pantalla_principal"){
                ContenidoPrincipal(controller, himnos = CincoProvider.ultimosCincoLista)
            }
            composable("pantalla_mostrar_himno/{numero}",
                arguments = listOf(navArgument("numero"){type = NavType.IntType})
            )
            { backStactEntrada ->
                backStactEntrada.arguments?.getInt("numero")?.let { no ->
                    ContenidoMostrarHimno(
                        navController = controller,
                        himno = CoroLineaProvider.corolineaLista[no]
                    )
                }
            }
            composable("pantalla_ayuda"){
                PantallaAyuda(navController = controller)
            }
            composable("pantalla_favoritos"){
                PantallaFavoritos(navController = controller)
            }
            composable("pantalla_por_coro_linea"){
                PantallaPorCoroLineas(navController = controller)
            }
        }
    }

}
