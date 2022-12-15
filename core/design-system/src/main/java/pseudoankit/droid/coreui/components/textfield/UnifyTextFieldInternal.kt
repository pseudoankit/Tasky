package pseudoankit.droid.coreui.components.textfield

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pseudoankit.droid.coreui.components.icon.UnifyIcon
import pseudoankit.droid.coreui.components.icon.UnifyIcons
import pseudoankit.droid.coreui.components.text.UnifyTextType
import pseudoankit.droid.coreui.components.text.UnifyTextView
import pseudoankit.droid.coreui.token.UnifyColors
import pseudoankit.droid.coreui.token.UnifyDimens

internal object UnifyTextFieldInternal {

    @Composable
    fun UnifyTextField.Icon?.TrailingIcon(
        isTextHidden: Boolean,
        toggleIsTextHidden: () -> Unit
    ): @Composable (() -> Unit)? = this?.run {
        {
            when (this) {
                is UnifyTextField.Icon.Custom -> UnifyIcon(
                    config = UnifyIcon.Config(
                        tint = UnifyColors.Gray,
                        size = UnifyDimens.Dp_24,
                        icon = this.icon,
                        onClick = this.onClick
                    )
                )
                is UnifyTextField.Icon.Password -> UnifyIcon(
                    config = UnifyIcon.Config(
                        tint = UnifyColors.Gray,
                        size = UnifyDimens.Dp_24,
                        icon = if (isTextHidden) UnifyIcons.EyeOn else UnifyIcons.EyeOff,
                        onClick = toggleIsTextHidden
                    )
                )
                is UnifyTextField.Icon.Valid -> UnifyIcon(
                    config = UnifyIcon.Config(
                        tint = UnifyColors.Green,
                        size = UnifyDimens.Dp_24,
                        icon = UnifyIcons.Check
                    )
                )
            }
        }
    }


    @Composable
    fun UnifyIcons?.LeadingIcon(): @Composable (() -> Unit)? = this?.run {
        {
            UnifyIcon(
                config = UnifyIcon.Config(
                    tint = UnifyColors.Gray,
                    size = UnifyDimens.Dp_24,
                    icon = this
                )
            )
        }
    }

    @Composable
    fun String?.Label(): @Composable (() -> Unit)? = this?.run {
        {
            UnifyTextView(
                config = UnifyTextView.Config(
                    text = this,
                    textType = UnifyTextType.BodyLarge,
                    color = UnifyColors.DarkGray
                )
            )
        }
    }

    @Composable
    fun ErrorMessage(errorMessage: String?) {
        if (errorMessage == null) return

        Spacer(modifier = Modifier.height(UnifyDimens.Dp_2))
        UnifyTextView(
            config = UnifyTextView.Config(
                text = errorMessage,
                textType = UnifyTextType.BodySmall,
                color = UnifyColors.Error
            )
        )
    }
}