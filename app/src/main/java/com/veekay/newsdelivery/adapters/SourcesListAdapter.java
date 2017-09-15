package com.veekay.newsdelivery.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.veekay.newsdelivery.R;
import com.veekay.newsdelivery.model.Source;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kingkong on 9/15/17.
 */

public class SourcesListAdapter extends RecyclerView.Adapter<SourcesListAdapter.SourcesViewHolder>{
    private Context mContext;
    private ArrayList<Source> mSources;

    public SourcesListAdapter(Context context, ArrayList<Source> sources){
        this.mContext = context;
        this.mSources = sources;
    }

    @Override
    public SourcesListAdapter.SourcesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_source_item, parent, false);
        SourcesViewHolder viewHolder = new SourcesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SourcesListAdapter.SourcesViewHolder holder, int position) {
        holder.bindSource(mSources.get(position));

    }

    @Override
    public int getItemCount() {
        return mSources.size();
    }

    public class SourcesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Context mContext;
        @Bind(R.id.sourceNameTextView) TextView sourceNameTextView;
        @Bind(R.id.dropdownListImageView) ImageView dropdownListImageView;
        @Bind(R.id.sourceDescription) TextView sourceDescription;
        @Bind(R.id.dropDownLayout) ConstraintLayout dropDownLayout;
        @Bind(R.id.clickToOpenSource) ImageView clickToOpenSource;

        public SourcesViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
            clickToOpenSource.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                }
            });
        }
        public void bindSource(Source source){

            sourceNameTextView.setText(source.getName());
            sourceDescription.setText(source.getDescription());
            dropDownLayout.setVisibility(View.GONE);
        }
        @Override
        public void onClick(View v){
            if(dropDownLayout.getVisibility()==View.VISIBLE){
                dropDownLayout.setVisibility(View.GONE);
                dropdownListImageView.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            }else{
                dropDownLayout.setVisibility(View.VISIBLE);
                dropdownListImageView.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);

            }
        }
    }
}
