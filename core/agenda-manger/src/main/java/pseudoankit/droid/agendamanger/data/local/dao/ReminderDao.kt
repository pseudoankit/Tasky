package pseudoankit.droid.agendamanger.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import pseudoankit.droid.agendamanger.data.local.entity.ReminderEntity
import java.time.LocalDate

@Dao
interface ReminderDao {

    @Query("Select * from reminderentity where date= :date")
    fun getReminders(date: LocalDate): Flow<List<ReminderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ReminderEntity)

    @Update
    suspend fun update(item: ReminderEntity)

    @Delete
    suspend fun delete(item: ReminderEntity)
}