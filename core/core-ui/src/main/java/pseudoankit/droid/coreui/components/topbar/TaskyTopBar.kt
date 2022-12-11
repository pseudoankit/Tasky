package pseudoankit.droid.coreui.components.topbar

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pseudoankit.droid.coreui.components.icon.TaskyIcon
import pseudoankit.droid.coreui.components.icon.TaskyIconConfig
import pseudoankit.droid.coreui.components.icon.TaskyIcons
import pseudoankit.droid.coreui.components.text.TaskyText
import pseudoankit.droid.coreui.components.text.TaskyTextConfig
import pseudoankit.droid.coreui.components.text.TaskyTextType
import pseudoankit.droid.coreui.util.TaskyColor
import pseudoankit.droid.coreui.util.TaskyDimens

data class TaskyTopBarConfig(
    val leadingIcon: TaskyIconConfig? = TaskyIconConfig(icon = TaskyIcons.Back),
    val title: String = "",
    val type: Type = Type.Small,
    val trailingSection: TrailingSection? = null,
    val tintColor: Color = TaskyColor.White
) {

    data class TrailingSection(
        val text: TaskyTextConfig?,
        val icon: TaskyIconConfig?,
        val modifier: Modifier = Modifier
    )

    enum class Type { Small }
}

@Composable
fun TaskyTopBar(config: TaskyTopBarConfig) {
    when (config.type) {
        TaskyTopBarConfig.Type.Small -> SmallTopBar(config = config)
    }
}

@Composable
private fun SmallTopBar(config: TaskyTopBarConfig) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(TaskyDimens.Dp_64)
            .padding(TaskyDimens.ScreenPadding)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
            TaskyIcon(
                config.leadingIcon?.copy(
                    tint = config.tintColor
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            config.trailingSection?.apply {
                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TaskyIcon(config = icon?.copy(tint = config.tintColor))
                    Spacer(modifier = Modifier.width(TaskyDimens.Dp_4))
                    TaskyText(config = text?.copy(color = config.tintColor))
                }
            }
        }

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            TaskyText(
                config = TaskyTextConfig(
                    text = config.title,
                    textType = TaskyTextType.TitleLarge,
                    color = config.tintColor
                )
            )
        }
    }
}