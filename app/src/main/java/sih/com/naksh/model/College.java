package sih.com.naksh.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dell on 23-03-2017.
 */

public class College implements Parcelable {

    private String clg_name;
    private String image;
    private int id;

    public College() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClg_name() {
        return this.clg_name;
    }

    public String getImage() {
        return this.image;
    }

    public void setClg_name(String clg_name) {
        this.clg_name = clg_name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.clg_name);
        dest.writeString(this.image);
        dest.writeInt(this.id);
    }

    protected College(Parcel in) {
        this.clg_name = in.readString();
        this.image = in.readString();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<College> CREATOR = new Parcelable.Creator<College>() {
        @Override
        public College createFromParcel(Parcel source) {
            return new College(source);
        }

        @Override
        public College[] newArray(int size) {
            return new College[size];
        }
    };
}
