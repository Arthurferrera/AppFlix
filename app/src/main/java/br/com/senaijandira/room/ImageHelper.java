package br.com.senaijandira.room;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by sn1041520 on 02/04/2018.
 */

public class ImageHelper {


    public static byte[] toByteArray(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        return stream.toByteArray();
    }

    public static Bitmap toBitmap(byte[] bArray){
        return BitmapFactory.decodeByteArray(bArray, 0, bArray.length);
    }

}
