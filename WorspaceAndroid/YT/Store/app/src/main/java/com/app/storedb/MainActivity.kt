
package com.app.storedb

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.app.storedb.constants.SQLiteConstants
import com.app.storedb.domain.database.SQLite

class MainActivity : AppCompatActivity() {

    var txtCode: EditText? = null
    var txtDescription: EditText? = null
    var txtPrice: EditText? = null
    var ibtnSave: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtCode = findViewById(R.id.txtCode)
        txtDescription = findViewById(R.id.txtDescription)
        txtPrice = findViewById(R.id.txtPrice)
    }

    fun save(view: View) {
        // Instanciamos SQLite
        var conn = SQLite(this, "store", null, 1)
        var database = conn.writableDatabase
        var code = txtCode?.text.toString()
        var description = txtDescription?.text.toString()
        var price = txtPrice?.text.toString()

        if (code.isNotEmpty() && description.isNotEmpty() && price.isNotEmpty()) {
            var register = ContentValues()
            register.put("code", code)
            register.put("description", description)
            register.put("price", price)

            // Mandamos insertar esto
            database.insert(SQLiteConstants.TABLE_PRODUCTS, null, register)
            clearFields()
            showMessage("Saved successfully")
        } else {
            showMessage("Please, fill all fields")
        }
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun clearFields() {
        txtCode?.setText("")
        txtDescription?.setText("")
        txtPrice?.setText("")
    }
}