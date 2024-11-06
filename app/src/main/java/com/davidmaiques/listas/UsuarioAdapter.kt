package com.davidmaiques.listas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.davidmaiques.listas.databinding.ItemUserBinding

class UsuarioAdapter(private val lista: List<Usuario>, private val listener: OnClickListener) :
    RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {
    //Clase viewHolder
    lateinit var context: Context
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUserBinding.bind(view)
        fun render(usuario: Usuario) {
            setListener(usuario)//le pasamos la funcion listener
            binding.nombre.text = usuario.getNom()
            binding.numero.text = usuario.getId().toString()
            binding.apellido.text = usuario.getCognom()
            // mostrar url de google
            Glide.with(context).load(usuario.getImatge())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgFoto)
        }
        //LISTENER
        fun setListener(user:Usuario){
            //activar listener a cada item
            binding.root.setOnClickListener {
                listener.onClick(user)
            }
        }
    }
    //Metoods sobreescritos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }
    override fun getItemCount() = lista.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.render(lista[position])
    }
}