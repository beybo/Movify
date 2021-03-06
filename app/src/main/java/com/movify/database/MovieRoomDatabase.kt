package com.movify.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Pelicula::class, InfoLista::class, PeliRefLista::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieListDao?
    companion object{
        private var INSTANCE: MovieRoomDatabase? = null

        fun getDatabase(context: Context): MovieRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(MovieRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MovieRoomDatabase::class.java, "list_database.db"
                        ) // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            .createFromAsset("myapp.db")
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}