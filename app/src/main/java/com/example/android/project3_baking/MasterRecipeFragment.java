package com.example.android.project3_baking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.project3_baking.Adapter.RecipeAdapter;
import com.example.android.project3_baking.Model.Recipe;

public class MasterRecipeFragment extends Fragment implements RecipeAdapter.RecipeAdapterOnClickHandler{

    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;


    public MasterRecipeFragment(){
    }

    @Override
    public void onClick(Recipe recipe) {
        String KEY_INTENT = "recipe_key";
        Intent intent = new Intent(getContext(), RecipeStepActivity.class);
        intent.putExtra(KEY_INTENT, recipe);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = view.findViewById(R.id.rv_recipes);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        recipeAdapter = new RecipeAdapter(getContext(), this);

        recyclerView.setAdapter(recipeAdapter);

        return view;
    }

}
