package com.example.mapaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.maplibre.android.MapLibre
import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style

class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

    // Proveedor de tiles: OpenFreeMap (libre, sin API key, sin registro).
    // Para cambiar de proveedor en el futuro, solo se modifica esta constante.
    private companion object {
        const val ESTILO_MAPA = "https://tiles.openfreemap.org/styles/liberty"
        const val PUNTO_INICIAL_LAT = -34.6037
        const val PUNTO_INICIAL_LNG = -58.3816
        const val ZOOM_INICIAL = 13.0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obligatorio: inicializar MapLibre antes de inflar el MapView
        MapLibre.getInstance(this)

        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync { map ->
            map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(PUNTO_INICIAL_LAT, PUNTO_INICIAL_LNG),
                    ZOOM_INICIAL
                )
            )

            map.setStyle(Style.Builder().fromUri(ESTILO_MAPA)) {
                // Estilo cargado: aquí agregaremos funcionalidad más adelante
            }
        }
    }

    // ─── Ciclo de vida obligatorio del MapView ───
    override fun onStart() { super.onStart(); mapView.onStart() }
    override fun onResume() { super.onResume(); mapView.onResume() }
    override fun onPause() { mapView.onPause(); super.onPause() }
    override fun onStop() { mapView.onStop(); super.onStop() }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
    override fun onLowMemory() { super.onLowMemory(); mapView.onLowMemory() }
    override fun onDestroy() { mapView.onDestroy(); super.onDestroy() }
}
