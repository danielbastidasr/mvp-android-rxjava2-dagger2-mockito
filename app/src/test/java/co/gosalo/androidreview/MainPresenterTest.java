package co.gosalo.androidreview;



import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import co.gosalo.androidreview.activities.main.mvp.MainModel;
import co.gosalo.androidreview.activities.main.mvp.MainPresenter;
import co.gosalo.androidreview.activities.main.mvp.view.MainActivityView;
import co.gosalo.androidreview.app.api.PagedResponseBody;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.utilities.RxSchedulersOverrideRule;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    private MainActivityView mainView;
    @Mock
    private MainModel mainModel;

    private MainPresenter mainPresenter;

    private List<Event> mockEvents = new ArrayList<>();

    @Rule
    public RxSchedulersOverrideRule rule = new RxSchedulersOverrideRule();


    @Before
    public void setUp()throws Exception {

        mainPresenter = new MainPresenter(mainView,mainModel);
    }

    @Test
    public void onGetEventsNullDataButSavedState(){
        /*TODO: Create a better test*/

        //Given SavedState different than null
        Mockito.when(mainModel.getEventsFromSaveStateOrApi()).thenReturn(Single.just(mockEvents));
        //Mockito.when(mainModel.getEvents(0)).thenReturn(Observable.just(mockEvents));
        //Mockito.when(mainModel.getEventsFromSaveState()).thenReturn(Maybe.empty());


        //When Presenter Called
        mainPresenter.onCreate();

        //Then the following methods should be called
        Mockito.verify(mainView,Mockito.times(1)).showLoading(false);
        Mockito.verify(mainView,Mockito.times(1)).setUpRecyclerView(mockEvents);


        //At the end we just destroy
        mainPresenter.onDestroy();
    }



}
