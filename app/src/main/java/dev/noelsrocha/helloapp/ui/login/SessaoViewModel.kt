package dev.noelsrocha.helloapp.ui.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.noelsrocha.helloapp.preferences.PreferencesKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SessaoViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {
    private val _uiState = MutableStateFlow(SessaoUiState())
    val uiState: StateFlow<SessaoUiState>
        get() = _uiState.asStateFlow()

    init {

    }

    suspend fun desloga() {
        dataStore.edit { preferences -> preferences[PreferencesKey.LOGADO] = false }
    }
}

data class SessaoUiState(val logado: Boolean = true)