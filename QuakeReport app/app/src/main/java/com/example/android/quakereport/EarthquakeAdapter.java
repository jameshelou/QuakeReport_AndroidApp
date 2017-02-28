package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import static com.example.android.quakereport.R.id.date;

/**
 * Created by James on 03-Jan-17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    EarthquakeAdapter (Activity context, ArrayList<Earthquake> list) {
        super(context, 0,  list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,
                    parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        // Find the TextViews
        TextView magnitude = (TextView) convertView.findViewById(R.id.magnitude);
        TextView location_primary = (TextView) convertView.findViewById(R.id.loc_primary);
        TextView location_offset = (TextView) convertView.findViewById(R.id.loc_offset);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView time = (TextView) convertView.findViewById(R.id.time);

        // Update magnitude info
        DecimalFormat formatMag = new DecimalFormat("0.0");
        magnitude.setText(formatMag.format(currentEarthquake.getMag()));

        // Update magnitude circle drawable
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);



        // Handle splitting of location primary and location offset with two separate TextViews
        String current_location = currentEarthquake.getLoc();
        if (current_location.contains("km")) {
            location_offset.setText(current_location.substring(0,
                    current_location.indexOf("of")+2));
            location_primary.setText(current_location.substring(
                    current_location.indexOf("of")+2, current_location.length()));
        } else {
            location_offset.setText(R.string.near_the);
            location_primary.setText(current_location);
        }



        // Create a Date object using the Unix timestamp from the current earthquake
        Date dateObject = new Date(currentEarthquake.getDate());

        // Format the date portion of the timestamp (MMM D, YYYY)
        String formattedDate = formatDate(dateObject);
        date.setText(formattedDate);

        // Format the time portion of the timestamp (h:mm a)
        String formattedTime = formatTime(dateObject);
        time.setText(formattedTime);

        return convertView;
    }

    /*
     * Returns the formatted date string (MMM D, YYYY) from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormatter.format(dateObject);
    }

    /*
     * Returns the formatted date string (HOUR:MINUTES AM/PM) from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        return timeFormatter.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
