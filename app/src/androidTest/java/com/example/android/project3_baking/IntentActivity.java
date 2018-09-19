package com.example.android.project3_baking;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

public class IntentActivity {

    @Rule
    public ActivityTestRule<RecipeStepActivity> recipeStepActivityActivityTestRule = new ActivityTestRule<RecipeStepActivity>(RecipeStepActivity.class){
        @Override
        protected Intent getActivityIntent(){
            Intent intent = new Intent(InstrumentationRegistry.getContext(), RecipeStepActivity.class);
            intent.putExtra("tablet_key", false);
            intent.putExtra("recipe_key", "");
            return intent;
        }
    };

    @Test
    public void statusDevice(){
    }

}
