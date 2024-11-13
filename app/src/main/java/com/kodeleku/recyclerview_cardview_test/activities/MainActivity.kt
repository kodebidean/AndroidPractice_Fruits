package com.kodeleku.recyclerview_cardview_test.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kodeleku.recyclerview_cardview_test.R
import com.kodeleku.recyclerview_cardview_test.adapter.FruitAdapter
import com.kodeleku.recyclerview_cardview_test.databinding.ActivityMainBinding
import com.kodeleku.recyclerview_cardview_test.model.Fruit
import com.kodeleku.recyclerview_cardview_test.provider.FruitProvider

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding: ActivityMainBinding get() = _binding
    // Instanciamos nuestra lista de frutas
    private lateinit var fruitList: MutableList<Fruit>
    // Instanciamos un LinearLayoutManager para nuestro recyclerView
    private lateinit var layoutManager: LinearLayoutManager
    // Instanciamos un FruitAdapter
    private lateinit var adapter: FruitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cargamos la lista de frutas a traves de FruitProvider
        fruitList = FruitProvider.cargarLista()

        // Asignamos la lógica de nuestro Main a nuestro LayoutManager
        layoutManager = LinearLayoutManager(this)
        // Le asignamos el recyclerView al layoutManager
        binding.rvFruits.layoutManager = layoutManager

        // Le damos como parametro a la instancia "adapter" nuestra lista de frutas
        adapter = FruitAdapter(fruitList) { fruit ->
            onItemSelected(fruit)
        }
        // Vinculamos nuestro recyclerView
        binding.rvFruits.adapter = adapter

    }

    private fun onItemSelected(fruit:Fruit) {
        Toast.makeText(this,"has pulsado sobre ${fruit.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        // Elemento sobre el que se hace click
        val fruitSeleccionada = fruitList[item.groupId]
        // ¿Que opción se ha seleccionado? -> 0 = Eliminar "fruitName" (estas seguro?)
        when (item.itemId){
            // option 1 -> Eliminar Fruta Seleccionada
           0 -> {
               val alert = AlertDialog.Builder(this).setTitle("Eliminar ${fruitSeleccionada.name}")
                   .setMessage("¿Estás seguro de que quieres eliminar ${fruitSeleccionada.name}")
                   // botón para cancelar
                   .setNeutralButton("Cancelar", null)
                   // botón para aceptar y respuesta.
                   .setPositiveButton("Aceptar") { _, _ ->
                       showMsg("Se ha eliminado ${fruitSeleccionada.name}")
                       fruitList.removeAt(item.groupId)
                       adapter.notifyItemRemoved(item.groupId)
                   }.create()
               alert.show()
           }
            // option 2 -> Añadir nueva Fruta
            1 -> {
                val newFruit = Fruit("Nueva fruta", "fruta nueva añadida", R.drawable.plum_bg)
                fruitList.add(newFruit)
                adapter.notifyItemInserted(fruitList.size)
            }


        }
        // Debe devolver un valor boolean
        return true
    }
    private fun showMsg(mensaje:String){
        Snackbar.make(binding.root,mensaje,Snackbar.LENGTH_SHORT).show()
    }

}