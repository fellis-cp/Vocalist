package com.example.vocalist.data

import com.example.vocalist.local.DataDao

class Repository(private val favoriteVocalistDao: DataDao){
    private val vocalist = mutableListOf<Vocalist>()

    init {
        if (vocalist.isEmpty()) {
            Data.dummyData.forEach{
                vocalist.add(it)
            }
        }
    }

    fun getVocalist () : List <Vocalist> {
        return vocalist
    }

    fun searchVocalist (query : String) : List<Vocalist> {
        return vocalist.filter {
            it.name.contains(query , ignoreCase = true)
        }
    }

    fun getVocalistId(vocalistId : Long) : Vocalist {
        return vocalist.first{
            it.id == vocalistId
        }
    }

    suspend fun saveFavVocalist(favoriteVocalist : Vocalist) {
        favoriteVocalistDao.insert(favoriteVocalist)
    }

    suspend fun delete (favoriteVocalist: Vocalist){
        favoriteVocalistDao.delete(favoriteVocalist)
    }

    suspend fun getFavVocalist() : List<Vocalist> {
        return favoriteVocalistDao.getAllVocalist()
    }

    fun isFavorite(vocalistId: Long) : Boolean =
        favoriteVocalistDao.isFavorite(vocalistId)

    companion object {
        @Volatile
        private var instance : Repository? = null

        fun getInstance (
            favoriteVocalistDao: DataDao ) : Repository = instance?: synchronized(this){
            Repository(favoriteVocalistDao).apply { instance = this }
        }
    }

}