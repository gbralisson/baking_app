package com.example.android.project3_baking;

import android.content.Context;
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

public class MasterRecipeFragment extends Fragment{

    private RecipeAdapter.RecipeAdapterOnClickHandler recipeAdapterOnClickHandler;

    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public MasterRecipeFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = view.findViewById(R.id.rv_recipes);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recipeAdapter = new RecipeAdapter(getContext(), recipeAdapterOnClickHandler);

        recyclerView.setAdapter(recipeAdapter);

        return view;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        this.layoutManager = layoutManager;
    }

    public void setRecipeAdapterOnClickHandler(RecipeAdapter.RecipeAdapterOnClickHandler recipeAdapterOnClickHandler){
        this.recipeAdapterOnClickHandler = recipeAdapterOnClickHandler;
    }

}
