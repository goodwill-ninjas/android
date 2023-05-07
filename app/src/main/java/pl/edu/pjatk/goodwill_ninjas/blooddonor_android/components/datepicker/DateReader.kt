package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.datepicker

import android.util.Log

class DateReader(private var service: DatePickerService): DateObserver {
    override fun update(){
        Log.d("dateObserver", service.date.toString())
    }
}