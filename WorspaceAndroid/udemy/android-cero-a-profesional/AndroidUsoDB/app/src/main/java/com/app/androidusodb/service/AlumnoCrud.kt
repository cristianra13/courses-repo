package com.app.androidusodb.service

import android.content.Context
import com.app.androidusodb.helpers.DatabaseHelper
import com.app.androidusodb.models.Alumno

class AlumnoCrud(context: Context) {

    // creamos la instancia del helper
    private var helper = DatabaseHelper

    init {
        helper = DatabaseHelper(context = context)
    }

    fun newAlumno(alumno: Alumno) {

    }

}