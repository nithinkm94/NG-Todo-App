package com.nkmgb.todoapp.ui.content.di

import com.nkmgb.todoapp.ui.content.reducer.addtodo.AddTodoScreenReducer
import com.nkmgb.todoapp.ui.content.reducer.addtodo.AddTodoScreenReducerImpl
import com.nkmgb.todoapp.ui.content.reducer.todo.TodoScreenReducer
import com.nkmgb.todoapp.ui.content.reducer.todo.TodoScreenReducerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContentScreenStateModule {

    @Singleton
    @Provides
    fun provideAddTodoScreenReducer(): AddTodoScreenReducer {
        return AddTodoScreenReducerImpl()
    }

    @Singleton
    @Provides
    fun provideTodoScreenReducer(): TodoScreenReducer {
        return TodoScreenReducerImpl()
    }
}
