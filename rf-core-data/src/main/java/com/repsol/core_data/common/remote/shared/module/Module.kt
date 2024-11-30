package com.repsol.core_data.common.remote.shared.module

import com.repsol.core_data.common.remote.ApiCreator
import com.repsol.core_data.common.remote.shared.api.SharedApi
import com.repsol.core_data.common.remote.shared.data_source.DefaultSharedRemoteDS
import com.repsol.core_data.common.remote.shared.data_source.SharedRemoteDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideSharedApi(
        apiCreator: ApiCreator
    ): SharedApi = apiCreator.create()


    @Provides
    fun provideSharedRemoteDS(
        remoteDS: DefaultSharedRemoteDS
    ): SharedRemoteDS = remoteDS
}