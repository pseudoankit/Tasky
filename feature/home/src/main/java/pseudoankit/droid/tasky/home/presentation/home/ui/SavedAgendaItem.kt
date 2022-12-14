package pseudoankit.droid.tasky.home.presentation.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pseudoankit.droid.agendamanger.domain.model.AgendaItem
import pseudoankit.droid.unify.components.card.DraggableCard
import pseudoankit.droid.unify.components.icon.UnifyIcon
import pseudoankit.droid.unify.components.icon.UnifyIcons

internal object SavedAgendaItem {
    @Composable
    operator fun invoke(
        agendaItem: AgendaItem,
        onCompletionToggled: () -> Unit,
        onDelete: () -> Unit,
        onEdit: () -> Unit
    ) {
        Box(Modifier.wrapContentSize()) {
            ActionsItems(
                onDelete = onDelete,
                onEdit = onEdit
            )

            DraggableCard(
                cardOffset = 150f,
                revealThreshold = 30f
            ) {
                when (agendaItem) {
                    is AgendaItem.Event -> TODO()
                    is AgendaItem.Reminder -> AgendaReminderCard(
                        agendaItem = agendaItem,
                        onCompletionToggled = onCompletionToggled,
                    )
                    is AgendaItem.Task -> TODO()
                }
            }
        }
    }

    @Composable
    private fun BoxScope.ActionsItems(
        onDelete: () -> Unit,
        onEdit: () -> Unit
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.matchParentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UnifyIcon(UnifyIcon.Config(icon = UnifyIcons.Edit, onClick = onEdit))
            UnifyIcon(UnifyIcon.Config(icon = UnifyIcons.Delete, onClick = onDelete))
        }
    }
}