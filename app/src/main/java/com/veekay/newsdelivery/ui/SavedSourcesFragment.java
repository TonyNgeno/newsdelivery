package com.veekay.newsdelivery.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.veekay.newsdelivery.Constants;
import com.veekay.newsdelivery.R;
import com.veekay.newsdelivery.adapters.SourcesListAdapter;
import com.veekay.newsdelivery.model.Source;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedSourcesFragment extends Fragment {
    private DatabaseReference mSourcesReference;
    public ArrayList<Source> sources = new ArrayList<>();
    private SourcesListAdapter sourcesListAdapter;
    @BindView(R.id.savedNewsSourceRecyclerView) RecyclerView savedNewsSourceRecyclerView;
    @BindView(R.id.noSourcesText) TextView noSourcesText;

    private FirebaseRecyclerAdapter mFirebaseAdapter;


    public SavedSourcesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_saved_sources, container, false);
        ButterKnife.bind(this, view);
        getSources();
        return view;
    }
    public void getSources(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        noSourcesText.setVisibility(TextView.GONE);
        if(user!=null){
            String uid = user.getUid();

            mSourcesReference = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.SOURCES_DB_KEY)
                    .child(uid);
            mSourcesReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot childSnapShop:dataSnapshot.getChildren()){
                        String category = (String) childSnapShop.child("category").getValue();
                        String country = (String) childSnapShop.child("country").getValue();
                        String description = (String) childSnapShop.child("description").getValue();
                        String id = (String) childSnapShop.child("id").getValue();
                        String language = (String) childSnapShop.child("language").getValue();
                        String name = (String) childSnapShop.child("name").getValue();
                        String pushId = (String) childSnapShop.child("pushId").getValue();
                        ArrayList<String> sortsByAvailable = (ArrayList) childSnapShop.child("sortsByAvailable").getValue();
                        String url =(String) childSnapShop.child("url").getValue();;
                        Source source = new Source(id,name,description,url,category,language,country,sortsByAvailable);
                        source.setPushId(pushId);
                        sources.add(source);
                    }
                    sourcesListAdapter = new SourcesListAdapter(getContext(), sources);
                    savedNewsSourceRecyclerView.setAdapter(sourcesListAdapter);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    savedNewsSourceRecyclerView.setLayoutManager(layoutManager);
                    savedNewsSourceRecyclerView.setHasFixedSize(false);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{
            noSourcesText.setVisibility(TextView.VISIBLE);
        }

    }
}
