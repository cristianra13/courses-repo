package com.app.storedb.constants

class SQLiteConstants {
    companion object {
        val TABLE_PRODUCTS = "products"

        val CREATE_DATABASE = "create table $TABLE_PRODUCTS (" +
                "code int primary key," +
                "description text," +
                "price real)"
    }
}