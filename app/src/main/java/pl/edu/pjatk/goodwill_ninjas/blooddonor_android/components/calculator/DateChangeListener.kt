package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.calculator


import android.util.Log

class DateChangeListener: InterfaceRefreshValue {
    override fun onDateChanged(newDate: String){
        Log.i("text", newDate)}
}