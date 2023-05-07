package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.datepicker

interface DateObservable {
    val observers: ArrayList<DateObserver>
    fun add(observer: DateObserver) {
        observers.add(observer)
    }
    fun remove(observer: DateObserver) {
        observers.remove(observer)
    }
    fun sendUpdateEvent() {
        observers.forEach { it.update() }
    }
}
