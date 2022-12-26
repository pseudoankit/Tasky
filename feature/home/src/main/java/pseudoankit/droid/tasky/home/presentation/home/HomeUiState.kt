package pseudoankit.droid.tasky.home.presentation.home

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import pseudoankit.droid.core.util.datetime.DateUtils
import pseudoankit.droid.core.util.datetime.DateUtils.toString
import pseudoankit.droid.core.util.datetime.model.TaskyDate

internal interface HomeUiState {

    sealed interface SideEffect {
        object ShowDatePicker : SideEffect
        data class HighlightCurrentSelectedDate(val position: Int) : SideEffect
        object ShowAgendaItems : SideEffect
    }

    @Immutable
    data class State(
        val selectedDate: TaskyDate = TaskyDate.Today
    ) {
        val selectedMonthDateRange: ImmutableList<TaskyDate> =
            DateUtils.getDateRangeForMonth(selectedDate)

        val displayHeaderDate get() = selectedDate.toString("MMM yyyy").orEmpty()
    }
}