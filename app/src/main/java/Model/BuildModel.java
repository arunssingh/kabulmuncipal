
package Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuildModel implements Parcelable
{

    @Override
    public String toString() {
        return name;
    }

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("zone")
    @Expose
    private String zone;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("land")
    @Expose
    private String land;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<BuildModel> CREATOR = new Creator<BuildModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public BuildModel createFromParcel(Parcel in) {
            return new BuildModel(in);
        }

        public BuildModel[] newArray(int size) {
            return (new BuildModel[size]);
        }

    }
    ;

    protected BuildModel(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.zone = ((String) in.readValue((String.class.getClassLoader())));
        this.district = ((String) in.readValue((String.class.getClassLoader())));
        this.land = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((Double) in.readValue((Double.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public BuildModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(zone);
        dest.writeValue(district);
        dest.writeValue(land);
        dest.writeValue(price);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
