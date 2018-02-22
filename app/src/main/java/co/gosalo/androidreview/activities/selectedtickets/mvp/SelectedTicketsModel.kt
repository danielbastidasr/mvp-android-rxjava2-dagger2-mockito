package co.gosalo.androidreview.activities.selectedtickets.mvp

import io.reactivex.Observable


class SelectedTicketsModel(private val tickets:String){

    fun getTickets(): Observable<String> {
        return Observable.just(tickets)
    }

}