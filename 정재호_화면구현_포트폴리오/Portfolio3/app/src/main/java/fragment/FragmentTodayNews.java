package fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jaeho.main.R;
import org.jaeho.main.databinding.FragmentTodayNewsBinding;

import java.util.HashMap;
import java.util.List;

import background.DBService;
import database.data.BaseNews;
import database.data.BreakingNews;
import database.data.PopularNews;
import database.data.TodayNews;
import database.viewmodel.TodayNewViewModel;
import recycler.adapter.TodayNewsAdapter;
import rss.RssParsing;
import utility.SharedKey;
import utility.UtilSharedPreference;

public class FragmentTodayNews extends Fragment {

    FragmentTodayNewsBinding binding;
    TodayNewViewModel model;
//    String todayNewsUrl = "http://rss.etnews.co.kr/Section901.xml";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTodayNewsBinding.inflate(inflater, container, false);

        TodayNewsAdapter adapter = new TodayNewsAdapter(new TodayNewsAdapter.NewsDiff());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        model = new ViewModelProvider(this).get(TodayNewViewModel.class);
        model.getTodayNewsList().observe(getViewLifecycleOwner(), new Observer<List<TodayNews>>() {
            @Override
            public void onChanged(List<TodayNews> todayNews) {
                adapter.submitList(todayNews);
            }
        });

//        if (!UtilSharedPreference.getBoolean(getContext(), SharedKey.NEWS_UPDATE_TODAY)) {
//
//            UtilSharedPreference.setBoolean(getContext(), SharedKey.NEWS_UPDATE_TODAY, true);
//
//            new Thread(() -> {
//                HashMap<String, BaseNews> baseNewsList = RssParsing.parsing(todayNewsUrl);
//
//                for (String key : baseNewsList.keySet()) {
//                    model.insert(new TodayNews(key, baseNewsList.get(key)));
//                }
//            }).start();
//        }

        return binding.getRoot();
    }
}