package co.gosalo.androidreview.activities.selectedtickets.mvp


import android.annotation.SuppressLint
import android.app.Activity
import android.widget.FrameLayout
import android.widget.TextView

import co.gosalo.androidreview.R

@SuppressLint("ViewConstructor")
class SelectedTicketsView(private val activity: Activity) : FrameLayout(activity) {


    private var ticketsSelected: TextView


    init {

        inflate(this.activity, R.layout.activity_selected_tickets, this)
        ticketsSelected = findViewById(R.id.tickets_selected_tv)

    }

    fun setTicketsSelected(ticketsSelected: String) {
        this.ticketsSelected!!.text = ticketsSelected
    }

}
