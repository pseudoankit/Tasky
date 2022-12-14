package pseudoankit.droid.alarm_scheduler.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import pseudoankit.droid.alarm_scheduler.domain.model.Alarm
import pseudoankit.droid.alarm_scheduler.util.Constant
import pseudoankit.droid.core.logger.TaskyLogger

internal class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val data =
            intent?.extras?.getParcelable(Constant.Arguments.ALARM, Alarm::class.java) ?: return

        // TODO notification and navigation
        TaskyLogger.log("triggering alarm", data.toString())
    }
}