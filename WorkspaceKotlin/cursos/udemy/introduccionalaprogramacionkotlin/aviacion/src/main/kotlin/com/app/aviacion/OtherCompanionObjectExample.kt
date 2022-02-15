package com.app.aviacion

class OtherCompanionObjectExample private constructor(){

    companion object {
        private var instance: OtherCompanionObjectExample? = null

        fun getInstance() {
            if (instance == null)
                instance = OtherCompanionObjectExample()
            instance
        }
    }
}

fun main() {
    OtherCompanionObjectExample.getInstance()
}