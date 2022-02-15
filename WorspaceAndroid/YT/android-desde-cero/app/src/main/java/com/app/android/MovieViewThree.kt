package com.app.android

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * @JvmOverloads -> Le dice al compilador que cuando vaya a generar está clase
 * genere todas las distintas sobrecargas de este constructor
 */
class MovieViewThree @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attr, defStyleAttr) {

    init {

    }


    // Otra forma de hacerlo con constructores
//    constructor(context: Context): this(context, null)
//    constructor(context: Context, attr: AttributeSet?): this(context, attr, 0)
//    constructor(context: Context, attr: AttributeSet?, defStyleAttr: Int): super(context, attr, defStyleAttr)

}