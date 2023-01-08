package pseudoankit.droid.tasky.home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import pseudoankit.droid.agendamanger.domain.model.AgendaItem
import pseudoankit.droid.core.model.TaskyDate
import pseudoankit.droid.coreui.util.extension.launch
import pseudoankit.droid.coreui.util.extension.postSideEffect
import pseudoankit.droid.coreui.util.extension.setState
import pseudoankit.droid.coreui.util.extension.state
import pseudoankit.droid.tasky.home.domain.usecase.DeleteAgendaUseCase
import pseudoankit.droid.tasky.home.domain.usecase.GetSavedAgendaItemsUseCase
import pseudoankit.droid.tasky.home.domain.usecase.ToggleAgendaItemCompletionUseCase
import java.time.LocalDate

internal class HomeViewModel(
    private val getSavedAgendaItemsUseCase: GetSavedAgendaItemsUseCase,
    private val toggleAgendaItemCompletionUseCase: ToggleAgendaItemCompletionUseCase,
    private val deleteAgendaUseCase: DeleteAgendaUseCase
) : ViewModel(),
    ContainerHost<HomeUiState.State, HomeUiState.SideEffect> {

    private var agendaItemsJob: CoroutineScope? = null

    override val container: Container<HomeUiState.State, HomeUiState.SideEffect> =
        viewModelScope.container(HomeUiState.State())

    init {
        loadAgendaItemsForSelectedDate()
    }

    fun onShowAgendaItems() = postSideEffect {
        HomeUiState.SideEffect.ShowAgendaItems
    }

    fun onDaySelected(date: TaskyDate) {
        setState { copy(selectedDate = date) }
        loadAgendaItemsForSelectedDate()
    }

    private fun loadAgendaItemsForSelectedDate() {
        agendaItemsJob?.cancel()
        agendaItemsJob = CoroutineScope(Dispatchers.IO)
        getSavedAgendaItemsUseCase.invoke(state.selectedDate)
            .onEach {
                setState { copy(savedAgendaItems = it) }
            }
            .launchIn(agendaItemsJob!!)
    }

    fun onDelete(agenda: AgendaItem) = launch {
        deleteAgendaUseCase(agenda)
    }

    fun onEdit(agenda: AgendaItem) = postSideEffect {
        HomeUiState.SideEffect.NavigateToAgendaScreen(agenda)
    }

    fun onAgendaItemCompletionToggle(agenda: AgendaItem) = launch {
        toggleAgendaItemCompletionUseCase.invoke(agenda)
    }

    fun onHeaderMonthSelected() = postSideEffect {
        HomeUiState.SideEffect.ShowDatePicker
    }

    fun onDateChanged(date: LocalDate) {
        setState {
            copy(selectedDate = TaskyDate(date))
        }
        highlightCurrentSelectedDate()
    }

    fun onInit() {
        highlightCurrentSelectedDate()
    }

    private fun highlightCurrentSelectedDate() = postSideEffect {
        HomeUiState.SideEffect.HighlightCurrentSelectedDate(state.selectedDate.value.dayOfMonth - 1)
    }
}