package co.gosalo.androidreview.activities.selectedtickets;

import co.gosalo.androidreview.app.AppComponent;
import dagger.Component;
import dagger.Subcomponent;


@SelectedTicketsScope
@Subcomponent(modules = { SelectedTicketsModule.class })
public interface SelectedTicketsComponent {

    void inject(SelectedTicketsActivity selectedTicketsActivity);

}