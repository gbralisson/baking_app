package com.example.android.project3_baking;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ListView;
import android.widget.RemoteViews;

import com.example.android.project3_baking.Model.Ingredient;
import com.example.android.project3_baking.Widget.WidgetContract;
import com.example.android.project3_baking.Widget.WidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);

//        views.setImageViewResource(R.id.widget_baking, R.drawable.baking_icon);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        Intent widgetIntent = new Intent(context, WidgetService.class);
//        widgetIntent.setAction(WidgetService.SHOW_INGREDIENTS_LIST);
//        widgetIntent.setData(Uri.parse(widgetIntent.toUri(Intent.URI_INTENT_SCHEME)));

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        views.setRemoteAdapter(R.id.list_ingredients_widget, widgetIntent);
//        views.setOnClickPendingIntent(R.id.list_ingredients_widget, pendingIntent);
//        views.setEmptyView(R.id.list_ingredients_widget,);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

