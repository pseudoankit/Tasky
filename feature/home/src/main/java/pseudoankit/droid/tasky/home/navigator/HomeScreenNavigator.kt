package pseudoankit.droid.tasky.home.navigator

import pseudoankit.droid.coreui.navigator.CoreNavigator

interface HomeScreenNavigator : CoreNavigator {
    fun navigateToAgendaItemsScreen()
    fun navigateToReminder()
    fun navigateToTasks()
    fun navigateToEvents()
}