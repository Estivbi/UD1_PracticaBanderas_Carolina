package com.example.ud1_practicabanderas_carolina

// AutonomiaAdapter.kt
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ud1_practicabanderas_carolina.databinding.ItemAutonomiaBinding

class AutonomiaAdapter(
    private var autonomias: List<Autonomia>,
    private val onItemClick: (Autonomia) -> Unit,
    private val onItemLongClick: (Autonomia) -> Unit
) : RecyclerView.Adapter<AutonomiaAdapter.AutonomiaViewHolder>() {

    inner class AutonomiaViewHolder(private val binding: ItemAutonomiaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(autonomia: Autonomia) {
            binding.textViewNombre.text = autonomia.nombre
            Glide.with(binding.root.context)
                .load(autonomia.bandera)
                .into(binding.imageViewBandera)

            binding.root.setOnClickListener { onItemClick(autonomia) }
            binding.root.setOnLongClickListener {
                onItemLongClick(autonomia)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutonomiaViewHolder {
        val binding = ItemAutonomiaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AutonomiaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AutonomiaViewHolder, position: Int) {
        holder.bind(autonomias[position])
    }

    override fun getItemCount() = autonomias.size

    fun updateData(newAutonomias: List<Autonomia>) {
        autonomias = newAutonomias
        notifyDataSetChanged()
    }
}