package com.example.ud1_practicabanderas_carolina

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ud1_practicabanderas_carolina.databinding.ActivityEditAutonomiaBinding

class EditAutonomiaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditAutonomiaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAutonomiaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val autonomiaId = intent.getIntExtra("autonomia_id", -1)
        val autonomiaNombre = intent.getStringExtra("autonomia_nombre") ?: ""

        binding.editTextNombre.setText(autonomiaNombre)

        binding.buttonGuardar.setOnClickListener {
            val newName = binding.editTextNombre.text.toString()
            if (newName.isNotBlank()) {
                val resultIntent = Intent()
                resultIntent.putExtra("autonomia_id", autonomiaId)
                resultIntent.putExtra("autonomia_nombre", newName)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}