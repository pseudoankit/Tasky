package pseudoankit.droid.tasky.home.domain.model

import pseudoankit.droid.core.util.TextResource
import pseudoankit.droid.unify.components.icon.UnifyIcons

enum class AgendaType {
    Reminder, Task, Event;

    val label
        get() = when (this) {
            Reminder -> TextResource.NormalString(Reminder.name)
            Task -> TextResource.NormalString(Task.name)
            Event -> TextResource.NormalString(Event.name)
        }

    val icon
        get() = when (this) {
            Reminder -> UnifyIcons.Bell
            Task -> UnifyIcons.Tasks
            Event -> UnifyIcons.Calendar
        }
}