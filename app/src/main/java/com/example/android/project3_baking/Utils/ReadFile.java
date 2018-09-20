package com.example.android.project3_baking.Utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class ReadFile {

    public static String readFromFile(String filename, Context context){

        String json = "";

        try {
            InputStream inputStream = context.getResources().getAssets().open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

}
