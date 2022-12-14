package pseudoankit.droid.unify.components.icon

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

internal object UnifyIconInternalIcon {

    @Composable
    operator fun invoke(config: UnifyIcon.Config) {
        when (config.icon.iconType) {
            is UnifyIcons.IconType.FontAwesome -> FontAwesomeIcon(config = config)
            is UnifyIcons.IconType.Vector -> VectorIcon(config = config)
        }
    }

    @Composable
    private fun FontAwesomeIcon(config: UnifyIcon.Config) {
        val faIcon = (config.icon.iconType as? UnifyIcons.IconType.FontAwesome)?.faIcon ?: return

        lazycoder21.droid.compose.FontAwesomeIcon(
            faIcon = faIcon,
            modifier = config.modifier,
            size = config.size,
            tint = config.tint
        )
    }

    @Composable
    private fun VectorIcon(config: UnifyIcon.Config) {
        val imageVector =
            (config.icon.iconType as? UnifyIcons.IconType.Vector)?.imageVector ?: return
        Icon(
            imageVector = imageVector,
            contentDescription = config.contentDescription,
            modifier = config.modifier.size(config.size),
            tint = config.tint
        )
    }
}