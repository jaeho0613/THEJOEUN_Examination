package recycler.holder;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jaeho.main.databinding.ItemTodayNewBinding;

import database.data.BaseNews;
import database.data.TodayNews;

public class TodayNewsHolder extends RecyclerView.ViewHolder {

    ItemTodayNewBinding binding;

    public TodayNewsHolder(@NonNull View itemView, ItemTodayNewBinding binding) {
        super(itemView);

        this.binding = binding;
    }

    public void bind(String title, String content) {
        TodayNews todayNews = new TodayNews(new BaseNews());
        todayNews.baseNews.title = title;
        todayNews.baseNews.content = content;

        binding.setTodayNews(todayNews);
    }

    public static TodayNewsHolder create(ViewGroup parent) {
        ItemTodayNewBinding binding = ItemTodayNewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TodayNewsHolder(binding.getRoot(), binding);
    }
}
