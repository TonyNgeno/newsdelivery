package com.veekay.newsdelivery.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.veekay.newsdelivery.Constants;
import com.veekay.newsdelivery.R;
import com.veekay.newsdelivery.model.Source;
import com.veekay.newsdelivery.ui.ReadArticlesActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kingkong on 9/15/17.
 */

public class SourcesListAdapter extends RecyclerView.Adapter<SourcesListAdapter.SourcesViewHolder>{
    private Context mContext;
    private ArrayList<Source> mSources;
    private FirebaseAuth mAuth;

    public SourcesListAdapter(Context context, ArrayList<Source> sources){
        this.mContext = context;
        this.mSources = sources;
    }

    @Override
    public SourcesListAdapter.SourcesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_source_item, parent, false);
        SourcesViewHolder viewHolder = new SourcesViewHolder(view);
        mAuth = FirebaseAuth.getInstance();
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
        @BindView(R.id.sourceNameTextView) TextView sourceNameTextView;
        @BindView(R.id.dropdownListImageView) ImageView dropdownListImageView;
        @BindView(R.id.sourceDescription) TextView sourceDescription;
        @BindView(R.id.dropDownLayout) ConstraintLayout dropDownLayout;
        @BindView(R.id.clickToOpenSource) ImageView clickToOpenSource;
        @BindView(R.id.listItemListView) ConstraintLayout listItemListView;
        @BindView(R.id.starIcon) ImageView starIcon;

        public SourcesViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            listItemListView.setOnClickListener(this);

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

            starIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleFavorites(source);
                }
            });
        }
        @Override
        public void onClick(View v){
            if (v == listItemListView){
                if(dropDownLayout.getVisibility()==View.VISIBLE){
                    dropDownLayout.setVisibility(View.GONE);
                    dropdownListImageView.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                }else{
                    dropDownLayout.setVisibility(View.VISIBLE);
                    dropdownListImageView.setImageResource(R.drawable.ic_arrow_downward_white_24dp);
                }
            }

        }
    }
    public void toggleFavorites(final Source source){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            String uid = currentUser.getUid();

            final DatabaseReference databaseReference = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.SOURCES_DB_KEY)
                    .child(uid);

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean valueExists = false;
                    for(DataSnapshot childSnapShot:dataSnapshot.getChildren()){
                        if(childSnapShot.child("id").getValue().equals(source.getId())){
                            valueExists = true;
                            return;
                        }
                    }

                    if(!valueExists){
                        DatabaseReference pushRef = databaseReference.push();
                        String pushId = pushRef.getKey();

                        source.setPushId(pushId);

                        pushRef.setValue(source);


                        Toast toast = Toast.makeText(mContext, "Saved", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        Toast toast = Toast.makeText(mContext, "Already Saved", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else{
            CharSequence text = "You need to login to add to Favorites";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(mContext, text, duration);
            toast.show();
        }

    }
}
