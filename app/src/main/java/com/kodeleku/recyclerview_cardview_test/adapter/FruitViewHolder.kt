package com.kodeleku.recyclerview_cardview_test.adapter

import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kodeleku.recyclerview_cardview_test.databinding.ItemFuitBinding
import com.kodeleku.recyclerview_cardview_test.model.Fruit
import com.squareup.picasso.Picasso

// Recibe una Vista como parámetro y hereda de ViewHolder, Implementamos la interfaz de View.OnCreateContextMenuListener para poder añadir items al menu
class FruitViewHolder (view: View): ViewHolder(view), View.OnCreateContextMenuListener {
    // En clases que no estan asociadas a activities, el binding se hace de la siguiente forma
    // Asociamos ItemFruitBinding a la vista que hereda de ViewHolder
    private val binding = ItemFuitBinding.bind(view)
    // Creamos una variable para la fruta que va a representar
    lateinit var fruit: Fruit

    // Función para pintar los datos de nuestro item (Fruit)
    fun render(item:Fruit, onClickListener: (Fruit)->Unit){
        // Inicializar item "fruit" para poder añadirlo en onCreateContextMenu
        fruit=item
        binding.tvFruit.text = item.name
        binding.tvDescription.text = item.description
        // Para la imagen utilizamos la libreria de Picasso
        // La cargamos y le asignamos 80x80, centrada y le asignamos el id del binding correspondiente a la vista (ivFruit)
        Picasso.get().load(item.photo).resize(80,80).centerInside().into(binding.ivFruit)

        itemView.setOnClickListener{
            onClickListener(item)
        }
        itemView.setOnCreateContextMenuListener(this)
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        // Al menu le ponemos un titulo con el nombre de la fruta al que hacemos longClick()
        menu!!.setHeaderTitle(fruit.name)
        menu.add(this.adapterPosition, 0, 0, "Eliminar")
        menu.add(this.adapterPosition, 1, 1, "Agregar")
    }


}