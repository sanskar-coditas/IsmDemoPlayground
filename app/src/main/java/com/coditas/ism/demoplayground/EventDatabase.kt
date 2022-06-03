package com.coditas.ism.demoplayground

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Event::class], version = 1)
abstract class EventDatabase : RoomDatabase(){
    //this approach is thread safe (singleton approach)
    abstract fun eventDao(): EventDAO
    companion object{

        @Volatile   //whenever new value assigns to instance variable; this annotation  will notify all threads with the updated value
        private var INSTANCE : EventDatabase?=null

        fun getDatabase(context:Context): EventDatabase {
            if(INSTANCE ==null)
            {
               synchronized(this)   //if multiple thread locking mechanism to create only one instance even if two thread access it
                {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,      //database instance
                        EventDatabase::class.java,
                        "eventDB").build()
                }
            }
            return INSTANCE!!
        }
    }





}