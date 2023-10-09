package com.nkmgb.todoapp.di

import com.nkmgb.todoapp.repository.TodoLabelRepository
import com.nkmgb.todoapp.repository.TodoRepository
import com.nkmgb.todoapp.room.dao.TodoDao
import com.nkmgb.todoapp.room.dao.TodoLabelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository {
        return TodoRepository(todoDao)
    }

    @Singleton
    @Provides
    fun provideTodoLabelRepository(todoLabelDao: TodoLabelDao): TodoLabelRepository {
        return TodoLabelRepository(todoLabelDao)
    }
}
