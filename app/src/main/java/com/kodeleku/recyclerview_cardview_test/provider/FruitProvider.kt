package com.kodeleku.recyclerview_cardview_test.provider

import com.kodeleku.recyclerview_cardview_test.R
import com.kodeleku.recyclerview_cardview_test.model.Fruit

class FruitProvider {
    // Importante insertar el companion object para que la clase sea accesible externamente
    companion object {
        fun cargarLista(): MutableList<Fruit> {
            return mutableListOf<Fruit>(
                Fruit("Fresas", "Descripción para fresa", R.drawable.strawberry_bg),
                Fruit("Manzanas", "Descripción para manzanas", R.drawable.apple_bg),
                Fruit("Peras", "Descripción para peras", R.drawable.pear_bg),
                Fruit("Bananas", "Descripción para bananas", R.drawable.banana_bg),
                Fruit("Naranjas", "Descripción para naranjas", R.drawable.orange_bg),
                Fruit("Frambuesas", "Descripción para frambuesas", R.drawable.raspberry_bg)
            )
        }
    }
}