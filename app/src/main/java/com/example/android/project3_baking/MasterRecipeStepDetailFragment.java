package com.example.android.project3_baking;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.project3_baking.Model.Step;

public class MasterRecipeStepDetailFragment extends Fragment{

    private TextView txtDescription;
    private Step step;

    public MasterRecipeStepDetailFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);

        txtDescription = view.findViewById(R.id.txt_description);
        txtDescription.setText(step.getDescription());

        return view;
    }

    public void setStep(Step step){
        this.step = step;
    }
}
