
package Model.Develop_Model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDevelopment implements Parcelable
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

    protected ModelDevelopment(Parcel in) {
        id = in.readString();
        planName = in.readString();
        description = in.readString();
        file = in.readString();
        childrens = in.createTypedArrayList(Childs.CREATOR);
    }

    public static final Creator<ModelDevelopment> CREATOR = new Creator<ModelDevelopment>() {
        @Override
        public ModelDevelopment createFromParcel(Parcel in) {
            return new ModelDevelopment(in);
        }

        @Override
        public ModelDevelopment[] newArray(int size) {
            return new ModelDevelopment[size];
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
    @SerializedName("childrens")
    @Expose
    private List<Childs> childrens = null;



    public ModelDevelopment() {
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

    public List<Childs> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Childs> childrens) {
        this.childrens = childrens;
    }



    public int describeContents() {
        return  0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(planName);
        dest.writeString(description);
        dest.writeString(file);
        dest.writeTypedList(childrens);
    }

}
