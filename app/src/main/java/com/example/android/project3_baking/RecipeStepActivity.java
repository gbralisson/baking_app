package com.example.android.project3_baking;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.project3_baking.Adapter.StepAdapter;
import com.example.android.project3_baking.Model.Recipe;
import com.example.android.project3_baking.Model.Step;

public class RecipeStepActivity extends AppCompatActivity implements StepAdapter.StepAdapterOnClickHandler{

    private Recipe recipe;
    private boolean mtwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);

        if (getIntent() != null){
            if (getIntent().hasExtra("recipe_key")){
                recipe = (Recipe) getIntent().getSerializableExtra("recipe_key");

                MasterRecipeStepFragment masterRecipeStepFragment = new MasterRecipeStepFragment();
                masterRecipeStepFragment.setStepAdapterOnClickHandler(this);
                masterRecipeStepFragment.setSteps(recipe.getSteps());
                masterRecipeStepFragment.setIngredients(recipe.getIngredients());

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.container_recipe_step, masterRecipeStepFragment).commit();

            }
        }

    }

    @Override
    public void onClick(Step step) {

        MasterRecipeStepDetailFragment masterRecipeStepDetailFragment = new MasterRecipeStepDetailFragment();
        masterRecipeStepDetailFragment.setStep(step);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.layout_recipe_step_detail_tablet) != null){
            fragmentManager.beginTransaction().replace(R.id.container_recipe_step_detail_tablet, masterRecipeStepDetailFragment).commit();
        }else {
            fragmentManager.beginTransaction().replace(R.id.container_recipe_step, masterRecipeStepDetailFragment).commit();
        }
    }
}
