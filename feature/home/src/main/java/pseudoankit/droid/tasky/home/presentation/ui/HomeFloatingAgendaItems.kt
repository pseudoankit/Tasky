package pseudoankit.droid.tasky.home.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import pseudoankit.droid.coreui.util.extension.asString
import pseudoankit.droid.coreui.util.extension.noRippleClickable
import pseudoankit.droid.tasky.home.domain.model.AgendaType
import pseudoankit.droid.unify.components.fab.UnifyFloatingButton
import pseudoankit.droid.unify.components.icon.UnifyIcon
import pseudoankit.droid.unify.components.textview.UnifyTextType
import pseudoankit.droid.unify.components.textview.UnifyTextView
import pseudoankit.droid.unify.token.UnifyColors
import pseudoankit.droid.unify.token.UnifyDimens

internal object HomeFloatingAgendaItems {

    @Composable
    operator fun invoke(
        onAgendaSelected: (AgendaType) -> Unit,
        onDismiss: () -> Unit,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .noRippleClickable(onDismiss)
                .padding(UnifyDimens.ScreenPadding),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            AgendaType.values().map {
                Spacer(modifier = Modifier.height(UnifyDimens.Dp_8))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    UnifyTextView(
                        config = UnifyTextView.Config(
                            text = it.label.asString(),
                            textType = UnifyTextType.TitleMedium,
                            color = UnifyColors.White,
                            fontStyle = FontStyle.Italic
                        )
                    )
                    Spacer(modifier = Modifier.width(UnifyDimens.Dp_8))
                    UnifyFloatingButton(
                        iconConfig = UnifyIcon.Config(icon = it.icon),
                        onClick = {
                            onAgendaSelected(it)
                        }
                    )
                }
            }
        }
    }
}