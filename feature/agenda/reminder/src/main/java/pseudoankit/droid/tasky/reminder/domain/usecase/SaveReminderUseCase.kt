package pseudoankit.droid.tasky.reminder.domain.usecase

import pseudoankit.droid.agendamanger.domain.model.AgendaItem
import pseudoankit.droid.agendamanger.domain.repository.ReminderRepository
import pseudoankit.droid.core.util.TaskyResult
import pseudoankit.droid.core.util.extension.safeCall
import pseudoankit.droid.tasky.reminder.presentation.ReminderUiState

internal class SaveReminderUseCase(
    private val repository: ReminderRepository
) {

    suspend operator fun invoke(state: ReminderUiState.State): TaskyResult<Unit> = safeCall(
        block = {
            val payload = state.run {
                AgendaItem.Reminder(
                    title = reminderText,
                    remindAllDay = remindAllDay,
                    date = selectedDate.value,
                    time = selectedTime?.value,
                    repeatInterval = repeatIntervalItems.firstOrNull { it.isSelected }?.item
                        ?: AgendaItem.Reminder.RepeatInterval.DoNotRepeat
                )
            }
            repository.save(payload)
            TaskyResult.Success(Unit)
        },
        onError = {
            TaskyResult.Error(it)
        }
    )
}