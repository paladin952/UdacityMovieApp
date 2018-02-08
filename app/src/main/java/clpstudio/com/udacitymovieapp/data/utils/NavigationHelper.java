package clpstudio.com.udacitymovieapp.data.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public abstract class NavigationHelper {
    private static final String YOUTUBE_URL = "http://www.youtube.com/watch?v=";
    private static final String YOUTUBE_DOMAIN = "vnd.youtube:";


    public static void openYoutubeApp(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_DOMAIN + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(YOUTUBE_URL + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

}
