package co.gosalo.androidreview.activities.selectedtickets.mvp

import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy


class SelectedTicketsPresenter(private var view: SelectedTicketsView, model:SelectedTicketsModel) {
    private var disposable:Disposable

    init {
        disposable = model.getTickets().subscribeBy {
            tickets-> view.setTicketsSelected(tickets)
        }
    }

    fun onDestoy(){
        disposable.dispose()
    }
}