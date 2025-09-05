package com.example.datepicker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.datepicker.databinding.ActivityMainBinding
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var dataPickerDiologue: DatePickerDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.et1.requestFocus()
        binding.et1.setOnClickListener {
            openTimePicker()
        }

    }

    private fun openDatePicker() {
        val cldr = Calendar.getInstance()
        val day = cldr[Calendar.DAY_OF_MONTH]
        val month = cldr[Calendar.MONTH]
        val year = cldr[Calendar.YEAR]


        dataPickerDiologue =
            DatePickerDialog(this@MainActivity, { view, year, monthOfYear, dayOfMonth ->
                val month = monthOfYear + 1
                var strMonth = "" + month
                var strDayOfMonth = "" + dayOfMonth
                if (month < 10) {
                    strMonth = "0$strMonth"
                }
                if (dayOfMonth < 10) {
                    strDayOfMonth = "0$dayOfMonth"
                }
                val date = "$strDayOfMonth/$strMonth/$year"
                binding.et1.setText(date)
            }, year, month, day)
        dataPickerDiologue!!.setTitle("Select Date")
        dataPickerDiologue!!.show()


    }

    private fun openTimePicker() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            binding.et2.text = SimpleDateFormat("HH:mm").format(cal.time)
        }
        TimePickerDialog(
            this,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            false
        ).show()
    }
}