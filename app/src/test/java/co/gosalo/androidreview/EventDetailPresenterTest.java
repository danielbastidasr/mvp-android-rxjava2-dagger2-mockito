package co.gosalo.androidreview;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import co.gosalo.androidreview.activities.eventdetail.EventDetailActivity;
import co.gosalo.androidreview.activities.eventdetail.mvp.EventDetailModel;
import co.gosalo.androidreview.activities.eventdetail.mvp.EventDetailPresenter;
import co.gosalo.androidreview.activities.eventdetail.mvp.EventDetailView;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.utilities.RxSchedulersOverrideRule;
import io.reactivex.Observable;


public class EventDetailPresenterTest {

    private EventDetailPresenter presenter;
    private EventDetailView view;
    private EventDetailModel model;

    private Event mockEvent = new Event("Event","Event Venue");

    @Rule
    public RxSchedulersOverrideRule rule = new RxSchedulersOverrideRule();


    @Before
    public void setUp()throws Exception {

        view = Mockito.mock(EventDetailView.class);
        model = Mockito.mock(EventDetailModel.class);

        //eventDetailActivity = Mockito.mock(EventDetailActivity.class);

        //presenter = new EventDetailPresenter(view,model,eventDetailActivity);

    }

    /*
    @Test
    public void onEventPassed(){


        TODO: Meaningful test for EventDetailPresenter

        Mockito.when(model.getEvent()).thenReturn(Observable.just(mockEvents));

        mainPresenter.onCreate();

        Mockito.verify(mainView,Mockito.times(1)).emptyList(activity.getString(R.string.error_list_events)+caseNull);
        Mockito.verify(mainView,Mockito.times(1)).showLoading(false);
        Mockito.verify(mainView,Mockito.times(1)).showLoading(true);

        mainPresenter.onDestroy();

    }
    */

}
