package sih.com.naksh.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dell on 23-03-2017.
 */

public class Post implements Parcelable {

    private String clg_name;
    private String clg_cap;
    private String clg_text;
    private String time_stamp;
    private String thumbnail;
    private String profilepic;
    private String emailid;
    private String category;
    private String venue;
    private String timing;

    public Post() {
    }

    public String getVenue() {
        return this.venue;
    }

    public String getTiming() {
        return this.timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getClg_name() {
        return this.clg_name;
    }

    public String getClg_cap() {
        return this.clg_cap;
    }

    public String getClg_text() {
        return this.clg_text;
    }

    public String getTime_stamp() {
        return this.time_stamp;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public String getCategory() {
        return this.category;
    }

    public String getEmailid() {
        return this.emailid;
    }

    public void setClg_name(String clg_name) {
        this.clg_name = clg_name;
    }

    public void setClg_cap(String clg_cap) {
        this.clg_cap = clg_cap;
    }

    public void setClg_text(String clg_text) {
        this.clg_text = clg_text;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getProfilepic() {
        return this.profilepic;
    }

    public static Parcelable.Creator<Post> getCREATOR() {
        return Post.CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.clg_name);
        dest.writeString(this.clg_cap);
        dest.writeString(this.clg_text);
        dest.writeString(this.time_stamp);
        dest.writeString(this.thumbnail);
        dest.writeString(this.category);
        dest.writeString(this.emailid);
        dest.writeString(this.venue);
        dest.writeString(this.timing);
    }

    protected Post(Parcel in) {
        this.clg_name = in.readString();
        this.clg_cap = in.readString();
        this.clg_text = in.readString();
        this.time_stamp = in.readString();
        this.thumbnail = in.readString();
        this.category = in.readString();
        this.emailid = in.readString();
        this.venue = in.readString();
        this.timing = in.readString();
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
