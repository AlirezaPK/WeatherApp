package ir.kodato.weatherapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import ir.kodato.weatherapp.domain.repository.CityDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.cityDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "city_preferences"
)

class CityDataStoreImpl(
    context: Context
) : CityDataStore {

    private object PreferencesKey {
        val cityKey = stringPreferencesKey(name = "city_key")
        val cityScreenKey = booleanPreferencesKey(name = "city_screen_key")
    }

    private val dataStore = context.cityDataStore

    override suspend fun saveCity(cityName: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.cityKey] = cityName
        }
    }

    override fun readCity(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val cityState = preferences[PreferencesKey.cityKey] ?: ""
            cityState
        }
    }
}