package br.com.fearaujo.marvel.util;

import android.content.Context;
import android.view.Surface;
import android.view.WindowManager;

public class OrientationHelper {

    public static final int PORT = 1;
    public static final int LAND = 2;

    public static int getOrientation(Context context){

        WindowManager windowService = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        int rotation = windowService.getDefaultDisplay().getRotation();

        if (Surface.ROTATION_0 == rotation) {
            rotation = PORT;
        } else if(Surface.ROTATION_180 == rotation) {
            rotation = PORT;
        } else if(Surface.ROTATION_90 == rotation) {
            rotation = LAND;
        } else if(Surface.ROTATION_270 == rotation) {
            rotation = LAND;
        }
        return rotation;
    }

}
