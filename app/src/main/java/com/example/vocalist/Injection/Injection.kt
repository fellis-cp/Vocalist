package com.example.vocalist.Injection

import android.content.Context
import com.example.vocalist.data.Repository
import com.example.vocalist.local.VocalistDb

object Injection {

    fun provideRepository (context : Context) : Repository {
        val database = VocalistDb.getDb(context)
        val dao = database.favoriteVocalistDao()
        return Repository.getInstance(dao)
    }
}