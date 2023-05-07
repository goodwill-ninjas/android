package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.calculator


import kotlin.properties.Delegates

class DateListener {
    var listener: InterfaceRefreshValue? = null
    var text: String by Delegates.observable("") { _, _, new ->
        listener?.onDateChanged(new)
    }
}