package pseudoankit.droid.agendamanger.domain.repository

import pseudoankit.droid.agendamanger.domain.model.AgendaItem

interface ReminderRepository {
    fun save(payload: AgendaItem.Reminder)
}