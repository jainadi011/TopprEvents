package com.zriton.topprevents.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by aditya on 24/09/16.
 */

public class Website implements Parcelable{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("experience")
    @Expose
    private String experience;

    /**
     * No args constructor for use in serialization
     *
     */
    public Website() {
    }

    /**
     *
     * @param id
     * @param category
     * @param description
     * @param name
     * @param image
     * @param experience
     */
    public Website(String id, String name, String image, String category, String description, String experience) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.category = category;
        this.description = description;
        this.experience = experience;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The experience
     */
    public String getExperience() {
        return experience;
    }

    /**
     *
     * @param experience
     * The experience
     */
    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel pParcel, int pI) {
        pParcel.writeString(id);
        pParcel.writeString(name);
        pParcel.writeString(image);
        pParcel.writeString(category);
        pParcel.writeString(description);
        pParcel.writeString(experience);
    }

    private Website(Parcel pParcel)
    {
        this.id = pParcel.readString();
        this.name = pParcel.readString();
        this.image = pParcel.readString();
        this.category = pParcel.readString();
        this.description = pParcel.readString();
        this.experience = pParcel.readString();
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Website createFromParcel(Parcel in) {
                    return new Website(in);
                }

                public Website[] newArray(int size) {
                    return new Website[size];
                }
            };
}
