package com.nativepractice.exercisew6_anmp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nativepractice.exercisew6_anmp.databinding.MotorListItemBinding
import com.nativepractice.exercisew6_anmp.model.Motor
import com.squareup.picasso.Picasso

class MotorListAdapter (val motorList:ArrayList<Motor>) :RecyclerView.Adapter<MotorListAdapter.MotorViewHolder>(){
    class MotorViewHolder(var binding: MotorListItemBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotorViewHolder {
        val binding = MotorListItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return MotorViewHolder(binding)
    }
    fun updateMotorList(newStudentList:ArrayList<Motor>){
        motorList.clear()
        motorList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return motorList.size
    }

    override fun onBindViewHolder(holder: MotorViewHolder, position: Int) {
        holder.binding.txtId.text = motorList[position].id ?:"N/A"
        holder.binding.txtName.text = motorList[position].name?:"N/A"
        holder.binding.txtBrand.text = motorList[position].brand?:"N/A"
        holder.binding.txtEngineType.text = motorList[position].engine?.let { engine ->
            engine.type ?: "N/A"
        } ?: "N/A"

        holder.binding.txtCc.text = motorList[position].engine?.let { engine ->
            engine.displacementCC ?: "N/A"
        } ?: "N/A"

        motorList[position].colors?.let { colors ->
            holder.binding.txtColor.text = colors.joinToString(", ")
        } ?: run {
            holder.binding.txtColor.text = "N/A"
        }
        val url = motorList[position].images
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener { picasso, uri, exception ->  exception.printStackTrace() }
        Picasso.get().load(url).into(holder.binding.imageView)
    }
}