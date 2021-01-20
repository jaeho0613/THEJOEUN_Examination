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
import org.jaeho.main.databinding.FragmentBreakingNewsBinding;
import org.jaeho.main.databinding.FragmentPopularNewsBinding;

import java.util.HashMap;
import java.util.List;

import background.DBService;
import database.data.BaseNews;
import database.data.BreakingNews;
import database.data.PopularNews;
import database.viewmodel.BreakingNewsViewModel;
import database.viewmodel.PopularNewsViewModel;
import recycler.adapter.BreakingNewsAdapter;
import recycler.adapter.PopularNewsAdapter;
import rss.RssParsing;
import utility.SharedKey;
import utility.UtilSharedPreference;

public class FragmentPopularNews extends Fragment {

    FragmentPopularNewsBinding binding;
    PopularNewsViewModel model;
//    String popularNewsUrl = "http://rss.etnews.co.kr/Section903.xml";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPopularNewsBinding.inflate(inflater, container, false);

        PopularNewsAdapter adapter = new PopularNewsAdapter(new PopularNewsAdapter.NewsDiff());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        model = new ViewModelProvider(this).get(PopularNewsViewModel.class);
        model.getTodayNewsList().observe(getViewLifecycleOwner(), new Observer<List<PopularNews>>() {
            @Override
            public void onChanged(List<PopularNews> popularNews) {
                adapter.submitList(popularNews);
            }
        });

//        if (!UtilSharedPreference.getBoolean(getContext(), SharedKey.NEWS_UPDATE_POPULAR)) {
//
//            UtilSharedPreference.setBoolean(getContext(), SharedKey.NEWS_UPDATE_POPULAR, true);
//
//            new Thread(() -> {
//                HashMap<String, BaseNews> baseNewsList = RssParsing.parsing(popularNewsUrl);
//
//                for (String key : baseNewsList.keySet()) {
//                    model.insert(new PopularNews(key, baseNewsList.get(key)));
//                }
//            }).start();
//        }

        return binding.getRoot();
    }
}