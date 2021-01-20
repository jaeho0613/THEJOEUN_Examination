package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jaeho.main.R;
import org.jaeho.main.databinding.FragmentBreakingNewsBinding;

import java.util.HashMap;
import java.util.List;

import background.DBService;
import database.data.BaseNews;
import database.data.BreakingNews;
import database.data.PopularNews;
import database.viewmodel.BreakingNewsViewModel;
import database.viewmodel.PopularNewsViewModel;
import recycler.adapter.BreakingNewsAdapter;
import rss.RssParsing;
import utility.SharedKey;
import utility.UtilSharedPreference;

public class FragmentBreakingNews extends Fragment {

    FragmentBreakingNewsBinding binding;
    BreakingNewsViewModel model;
//    String breakingNewsUrl = "http://rss.etnews.co.kr/Section902.xml";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBreakingNewsBinding.inflate(inflater, container, false);

        BreakingNewsAdapter adapter = new BreakingNewsAdapter(new BreakingNewsAdapter.NewsDiff());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        model = new ViewModelProvider(this).get(BreakingNewsViewModel.class);
        model.getTodayNewsList().observe(getViewLifecycleOwner(), new Observer<List<BreakingNews>>() {
            @Override
            public void onChanged(List<BreakingNews> breakingNews) {
                adapter.submitList(breakingNews);
            }
        });

//        if (!UtilSharedPreference.getBoolean(getContext(), SharedKey.NEWS_UPDATE_BREAKING)) {
//
//            UtilSharedPreference.setBoolean(getContext(), SharedKey.NEWS_UPDATE_BREAKING, true);
//
//            new Thread(() ->{
//                HashMap<String, BaseNews> baseNewsList = RssParsing.parsing(breakingNewsUrl);
//
//                for (String key : baseNewsList.keySet()) {
//                    model.insert(new BreakingNews(key, baseNewsList.get(key)));
//                }
//            }).start();
//        }

        return binding.getRoot();
    }
}