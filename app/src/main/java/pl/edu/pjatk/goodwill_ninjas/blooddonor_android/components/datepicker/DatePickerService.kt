package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.datepicker

class DatePickerService: DateObservable {
    override val observers: ArrayList<DateObserver> = ArrayList()
    var date = 0L
        set(value){
            field = value
            sendUpdateEvent()
        }

}