package com.nkmgb.todoapp.di

import android.content.Context
import androidx.room.Room
import com.nkmgb.todoapp.room.TodoDatabase
import com.nkmgb.todoapp.room.dao.TodoDao
import com.nkmgb.todoapp.room.dao.TodoLabelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
private object DatabaseModule {

    @Provides
    fun provideTodoDao(appDatabase: TodoDatabase): TodoDao {
        return appDatabase.todoDao()
    }

    @Provides
    fun provideTodoLabelDao(appDatabase: TodoDatabase): TodoLabelDao {
        return appDatabase.todoLabelDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "appDB"
        ).build()
    }
}
