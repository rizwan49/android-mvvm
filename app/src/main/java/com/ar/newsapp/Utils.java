package com.ar.newsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public final class Utils {
    private static final String TAG = Utils.class.getName();
    private static final SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private static final SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");

    private Utils() {

    }

    /***
     * using this method to set the view visible
     * @param views list of views...
     */
    public static void hideViews(View... views) {
        for (View view : views) {
            if (view == null) continue;
            view.setVisibility(View.GONE);
        }
    }

    /***
     * using this method to set the view visible
     * @param views list of views...
     */
    public static void showViews(View... views) {
        for (View view : views) {
            if (view == null) continue;
            view.setVisibility(View.VISIBLE);
        }
    }


    /***
     * this method used to load image into specified imageView;
     * @param context pass context for Picasso to initiate;
     * @param imageView after loading the image will be appear on this widget;
     * @param imageUrl image url
     * @param placeHolder
     * @param errorImageDrawable in case error comes during fetching image from given url then this errorImageDrawable will be appear into imageView;
     */
    public static void loadImage(Context context, ImageView imageView, String imageUrl, int placeHolder, int errorImageDrawable) {
        Uri url = null;
        if (!TextUtils.isEmpty(imageUrl))
            url = Uri.parse(imageUrl);

        Picasso.with(context)
                .load(url)
                .placeholder(placeHolder)
                .error(errorImageDrawable)
                .into(imageView);

    }

    /***
     * @param context passing to get the network status
     * @return boolean value as true or false
     * true: if network is available
     * false: if not available
     */
    public static boolean isNetworkAvailable(final Context context) {
        if (context == null) return false;
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (Objects.requireNonNull(connectivityManager).getActiveNetworkInfo() != null) {
            try {
                return connectivityManager.getActiveNetworkInfo().isConnected();
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public static String getDateFormat(String unformatted) {
        Date date = null;
        String formattedDate = null;
        try {
            date = input.parse(unformatted);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            formattedDate = output.format(date);
            Log.i(TAG, "" + formattedDate);
            return output.format(date);
        }
        return "";
    }

}
