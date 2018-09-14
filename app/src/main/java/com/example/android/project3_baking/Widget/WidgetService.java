package com.example.android.project3_baking.Widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

public class WidgetService extends IntentService{

    public final static String SHOW_INGREDIENTS_LIST = "com.example.android.project3_baking.ingredients_list";

    public WidgetService(String name) {
        super(name);
    }

    public static void setShowIngredientsList(Context context){
        Intent intent = new Intent(context, WidgetService.class);
        intent.setAction(SHOW_INGREDIENTS_LIST);
        context.startActivity(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null){
            final String action = intent.getAction();
            if (SHOW_INGREDIENTS_LIST.equals(action)){

            }
        }
    }
}
