package com.theolm.gym_share.data.di

import com.theolm.gym_share.presentation.ui.common.ErrorHandler
import com.theolm.gym_share.presentation.ui.common.ErrorHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class VmModule {
    @Binds
    abstract fun bindsErrorHandler(errorHandler: ErrorHandlerImpl): ErrorHandler
}