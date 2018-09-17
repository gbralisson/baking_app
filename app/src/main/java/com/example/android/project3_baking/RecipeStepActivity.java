package com.example.android.project3_baking;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.project3_baking.Adapter.StepAdapter;
import com.example.android.project3_baking.Model.Ingredient;
import com.example.android.project3_baking.Model.Recipe;
import com.example.android.project3_baking.Model.Step;
import com.example.android.project3_baking.Widget.WidgetContract;

public class RecipeStepActivity extends AppCompatActivity implements StepAdapter.StepAdapterOnClickHandler{

    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);

        ActionBar actionBar = this.getSupportActionBar();

        if (getIntent() != null){
            if (getIntent().hasExtra("recipe_key")){
                recipe = (Recipe) getIntent().getSerializableExtra("recipe_key");

                actionBar.setTitle(recipe.getName());

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
        masterRecipeStepDetailFragment.setStatusClickIngredient(false);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.layout_recipe_step_detail_tablet) != null){
            fragmentManager.beginTransaction().replace(R.id.container_recipe_step_detail_tablet, masterRecipeStepDetailFragment).commit();
        }else {
            fragmentManager.addOnBackStackChangedListener(null);
            fragmentManager.beginTransaction().replace(R.id.container_recipe_step, masterRecipeStepDetailFragment).commit();
        }
    }

    public void clickIngredients(View view){

        MasterRecipeStepDetailFragment masterRecipeStepDetailFragment = new MasterRecipeStepDetailFragment();
        masterRecipeStepDetailFragment.setIngredient(recipe.getIngredients());
        masterRecipeStepDetailFragment.setStatusClickIngredient(true);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.layout_recipe_step_detail_tablet) != null){
            fragmentManager.beginTransaction().replace(R.id.container_recipe_step_detail_tablet, masterRecipeStepDetailFragment).commit();
        }else {
            fragmentManager.addOnBackStackChangedListener(null);
            fragmentManager.beginTransaction().replace(R.id.container_recipe_step, masterRecipeStepDetailFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ingredient_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_info) {
            setIngredientsDatabase(recipe.getIngredients());
        }

        return super.onOptionsItemSelected(item);
    }

    public void setIngredientsDatabase(Ingredient[] ingredients){

        for (Ingredient ingredient : ingredients) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(WidgetContract.IngredientEntry.INGREDIENT_NAME, ingredient.getIngredient());
            contentValues.put(WidgetContract.IngredientEntry.INGREDIENT_MEASURE, ingredient.getMeasure());
            contentValues.put(WidgetContract.IngredientEntry.INGREDIENT_QUANTITY, ingredient.getQuantity());

            if (getIngredientsList() != null)
                getContentResolver().update(WidgetContract.IngredientEntry.CONTENT_URI, contentValues, null, null);
            else
                getContentResolver().insert(WidgetContract.IngredientEntry.CONTENT_URI, contentValues);

        }
    }

    public Cursor getIngredientsList(){
        Uri INGREDIENT = WidgetContract.IngredientEntry.CONTENT_URI;
        Cursor cursor = getContentResolver().query(INGREDIENT, null, null, null, null);
        return cursor;
    }
}
