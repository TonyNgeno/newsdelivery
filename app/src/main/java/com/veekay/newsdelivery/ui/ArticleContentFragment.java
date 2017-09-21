package com.veekay.newsdelivery.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.veekay.newsdelivery.R;
import com.veekay.newsdelivery.model.Article;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleContentFragment extends Fragment {
    private Article mArticle;
    @BindView(R.id.articleImageView) ImageView articleImageView;
    @BindView(R.id.articleTitle) TextView articleTitle;
    @BindView(R.id.publishedAtTextView) TextView publishedAtTextView;
    @BindView(R.id.articleDescription) TextView articleDescription;
    @BindView(R.id.openInBrowserIcon) ImageView openInBrowserIcon;

    public static ArticleContentFragment newInstance(Article article){
        ArticleContentFragment articleContentFragment = new ArticleContentFragment();
        Bundle args = new Bundle();
        args.putParcelable("article", Parcels.wrap(article));
        articleContentFragment.setArguments(args);
        return articleContentFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArticle = Parcels.unwrap(getArguments().getParcelable("article"));
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_content, container, false);
        ButterKnife.bind(this, view);
        Picasso.with(view.getContext()).load(mArticle.getUrlToImage()).into(articleImageView);
        articleTitle.setText(mArticle.getTitle());
        publishedAtTextView.setText(mArticle.getPublishedAT());
        articleDescription.setText(mArticle.getDescription());

        openInBrowserIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ArticleWebViewActivity.class);
                intent.putExtra("url", mArticle.getUrl());
                startActivity(intent);
            }
        });


        return view;
    }
}
