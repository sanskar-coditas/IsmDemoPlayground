package com.coditas.ism.demoplayground
import android.os.Bundle
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.coditas.ism.demoplayground.databinding.ActivityMainBinding
import com.coditas.ism.demoplayground.databinding.CalendarViewBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CalendarView : AppCompatActivity() {
    // on below line we are creating
    // variables for text view and calendar view

    lateinit var calendarView: CalendarView
      private lateinit var binding: CalendarViewBinding
      private lateinit var eventStore:String
    private lateinit var date:String
    private lateinit var eventListAdapter: EventListAdapter
    lateinit var database: EventDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        calendarView = findViewById(R.id.calendarView)
        database= EventDatabase.getDatabase(this)

        binding.btnCreateEvent.setOnClickListener {

            eventStore = binding.etForEventStored.text.toString()
            binding.tvForDateSelected.text=date
            //binding.tvForDateSelected.text=date


            if(date.isNotEmpty() && eventStore.isNotEmpty()){
                GlobalScope.launch {
                    database.eventDao().insertEvent(Event(0,date,eventStore))

                }
                storeData()


            }else{
                Toast.makeText(this,"Please select the date and enter the event",Toast.LENGTH_SHORT).show()
            }

        }
        // on below line we are adding set on
        // date change listener for calendar view.
        dateSelection()
        storeData()


    }

    private fun dateSelection() {
        calendarView
            .setOnDateChangeListener(
                OnDateChangeListener { view, year, month, dayOfMonth ->
                    // In this Listener we are getting values
                    // such as year, month and day of month
                    // on below line we are creating a variable
                    // in which we are adding all the cariables in it.
                    date = (dayOfMonth.toString() + "-"
                            + (month + 1) + "-" + year)
                    // set this date in TextView for Display
                })

    }

    private fun storeData(){
         database.eventDao().getEvent().observe( this, Observer {
             binding.tvForEvent.text=it.toString()

             eventListAdapter = EventListAdapter(it as ArrayList<Event>)
             Toast.makeText(this,eventListAdapter.toString(),Toast.LENGTH_SHORT).show()
             binding.eventList.adapter = eventListAdapter

        })
    }
}
//import android.app.Activity
//import android.widget.CalendarView
//import android.widget.Toast
//
//object CalendarView {
//    fun genericCalenderView(activity: Activity?){
//        val calendar= activity?.let { CalendarView(it) }
//       // val calendarView = calendar?.findViewById<CalendarView>(R.id.calendarView)
//
//        calendar
//            ?.setOnDateChangeListener(
//                CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
//                    // In this Listener we are getting values
//                    // such as year, month and day of month
//                    // on below line we are creating a variable
//                    // in which we are adding all the cariables in it.
//                    val Date = (dayOfMonth.toString() + "-"
//                            + (month + 1) + "-" + year)
//
//                    // set this date in TextView for Display
//                    Toast.makeText(activity,Date,Toast.LENGTH_LONG).show()
//                })
//    }
//}