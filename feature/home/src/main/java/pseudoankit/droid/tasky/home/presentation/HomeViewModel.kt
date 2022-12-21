package pseudoankit.droid.tasky.home.presentation

import pseudoankit.droid.core.util.datetime.TaskyDate
import pseudoankit.droid.coreui.viewmodel.BaseViewModel
import pseudoankit.droid.tasky.home.domain.model.AgendaType
import java.time.LocalDate

internal class HomeViewModel :
    BaseViewModel<HomeUiState.State, HomeUiState.SideEffect, Nothing>(HomeUiState.State()) {

    fun onAgendaSelected(agendaType: AgendaType) = postSideEffect {
        onAgendaItemsVisibilityToggled()
        HomeUiState.SideEffect.NavigateToAgenda(agendaType)
    }

    fun onAgendaItemsVisibilityToggled() = setState {
        copy(showAgendaItems = showAgendaItems.not())
    }

    fun onDaySelected(date: TaskyDate) = setState {
        copy(selectedDate = date)
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
        HomeUiState.SideEffect.HighlightCurrentSelectedDate(state.selectedDate.date.dayOfMonth - 1)
    }
}