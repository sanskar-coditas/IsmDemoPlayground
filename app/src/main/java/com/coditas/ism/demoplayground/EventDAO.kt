package com.coditas.ism.demoplayground

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao  // used to perform curd and other operations
interface EventDAO {
    @Insert
    suspend fun insertEvent(event: Event)

    @Update
    suspend fun updateContact(event: Event)

    @Delete
    suspend fun deleteContact(event: Event)

    @Query("SELECT * FROM event")
     fun getEvent():LiveData<List<Event>>
}