package com.example.newapp_java.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newapp_java.R;
import com.example.newapp_java.viewmodel.NewsViewmodel;


public class ArticleFragment extends Fragment {

    RecyclerView recyclerView;
    NewsViewmodel newsViewmodel;
    NewsAdapter newsAdapter = new NewsAdapter();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_article, container, false);
        // Inflate the layout for this fragment

        recyclerView = root.findViewById(R.id.newsRecycler);

        newsViewmodel = new ViewModelProvider(this)
                .get(NewsViewmodel.class);

        newsViewmodel.loadResult();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(newsAdapter);

        newsViewmodel.getResult().observe(
                getViewLifecycleOwner(), news -> {
                    newsAdapter.updateArticle(news.getArticles());
                    Log.d("Value", "onResponse:  Successful ");
                }
        );

       return root;



    }
}