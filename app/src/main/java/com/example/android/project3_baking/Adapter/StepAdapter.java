package com.example.android.project3_baking.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.project3_baking.Model.Step;
import com.example.android.project3_baking.R;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepAdapterViewHolder>{

    private Step[] steps;
    private Context context;
    private StepAdapterOnClickHandler stepAdapterOnClickHandler;

    public StepAdapter(Context context, StepAdapterOnClickHandler stepAdapterOnClickHandler){
        this.context = context;
        this.stepAdapterOnClickHandler = stepAdapterOnClickHandler;
    }

    public interface StepAdapterOnClickHandler{
        void onClick(Step step);
    }

    @Override
    public StepAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_step_list_item, parent, false);

        return new StepAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepAdapterViewHolder holder, int position) {
        holder.txt_card_shortDescription.setText(steps[position].getShortDescription());
        holder.txt_card_thumbnail.setText(steps[position].getThumbnailURL());
    }

    @Override
    public int getItemCount() {
        if (steps == null)
            return 0;

        return steps.length;
    }

    public class StepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txt_card_shortDescription;
        private TextView txt_card_thumbnail;
        private TextView txt_card_description;

        public StepAdapterViewHolder(View itemView) {
            super(itemView);
            txt_card_shortDescription = itemView.findViewById(R.id.txt_card_shortDescription_step);
            txt_card_thumbnail = itemView.findViewById(R.id.txt_card_thumbnail_step);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = getAdapterPosition();
            Step step = steps[id];
            stepAdapterOnClickHandler.onClick(step);
        }
    }

    public void setSteps(Step[] steps){
        this.steps = steps;
        notifyDataSetChanged();
    }


}
