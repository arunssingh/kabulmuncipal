
package Model.Model_Complaint;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import Model.District;

public class ModelComplaint implements Parcelable
{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("id")
    @Expose
    private String ids;
    @SerializedName("name")
    @Expose
    private String fname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("district")
    @Expose
    private Model.District district;
    @SerializedName("directorate")
    @Expose
    private Directorate directorate;
    @SerializedName("issues")
    @Expose
    private Issues issues;
    @SerializedName("attachment")
    @Expose
    private String attachment;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;
    public final static Creator<ModelComplaint> CREATOR = new Creator<ModelComplaint>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ModelComplaint createFromParcel(Parcel in) {
            return new ModelComplaint(in);
        }

        public ModelComplaint[] newArray(int size) {
            return (new ModelComplaint[size]);
        }

    }
    ;

    protected ModelComplaint(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.ids = ((String) in.readValue((String.class.getClassLoader())));
        this.fname = ((String) in.readValue((String.class.getClassLoader())));
        this.lname = ((String) in.readValue((String.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.designation = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.district = ((Model.District) in.readValue((Model.District.class.getClassLoader())));
        this.directorate = ((Directorate) in.readValue((Directorate.class.getClassLoader())));
        this.issues = ((Issues) in.readValue((Issues.class.getClassLoader())));
        this.attachment = ((String) in.readValue((String.class.getClassLoader())));
        this.remarks = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.v = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public ModelComplaint() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Model.District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Directorate getDirectorate() {
        return directorate;
    }

    public void setDirectorate(Directorate directorate) {
        this.directorate = directorate;
    }

    public Issues getIssues() {
        return issues;
    }

    public void setIssues(Issues issues) {
        this.issues = issues;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(ids);
        dest.writeValue(fname);
        dest.writeValue(lname);
        dest.writeValue(phone);
        dest.writeValue(email);
        dest.writeValue(designation);
        dest.writeValue(gender);
        dest.writeValue(district);
        dest.writeValue(directorate);
        dest.writeValue(issues);
        dest.writeValue(attachment);
        dest.writeValue(remarks);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(v);
    }

    public int describeContents() {
        return  0;
    }

}
