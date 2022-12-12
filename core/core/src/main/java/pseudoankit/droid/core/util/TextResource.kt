package pseudoankit.droid.core.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

sealed interface TextResource {
    data class WithResId(internal val stringId: Int) : TextResource
    data class WithString(internal val text: String) : TextResource

    fun asString(context: Context): String {
        return when (this) {
            is WithResId -> context.resources.getString(stringId)
            is WithString -> text
        }
    }

    @Composable
    fun TextResource.asString(): String {
        return asString(LocalContext.current)
    }
}