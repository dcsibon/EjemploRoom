package com.dam23_24.ejemploroom.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.dam23_24.ejemploroom.TasksManageApp
import com.dam23_24.ejemploroom.addtasks.data.TaskDao
import com.dam23_24.ejemploroom.addtasks.data.TasksManageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideTaskDao(tasksManageDatabase: TasksManageDatabase): TaskDao {
        return tasksManageDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideTasksMAnageDatabase(@ApplicationContext appContext: Context): TasksManageDatabase {
        return Room.databaseBuilder(appContext, TasksManageDatabase::class.java, "TaskDatabase").build()
    }
}