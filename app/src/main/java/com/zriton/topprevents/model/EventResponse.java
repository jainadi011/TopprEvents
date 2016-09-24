package com.zriton.topprevents.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aditya on 24/09/16.
 */

public class EventResponse {


    @SerializedName("websites")
    @Expose
    private List<Website> websites = new ArrayList<Website>();
    @SerializedName("quote_max")
    @Expose
    private String quoteMax;
    @SerializedName("quote_available")
    @Expose
    private String quoteAvailable;

    /**
     * No args constructor for use in serialization
     *
     */
    public EventResponse() {
    }

    /**
     *
     * @param quoteAvailable
     * @param websites
     * @param quoteMax
     */
    public EventResponse(List<Website> websites, String quoteMax, String quoteAvailable) {
        this.websites = websites;
        this.quoteMax = quoteMax;
        this.quoteAvailable = quoteAvailable;
    }

    /**
     *
     * @return
     * The websites
     */
    public List<Website> getWebsites() {
        return websites;
    }

    /**
     *
     * @param websites
     * The websites
     */
    public void setWebsites(List<Website> websites) {
        this.websites = websites;
    }

    /**
     *
     * @return
     * The quoteMax
     */
    public String getQuoteMax() {
        return quoteMax;
    }

    /**
     *
     * @param quoteMax
     * The quote_max
     */
    public void setQuoteMax(String quoteMax) {
        this.quoteMax = quoteMax;
    }

    /**
     *
     * @return
     * The quoteAvailable
     */
    public String getQuoteAvailable() {
        return quoteAvailable;
    }

    /**
     *
     * @param quoteAvailable
     * The quote_available
     */
    public void setQuoteAvailable(String quoteAvailable) {
        this.quoteAvailable = quoteAvailable;
    }

}
