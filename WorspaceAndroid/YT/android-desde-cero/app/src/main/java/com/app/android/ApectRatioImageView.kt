package com.app.android

import android.content.Context
import android.util.AttributeSet

class ApectRatioImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {

    var ratio: Float = 1f

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ApectRatioImageView)
        // recuperamos el ratio
        ratio = attributes.getFloat(R.styleable.ApectRatioImageView_ratio, 1f)
        attributes.recycle()
    }


    /*
        Esta funcion se llama cuando estamos pintando una pantalla y queremos saber
        cual es el tamño final que va a tener está vista para decircelo a las vistas
        padre, pasara por esta función
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var width = measuredWidth
        var height = measuredHeight

        if (width == 0 && height == 0) {
            return
        }

        // Comprobar si la altura o la anchura es distinto de cero
        if (width > 0) {
            height = (width * ratio).toInt()
        }

        if (height > 0) {
            width = (height / ratio).toInt()
        }

        setMeasuredDimension(width, height)
    }

}