package pseudoankit.droid.tasky.home.presentation

import pseudoankit.droid.coreui.model.TaskyDate
import pseudoankit.droid.coreui.viewmodel.BaseViewModel
import java.time.LocalDate

internal class HomeViewModel :
    BaseViewModel<HomeUiState.State, HomeUiState.SideEffect, Nothing>(HomeUiState.State()) {

    fun onDaySelected(date: TaskyDate) = setState {
        copy(selectedDate = date)
    }

    fun onHeaderMonthSelected() = postSideEffect {
        HomeUiState.SideEffect.ShowDatePickerDialog
    }

    fun onDialogDateSelected(date: LocalDate) {
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