package com.example.mydictionary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydictionary.R;
import com.example.mydictionary.models.MeaningModel;
import com.example.mydictionary.models.WordFullModel;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {
    private List<String> wordList;
    private Context context;

    public DetailAdapter(List<String> wordList, Context context) {
        this.wordList = wordList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_row_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemWordDefinition.setText(wordList.get(position));
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemWordDefinition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemWordDefinition = itemView.findViewById(R.id.item_word_definition);
        }

        public TextView getItemWordDefinition() {
            return itemWordDefinition;
        }
    }
}
