package com.example.android.quakereport;

/**
 * Created by James on 03-Jan-17.
 */

public class Earthquake {
    private double mag;
    private String loc;
    private Long date;
    private String url;

    /*
     * Default Earthquake constructor
     */
    Earthquake (double mag, String loc, Long date, String url) {
        this.mag = mag;
        this.loc = loc;
        this.date = date;
        this.url = url;
    }

    /*
     * Getter methods
     */
    public double getMag() { return mag; }

    public String getLoc() { return loc; }

    public Long getDate() { return date; }

    public String getURL() { return url; }

}
