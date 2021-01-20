package recycler.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import database.data.PopularNews;
import recycler.holder.PopularNewsHolder;

public class PopularNewsAdapter extends ListAdapter<PopularNews, PopularNewsHolder> {

    public PopularNewsAdapter(@NonNull DiffUtil.ItemCallback<PopularNews> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PopularNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return PopularNewsHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularNewsHolder holder, int position) {
        PopularNews popularNews = getItem(position);
        holder.bind(popularNews.baseNews.title, popularNews.baseNews.content);
    }

    public static class NewsDiff extends DiffUtil.ItemCallback<PopularNews> {

        @Override
        public boolean areItemsTheSame(@NonNull PopularNews oldItem, @NonNull PopularNews newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull PopularNews oldItem, @NonNull PopularNews newItem) {
            return oldItem.guid.equals(newItem.guid);
        }
    }
}
