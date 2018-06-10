package com.haretskiy.pavel.gifrandom.data

class StorageImpl : Storage {

    private val list = ArrayList<String>()

    override fun addItem(url: String) {
        if (!contains(url)) list.add(url)
    }

    override fun deleteItem(url: String) {
        list.remove(url)
    }

    override fun contains(url: String) = list.contains(url)

    override fun clear() {
        list.clear()
    }

    override fun getAll() = list

    override fun addItems(items: List<String>) {
        list.addAll(items)
        //Todo: contains????
    }
}