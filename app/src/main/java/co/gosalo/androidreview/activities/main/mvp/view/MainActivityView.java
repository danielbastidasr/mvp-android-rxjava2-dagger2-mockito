package co.gosalo.androidreview.activities.main.mvp.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.gosalo.androidreview.R;
import co.gosalo.androidreview.activities.main.MainActivity;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.activities.main.mvp.view.adapter.EventAdapter;



@SuppressLint("ViewConstructor")
public class MainActivityView extends FrameLayout {

    private final ProgressDialog progressDialog = new ProgressDialog(getContext());
    private MainActivity activity;

    @BindView(R.id.reciclerView)
    RecyclerView recyclerView;

    @BindView(R.id.emptyListView)
    TextView emptyList;

    public MainActivityView(MainActivity activity) {
        super(activity);

        this.activity = activity;

        inflate(this.activity,R.layout.activity_main,this);
        ButterKnife.bind(this);

        progressDialog.setMessage("Looking for Events");

        recyclerView.setHasFixedSize(true);
        //          WE SET ORIENTATION OF THE RECYCLE VIEW AS VERTICAL
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //          WE ADD SOME SEPARATION AMONG THE EVENTS
        recyclerView.addItemDecoration(new EventAdapter.SpaceItems(30));


    }

    public void setUpRecyclerView(List< Event> events){
        //          CREATE AN ADAPTER WHEN WE THE LIST
        EventAdapter eventAdapter = new EventAdapter(events, activity);
        recyclerView.setAdapter(eventAdapter);

    }


    public void showLoading(boolean loading){
        if(loading){
            progressDialog.show();
        }
        else{
            progressDialog.dismiss();
        }
    }

    public void emptyList(String errorText){
        recyclerView.setVisibility(INVISIBLE);

        emptyList.setText(errorText);
        emptyList.setVisibility(VISIBLE);

    }

}
