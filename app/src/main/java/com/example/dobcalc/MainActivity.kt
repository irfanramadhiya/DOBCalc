package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        val tvSelectedDate: TextView = findViewById(R.id.tvSelectedDate)
        val tvAgeInMinutes: TextView = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker(tvSelectedDate, tvAgeInMinutes)
        }

    }

    private fun clickDatePicker(tvSelectedDate: TextView, tvAgeInMinutes: TextView){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{_, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.text = selectedDate

            tvAgeInMinutes.text = getDifferenceInMinutes(selectedDate)
        },
            year, month, day)

        // set max date
        myCalendar.add(Calendar.DAY_OF_MONTH, -1)
        dpd.datePicker.maxDate = myCalendar.timeInMillis
        dpd.show()
    }

    private fun getDifferenceInMinutes(selectedDate: String):String{
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val theDate = sdf.parse(selectedDate)
        val selectedDateInMinutes = theDate.time / 60000
        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
        val currentDateInMinutes = currentDate.time / 60000
        return (currentDateInMinutes - selectedDateInMinutes).toString()
    }
}