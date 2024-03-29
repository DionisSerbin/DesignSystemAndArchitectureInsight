package ru.serbin.features.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.serbin.features.data.mockApi.FeaturesInteractor
import ru.serbin.features.data.mockApi.FeaturesInteractorImpl
import ru.serbin.features.domain.repository.FeaturesRepository
import ru.serbin.features.domain.repository.FeaturesRepositoryImpl
import ru.serbin.features.enabledfeature.domain.usecases.EnabledUsecases
import ru.serbin.features.enabledfeature.domain.usecases.GetEnabledFeatures
import ru.serbin.features.enabledfeature.domain.usecases.UpdateEnabledFeaturesFromSettings
import ru.serbin.features.settingsfeatures.domain.usecases.ChangeFeatureStateByName
import ru.serbin.features.settingsfeatures.domain.usecases.GetAllFeatures
import ru.serbin.features.settingsfeatures.domain.usecases.SettingsUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FeaturesModule {

    @Provides
    @Singleton
    fun provideFeaturesInteractor(): FeaturesInteractor {
        return FeaturesInteractorImpl()
    }

    @Provides
    @Singleton
    fun provideFeaturesRepository(featureInteractor: FeaturesInteractor): FeaturesRepository {
        return FeaturesRepositoryImpl(
            featuresInteractor = featureInteractor
        )
    }

    @Provides
    @Singleton
    fun provideGetEnabledFeatures(featuresRepository: FeaturesRepository): GetEnabledFeatures {
        return GetEnabledFeatures(
            featuresRepository = featuresRepository
        )
    }

    @Provides
    @Singleton
    fun provideUpdateEnabledFeaturesFromSettings(): UpdateEnabledFeaturesFromSettings {
        return UpdateEnabledFeaturesFromSettings()
    }

    @Provides
    @Singleton
    fun provideEnabledUseCases(
        getEnabledFeatures: GetEnabledFeatures,
        updateEnabledFeaturesFromSettings: UpdateEnabledFeaturesFromSettings
    ): EnabledUsecases {
        return EnabledUsecases(
            getEnabledFeatures = getEnabledFeatures,
            updateEnabledFeaturesFromSettings = updateEnabledFeaturesFromSettings
        )
    }

    @Provides
    @Singleton
    fun provideGetAllFeatures(featuresRepository: FeaturesRepository): GetAllFeatures {
        return GetAllFeatures(
            featuresRepository = featuresRepository
        )
    }

    @Provides
    @Singleton
    fun provideChangeFeatureByName(): ChangeFeatureStateByName {
        return ChangeFeatureStateByName()
    }

    @Provides
    @Singleton
    fun provideSettingsUseCases(
        getAllFeatures: GetAllFeatures,
        changeFeatureStateByName: ChangeFeatureStateByName
    ): SettingsUseCases {
        return SettingsUseCases(
            getAllFeatures = getAllFeatures,
            changeFeatureStateByName = changeFeatureStateByName
        )
    }
}