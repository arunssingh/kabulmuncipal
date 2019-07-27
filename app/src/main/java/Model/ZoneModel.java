
package Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ZoneModel implements Parcelable
{

    @Override
    public String toString() {
        return name;
    }

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("district_id")
    @Expose
    private String districtId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coefficient")
    @Expose
    private Double coefficient;
    public final static Creator<ZoneModel> CREATOR = new Creator<ZoneModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ZoneModel createFromParcel(Parcel in) {
            return new ZoneModel(in);
        }

        public ZoneModel[] newArray(int size) {
            return (new ZoneModel[size]);
        }

    }
    ;

    protected ZoneModel(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.districtId = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.coefficient = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public ZoneModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(districtId);
        dest.writeValue(name);
        dest.writeValue(coefficient);
    }

    public int describeContents() {
        return  0;
    }

}
