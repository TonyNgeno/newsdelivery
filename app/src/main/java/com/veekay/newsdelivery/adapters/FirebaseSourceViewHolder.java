package com.veekay.newsdelivery.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.veekay.newsdelivery.R;
import com.veekay.newsdelivery.model.Source;
import com.veekay.newsdelivery.ui.ReadArticlesActivity;

import org.parceler.Parcels;

import butterknife.BindView;

/**
 * Created by kingkong on 9/25/17.
 */

public class FirebaseSourceViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    @BindView(R.id.sourceNameTextView) TextView sourceNameTextView;
    @BindView(R.id.dropdownListImageView) ImageView dropdownListImageView;
    @BindView(R.id.sourceDescription) TextView sourceDescription;
    @BindView(R.id.dropDownLayout) ConstraintLayout dropDownLayout;
    @BindView(R.id.clickToOpenSource) ImageView clickToOpenSource;
    @BindView(R.id.listItemListView) ConstraintLayout listItemListView;
    @BindView(R.id.starIcon) ImageView starIcon;

    View mView;
    Context mContext;
    public FirebaseSourceViewHolder(View view){
        super(view);
        mContext = view.getContext();

    }
    public void bindSource(final Source source){
        sourceNameTextView.setText(source.getName());
        sourceDescription.setText(source.getDescription());
        dropDownLayout.setVisibility(View.GONE);
        dropdownListImageView.setImageResource(R.drawable.ic_play_arrow_black_24dp);

        clickToOpenSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ReadArticlesActivity.class);
                intent.putExtra("source", Parcels.wrap(source));
                mContext.startActivity(intent);
            }
        });
    }
}
