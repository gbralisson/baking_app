package com.example.android.project3_baking;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.project3_baking.Adapter.StepAdapter;
import com.example.android.project3_baking.Model.Step;

public class MasterRecipeStepFragment extends Fragment implements StepAdapter.StepAdapterOnClickHandler{

    private RecyclerView recyclerView;
    private StepAdapter stepAdapter;

    public MasterRecipeStepFragment(){

    }

    @Override
    public void onClick(Step step) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_recipe_step, container);

        recyclerView = view.findViewById(R.id.rv_recipe_steps);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        stepAdapter = new StepAdapter(getContext(), this);

        recyclerView.setAdapter(stepAdapter);

        return view;
    }


}
