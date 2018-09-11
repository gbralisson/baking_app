package com.example.android.project3_baking.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.project3_baking.Model.Recipe;
import com.example.android.project3_baking.R;
import com.example.android.project3_baking.Utils.Attributes;
import com.example.android.project3_baking.Utils.ParseJson;

import org.json.JSONException;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder>{

    private Recipe[] recipes;
    private Context context;
    private RecipeAdapterOnClickHandler recipeAdapterOnClickHandler;

    public RecipeAdapter(Context context, RecipeAdapterOnClickHandler recipeAdapterOnClickHandler){
        this.context = context;
        this.recipeAdapterOnClickHandler = recipeAdapterOnClickHandler;

        try {
            recipes = ParseJson.getRecipeJson(Attributes.testeJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface RecipeAdapterOnClickHandler{
        void onClick(Recipe recipe);
    }

    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_list_item, parent, false);

        return new RecipeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder holder, int position) {
        holder.txtCardName.setText(recipes[position].getName());
        holder.txtCardServing.setText(String.valueOf(recipes[position].getServings()));
    }

    @Override
    public int getItemCount() {
        if (recipes == null)
            return 0;

        return recipes.length;
    }



    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtCardName;
        private TextView txtCardServing;

        public RecipeAdapterViewHolder(View itemView) {
            super(itemView);
            txtCardName = itemView.findViewById(R.id.txt_card_name);
            txtCardServing = itemView.findViewById(R.id.txt_card_serving);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = getAdapterPosition();
            Recipe recipe = recipes[id];
            recipeAdapterOnClickHandler.onClick(recipe);
        }
    }

    public void setRecipes(Recipe[] recipes){
        this.recipes = recipes;
        notifyDataSetChanged();
    }

}
