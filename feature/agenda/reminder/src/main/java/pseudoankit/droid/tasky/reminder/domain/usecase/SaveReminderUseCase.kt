package pseudoankit.droid.tasky.reminder.domain.usecase

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import pseudoankit.droid.agendamanger.domain.repository.ReminderRepository
import pseudoankit.droid.core.util.TaskyResult
import pseudoankit.droid.core.util.extension.safeCall
import pseudoankit.droid.tasky.reminder.presentation.ReminderUiState
import pseudoankit.droid.tasky.reminder.presentation.mapper.ReminderMapper.mapToReminderObj

internal class SaveReminderUseCase(
    private val repository: ReminderRepository,
    private val triggerAlarmUseCase: TriggerAlarmUseCase
) {

    // TODO: wait for entry to save in db then schedule alarm
    suspend operator fun invoke(state: ReminderUiState.State): TaskyResult<Unit> = safeCall(
        block = {
            val payload = state.mapToReminderObj
            coroutineScope {
                launch {
                    triggerAlarmUseCase(payload)
                }
                launch {
                    repository.save(payload)
                }
            }
            TaskyResult.Success(Unit)
        },
        onError = {
            TaskyResult.Error(it)
        }
    )
}