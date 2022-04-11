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

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {
    private List<MeaningModel> meaningModelList;
    private Context context;

    public DetailAdapter(List<MeaningModel> meaningModelList, Context context) {
        this.meaningModelList = meaningModelList;
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
        //holder.itemWordDefinition.setText(meaningModelList.get(position).);
    }

    @Override
    public int getItemCount() {
        return 0;
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
