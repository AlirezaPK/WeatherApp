package ir.kodato.weatherapp.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.kodato.weatherapp.data.remote.WeatherApi
import ir.kodato.weatherapp.data.repository.CityDataStoreImpl
import ir.kodato.weatherapp.data.repository.CityRepositoryImpl
import ir.kodato.weatherapp.data.repository.WeatherRepositoryImpl
import ir.kodato.weatherapp.domain.repository.CityDataStore
import ir.kodato.weatherapp.domain.repository.CityRepository
import ir.kodato.weatherapp.domain.repository.WeatherRepository
import ir.kodato.weatherapp.util.Constant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideCityDataStore(
        @ApplicationContext context: Context
    ): CityDataStore {
        return CityDataStoreImpl(context = context)
    }
}