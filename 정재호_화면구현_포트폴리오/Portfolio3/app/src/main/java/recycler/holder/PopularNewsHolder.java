package recycler.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jaeho.main.databinding.ItemPopularNewBinding;

import database.data.BaseNews;
import database.data.PopularNews;

public class PopularNewsHolder extends RecyclerView.ViewHolder {

    ItemPopularNewBinding binding;

    public PopularNewsHolder(@NonNull View itemView, ItemPopularNewBinding binding) {
        super(itemView);

        this.binding = binding;
    }

    public void bind(String title, String content) {
        PopularNews popularNews = new PopularNews(new BaseNews());
        popularNews.baseNews.title = title;
        popularNews.baseNews.content = content;

        binding.setPopularNews(popularNews);
    }

    public static PopularNewsHolder create(ViewGroup parent) {
        ItemPopularNewBinding binding = ItemPopularNewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PopularNewsHolder(binding.getRoot(), binding);
    }
}
