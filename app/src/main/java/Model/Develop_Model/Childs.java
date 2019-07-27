
package Model.Develop_Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Childs implements Parcelable
{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("plan_name")
    @Expose
    private String planName;
    @SerializedName("description")
    @Expose
    private String description;

    protected Childs(Parcel in) {
        id = in.readString();
        planName = in.readString();
        description = in.readString();
        file = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(planName);
        dest.writeString(description);
        dest.writeString(file);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Childs> CREATOR = new Creator<Childs>() {
        @Override
        public Childs createFromParcel(Parcel in) {
            return new Childs(in);
        }

        @Override
        public Childs[] newArray(int size) {
            return new Childs[size];
        }
    };

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @SerializedName("file")
    @Expose
    private String file;




    public Childs() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
