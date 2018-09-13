package com.example.android.project3_baking;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.android.project3_baking.Adapter.RecipeAdapter;
import com.example.android.project3_baking.Model.Recipe;
import com.example.android.project3_baking.Utils.ParseJson;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MasterRecipeFragment masterRecipeFragment = new MasterRecipeFragment();
        masterRecipeFragment.setRecipeAdapterOnClickHandler(this);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.container_main_tablet) != null){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
            masterRecipeFragment.setLayoutManager(gridLayoutManager);

            fragmentManager.beginTransaction().add(R.id.container_main_tablet, masterRecipeFragment).commit();

        }else{
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            masterRecipeFragment.setLayoutManager(linearLayoutManager);

            fragmentManager.beginTransaction().add(R.id.container_main, masterRecipeFragment).commit();
        }

    }

    @Override
    public void onClick(Recipe recipe) {
        String KEY_INTENT = "recipe_key";
        Intent intent = new Intent(this, RecipeStepActivity.class);
        intent.putExtra(KEY_INTENT, recipe);
        startActivity(intent);
    }
}
