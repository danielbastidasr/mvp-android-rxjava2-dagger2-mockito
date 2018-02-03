package co.gosalo.androidreview.app.api.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable{

    private String title;
    private String venue;

    protected Event(Parcel in) {
        title = in.readString();
        venue = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getVenue() {
        return venue;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(venue);
    }
}
