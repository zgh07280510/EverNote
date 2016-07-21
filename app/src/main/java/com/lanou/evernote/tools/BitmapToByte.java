package com.lanou.evernote.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.litesuits.orm.db.annotation.Table;

import java.io.ByteArrayOutputStream;

/**
 * Created by dllo on 16/7/21.
 */
@Table("bitmap.table")
public class BitmapToByte {

    private byte[] photoImage;


    public Bitmap getImage() {
        if (photoImage == null) {
            return null;
        }
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(photoImage, 0, photoImage.length);
        return imageBitmap;
    }
    public  void setImage(Bitmap image) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, output);
        //转换成功了
        photoImage = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
