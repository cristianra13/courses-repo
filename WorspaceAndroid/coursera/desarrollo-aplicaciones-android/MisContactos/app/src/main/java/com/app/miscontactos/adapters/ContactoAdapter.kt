package com.app.miscontactos.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.app.miscontactos.DetalleContacto
import com.app.miscontactos.R
import com.app.miscontactos.models.Contacto

// ContactoAdapter lo que hara es pasarle los valores a ContactoViewHolder
class ContactoAdapter(activity: Activity, contactos: MutableList<Contacto>) : Adapter<ContactoAdapter.Companion.ContactoViewHolder> () {

    private var contactos: MutableList<Contacto> = contactos
    private var activity: Activity = activity

    /**
     * Da la vida al layout cardview llenandolo
     * Infla el layout y lo pasa al view holder para que obtenga cada elemento
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        // pasamos el layou cardview_contacto inflado, como un view a ContactoViewHolder(view)
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.cardview_contacto, parent, false)
        return ContactoViewHolder(view)
    }

    /**
     * Pasamos la lista de contactos a cada elemento del viewholder
     */
    override fun onBindViewHolder(contactoViewHolder: ContactoViewHolder, position: Int) {
        /*
            Se recorre la lista por cada posicion y se envían al holder
         */
        var contacto: Contacto = contactos[position]
        contactoViewHolder.imgFoto.setImageResource(contacto.foto)
        contactoViewHolder.tvNombreCV.setText(contacto.nombre)
        contactoViewHolder.tvTelefonoCV.setText(contacto.telefono)

        contactoViewHolder.btnLike.setOnClickListener {
            Toast.makeText(activity, "diste like a ${contacto.nombre}", Toast.LENGTH_LONG).show()
        }

        contactoViewHolder.imgFoto.setOnClickListener {
            // Al Hacer click sobre la imagen, muestra toas con mensaje
            Toast.makeText(activity, contacto.nombre, Toast.LENGTH_LONG).show()
            // Enviamos la data del contacto a la siguiente activity
            var intent: Intent = Intent(activity, DetalleContacto::class.java)
            intent.putExtra("nombre",  contacto.nombre) // Enviamos parametros
            intent.putExtra("telefono",  contacto.telefono) // Enviamos parametros
            intent.putExtra("email",  contacto.email) // Enviamos parametros
            // iniciamos la activity
            activity.startActivity(intent)
        }
    }

    /**
     * Cantidad de elementos que contiene la lista
     */
    override fun getItemCount(): Int {
        return contactos.size
    }

    companion object {

        // inner class ViewHolder con constructor igual a la superclase
        class ContactoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            // declaramos todos los views que tenemos en el cardview
            var imgFoto: ImageView = itemView.findViewById(R.id.ivFotoContactoCardView)
            var tvNombreCV: TextView = itemView.findViewById(R.id.tvNombreCardView)
            var tvTelefonoCV: TextView = itemView.findViewById(R.id.tvTelefonoCardView)
            var btnLike: ImageButton = itemView.findViewById(R.id.btnLike)
        }
    }
}