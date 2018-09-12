package com.example.android.project3_baking;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.project3_baking.Adapter.StepAdapter;
import com.example.android.project3_baking.Model.Recipe;

public class RecipeStepActivity extends AppCompatActivity {

    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);

        if (getIntent() != null){
            if (getIntent().hasExtra("recipe_key")){
                recipe = (Recipe) getIntent().getSerializableExtra("recipe_key");

                MasterRecipeStepFragment masterRecipeStepFragment = new MasterRecipeStepFragment();
                masterRecipeStepFragment.setSteps(recipe.getSteps());
                masterRecipeStepFragment.setIngredients(recipe.getIngredients());

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.container_recipe_step, masterRecipeStepFragment).commit();

            }
        }

    }
}
