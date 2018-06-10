package com.haretskiy.pavel.gifrandom.data

import com.haretskiy.pavel.gifrandom.rest.RestApiImpl

class RepositoryImpl(
        private val storage: Storage,
        private val restApi: RestApiImpl
) : Repository {

    override fun loadGifs() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}