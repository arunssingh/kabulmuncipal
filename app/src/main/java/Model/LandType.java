
package Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LandType implements Parcelable
{

    @SerializedName("_id")
    @Expose
    private String id;

    protected LandType(Parcel in) {
        id = in.readString();
        name = in.readString();
        if (in.readByte() == 0) {
            rateCoefficient = null;
        } else {
            rateCoefficient = in.readDouble();
        }
        if (in.readByte() == 0) {
            coefficient = null;
        } else {
            coefficient = in.readDouble();
        }
    }

    public static final Creator<LandType> CREATOR = new Creator<LandType>() {
        @Override
        public LandType createFromParcel(Parcel in) {
            return new LandType(in);
        }

        @Override
        public LandType[] newArray(int size) {
            return new LandType[size];
        }
    };

    @Override
    public String toString() {
        return name;
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rate_coefficient")
    @Expose
    private Double rateCoefficient;

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    @SerializedName("coefficient")
    @Expose
    private Double coefficient;




    public LandType() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRateCoefficient() {
        return rateCoefficient;
    }

    public void setRateCoefficient(Double rateCoefficient) {
        this.rateCoefficient = rateCoefficient;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        if (rateCoefficient == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(rateCoefficient);
        }
        if (coefficient == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(coefficient);
        }
    }
}
