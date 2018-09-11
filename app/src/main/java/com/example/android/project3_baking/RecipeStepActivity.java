package com.example.android.project3_baking;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.project3_baking.Adapter.StepAdapter;
import com.example.android.project3_baking.Model.Recipe;

public class RecipeStepActivity extends AppCompatActivity {

    private Recipe recipe;
    private StepAdapter stepAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);

        stepAdapter = new StepAdapter();

        if (getIntent() != null){
            if (getIntent().hasExtra("recipe_key")){
                recipe = getIntent().getParcelableExtra("recipe_key");
                stepAdapter.setSteps(recipe.getSteps());
                //Log.d("teste", recipe.getName());
            }
        }

    }
}
