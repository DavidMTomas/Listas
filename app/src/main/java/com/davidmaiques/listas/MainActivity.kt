package com.davidmaiques.listas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidmaiques.listas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {
    lateinit var userAdapter: UsuarioAdapter
    lateinit var linearLayoutManager: LinearLayoutManager // disposicion itms
    lateinit var binding: ActivityMainBinding // captura atributos
    lateinit var itemDecoration: DividerItemDecoration // personaliza los items
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        userAdapter =
            UsuarioAdapter(UsuarioDades.USUARIOS, this)// datos de la clase, no el main getUsers()
        linearLayoutManager = LinearLayoutManager(this)
        itemDecoration=DividerItemDecoration(this,1) //Linea horizontal

        // binding sobre RecyclerView
        binding.rvUser.apply {
            adapter = userAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(itemDecoration)
        }
    }
    override fun onClick(user: Usuario) {
        Toast.makeText(this,user.getNom(),Toast.LENGTH_LONG).show()
        Snackbar.make(binding.root,user.getCognom(),Snackbar.LENGTH_SHORT).show()
    }
    // datos en el main , se podria pasar como argumento del Usuario adapter
    private fun getUsers(): MutableList<Usuario> {
        val users = mutableListOf<Usuario>()
        val anna = Usuario(2, "anna", "sanchis", "")
        users.add(anna)
        return users
    }

}