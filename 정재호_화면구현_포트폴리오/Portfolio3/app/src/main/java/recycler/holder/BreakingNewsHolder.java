package recycler.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.jaeho.main.databinding.FragmentBreakingNewsBinding;
import org.jaeho.main.databinding.ItemBreakingNewBinding;

import database.data.BaseNews;
import database.data.BreakingNews;

public class BreakingNewsHolder extends RecyclerView.ViewHolder {

    ItemBreakingNewBinding binding;

    public BreakingNewsHolder(@NonNull View itemView, ItemBreakingNewBinding binding) {
        super(itemView);

        this.binding = binding;
    }

    public void bind(String title, String content, Long guid) {
        BreakingNews breakingNews = new BreakingNews(new BaseNews());
        breakingNews.baseNews.title = title;
        breakingNews.baseNews.content = content;
        breakingNews.guid = guid;

        binding.setBreakingNews(breakingNews);
    }

    public static BreakingNewsHolder create(ViewGroup parent) {
        ItemBreakingNewBinding binding = ItemBreakingNewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BreakingNewsHolder(binding.getRoot(), binding);
    }
}
