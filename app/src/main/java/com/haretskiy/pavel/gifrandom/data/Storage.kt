package com.haretskiy.pavel.gifrandom.data

interface Storage {
    fun addItem(url: String)
    fun deleteItem(url: String)
    fun contains(url: String): Boolean
    fun getAll(): List<String>
    fun clear()
    fun addItems(items: List<String>)
}