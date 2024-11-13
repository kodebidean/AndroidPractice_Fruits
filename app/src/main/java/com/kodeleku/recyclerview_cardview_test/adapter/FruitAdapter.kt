package com.kodeleku.recyclerview_cardview_test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kodeleku.recyclerview_cardview_test.R
import com.kodeleku.recyclerview_cardview_test.model.Fruit

class FruitAdapter (private val lista:List<Fruit>, private val onClickListener: (Fruit)->Unit ): RecyclerView.Adapter<FruitViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        // Creamos un layuoutInflater desde el contexto del padre
        val layoutInflater = LayoutInflater.from(parent.context)
        // Y le decimos que devuelva un FruitViewHolder inflando la celda que voy a pintar
        return FruitViewHolder(layoutInflater.inflate(R.layout.item_fuit, parent, false))
    }

    override fun getItemCount(): Int {
        // Devolvemos el tamaño de la lista
        return lista.size
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {

        val item = lista[position]
        // Le pasamos el item a holder para que lo pinte con el método render creado en nuestro FruitViewHolder
        holder.render(item, onClickListener)
    }

}