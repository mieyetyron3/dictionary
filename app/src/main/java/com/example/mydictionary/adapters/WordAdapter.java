package com.example.mydictionary.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydictionary.R;
import com.example.mydictionary.models.WordFullModel;

import java.io.Serializable;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {
    private List<List<WordFullModel>> wordFullModelRecyclerList;
    private Context context;
    private NavController navController;

    public WordAdapter(List<List<WordFullModel>> wordFullModelRecyclerList, Context context) {
        this.wordFullModelRecyclerList = wordFullModelRecyclerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_row_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String word = wordFullModelRecyclerList.get(position).get(0).getWord();
        holder.getItemWord().setText(word);
        //TODO: handle background based on the null value

        //Set OnclickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Navigate to DetailFragment
                Bundle bundle = new Bundle();
                List<WordFullModel> wordList = wordFullModelRecyclerList.get(holder.getAdapterPosition());
                bundle.putSerializable("wordDetails", (Serializable) wordList);
                navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_listFragment_to_detailFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {

        return wordFullModelRecyclerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemWord;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Define click listener for the ViewHolder's View

            //Initialize views
            itemWord = itemView.findViewById(R.id.item_word);
        }

        public TextView getItemWord() {
            return itemWord;
        }
    }
}
