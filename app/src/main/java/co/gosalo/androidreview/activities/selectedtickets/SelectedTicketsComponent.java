package co.gosalo.androidreview.activities.selectedtickets;

import co.gosalo.androidreview.app.AppComponent;
import dagger.Component;


@SelectedTicketsScope
@Component(modules = { SelectedTicketsModule.class }, dependencies = AppComponent.class)
public interface SelectedTicketsComponent {

    void inject(SelectedTicketsActivity selectedTicketsActivity);

}