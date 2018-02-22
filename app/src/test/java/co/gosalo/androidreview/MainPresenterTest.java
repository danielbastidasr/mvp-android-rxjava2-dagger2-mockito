package co.gosalo.androidreview;



import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import co.gosalo.androidreview.activities.main.mvp.MainModel;
import co.gosalo.androidreview.activities.main.mvp.MainPresenter;
import co.gosalo.androidreview.activities.main.mvp.view.MainActivityView;
import co.gosalo.androidreview.app.api.PagedResponseBody;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.utilities.RxSchedulersOverrideRule;
import io.reactivex.Observable;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    private MainActivityView mainView;
    @Mock
    private MainModel mainModel;

    private MainPresenter mainPresenter;

    private PagedResponseBody<List<Event>> mockEvents = new PagedResponseBody<>();

    @Rule
    public RxSchedulersOverrideRule rule = new RxSchedulersOverrideRule();


    @Before
    public void setUp()throws Exception {

        mainPresenter = new MainPresenter(mainView,mainModel);
    }

    @Test
    public void onGetEventsNullData(){

        //Given that there is no network connection or PageResponseBody attributes may be null
        Mockito.when(mainModel.getListEvents(0)).thenReturn(Observable.just(mockEvents));

        //When Presenter Called
        mainPresenter.onCreate();

        //Then the following methods should be called
        Mockito.verify(mainView,Mockito.times(1)).showLoading(true);
        Mockito.verify(mainView,Mockito.times(1)).emptyList();
        Mockito.verify(mainView,Mockito.times(1)).showLoading(false);

        //At the end we just destroy
        mainPresenter.onDestroy();
    }



}
