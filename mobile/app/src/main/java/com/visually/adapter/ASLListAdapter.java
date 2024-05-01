package com.visually.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.visually.R;
import com.visually.search.SearchActivity;
import com.visually.search.ViewASLMaterialActivity;
import com.visually.types.SearchASLResponse;
import com.visually.utils.Utils;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ASLListAdapter extends RecyclerView.Adapter<ASLListAdapter.ViewHolder> {

    private List<SearchASLResponse> aslData;
    private Context context;

    public ASLListAdapter(List<SearchASLResponse> aslData, SearchActivity activity) {
        this.aslData = aslData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.asl_list, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SearchASLResponse aslElement = aslData.get(position);
        final String title = aslElement.title() != null && !aslElement.title().isEmpty() ? aslElement.title() : "Untitled";

        holder.aslTitle.setText(title);

        String description = Utils.shortenText(aslElement.description(), 25);
        String url = Utils.shortenText(aslElement.url(), 25);
        String videoUrl = aslElement.videoUrl();

        holder.aslDescription.setText(description);
        holder.aslUrl.setText(url);

        holder.itemView.setOnClickListener(v -> {
//                Toast.makeText(context, marketElement.getMarketName() + " : " + marketElement.getMarketId(), Toast.LENGTH_SHORT).show();

            Intent aslMaterialActivityIntent = new Intent(context, ViewASLMaterialActivity.class);
            aslMaterialActivityIntent.putExtra("title", title);
            aslMaterialActivityIntent.putExtra("description", aslElement.description());
            aslMaterialActivityIntent.putExtra("url", aslElement.url());
            aslMaterialActivityIntent.putExtra("videoUrl", videoUrl);

            context.startActivity( aslMaterialActivityIntent );
        });
    }


    @Override
    public int getItemCount() {
        return aslData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView aslTitle;

        TextView aslDescription;

        TextView aslUrl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            aslTitle = itemView.findViewById(R.id.aslTitleCardView);
            aslDescription = itemView.findViewById(R.id.descriptionCardView);
            aslUrl = itemView.findViewById(R.id.urlCardView);
        }
    }
}
