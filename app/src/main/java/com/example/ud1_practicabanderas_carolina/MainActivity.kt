package com.example.ud1_practicabanderas_carolina


import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ud1_practicabanderas_carolina.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AutonomiaAdapter
    private val autonomias = mutableListOf<Autonomia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadAutonomias()
    }

    private fun setupRecyclerView() {
        adapter = AutonomiaAdapter(
            autonomias,
            onItemClick = { autonomia ->
                Toast.makeText(this, "Yo soy de ${autonomia.nombre}", Toast.LENGTH_SHORT).show()
            },
            onItemLongClick = { autonomia ->
                showContextMenu(autonomia)
            }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun loadAutonomias() {
        // Aquí deberías cargar las autonomías reales
        autonomias.addAll(listOf(
            Autonomia(1, "Andalucía", R.drawable.andalucia),
            Autonomia(2, "Aragón", R.drawable.aragon),
            Autonomia(3, "Asturias", R.drawable.asturias),
            Autonomia(4, "Islas Baleares", R.drawable.baleares),
            Autonomia(5, "Canarias", R.drawable.canarias),
            Autonomia(6, "Cantabria", R.drawable.cantabria),
            Autonomia(7, "Castilla-La Mancha", R.drawable.castillamancha),
            Autonomia(8, "Castilla y León", R.drawable.castillaleon),
            Autonomia(9, "Cataluña", R.drawable.catalunya),
            Autonomia(10, "Extremadura", R.drawable.extremadura),
            Autonomia(11, "Galicia", R.drawable.galicia),
            Autonomia(12, "La Rioja", R.drawable.larioja),
            Autonomia(13, "Comunidad de Madrid", R.drawable.madrid),
            Autonomia(14, "Región de Murcia", R.drawable.murcia),
            Autonomia(15, "Navarra", R.drawable.navarra),
            Autonomia(16, "País Vasco", R.drawable.paisvasco),
            Autonomia(17, "Comunidad Valenciana", R.drawable.valencia),
            Autonomia(18, "Ceuta", R.drawable.ceuta),
            Autonomia(19, "Melilla", R.drawable.melilla),
            Autonomia(20, "España", R.drawable.spain)
        ))
        adapter.notifyDataSetChanged()
    }

    private fun showContextMenu(autonomia: Autonomia) {
        val options = arrayOf("Editar", "Eliminar")
        AlertDialog.Builder(this)
            .setItems(options) { _, which ->
                when (which) {
                    0 -> editAutonomia(autonomia)
                    1 -> deleteAutonomia(autonomia)
                }
            }
            .show()
    }

    private fun editAutonomia(autonomia: Autonomia) {
        val intent = Intent(this, EditAutonomiaActivity::class.java)
        intent.putExtra("autonomia_id", autonomia.id)
        intent.putExtra("autonomia_nombre", autonomia.nombre)
        startActivityForResult(intent, EDIT_AUTONOMIA_REQUEST)
    }

    private fun deleteAutonomia(autonomia: Autonomia) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar Autonomía")
            .setMessage("¿Estás seguro de que quieres eliminar ${autonomia.nombre}?")
            .setPositiveButton("Sí") { _, _ ->
                autonomias.remove(autonomia)
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("No", null)
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_recargar -> {
                loadAutonomias()
                true
            }
            R.id.action_limpiar -> {
                autonomias.clear()
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_AUTONOMIA_REQUEST && resultCode == RESULT_OK) {
            val id = data?.getIntExtra("autonomia_id", -1) ?: return
            val newName = data.getStringExtra("autonomia_nombre") ?: return
            val autonomia = autonomias.find { it.id == id } ?: return
            autonomia.nombre = newName
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        const val EDIT_AUTONOMIA_REQUEST = 1
    }
}