package recycler.adapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import database.data.TodayNews;
import recycler.holder.TodayNewsHolder;

public class TodayNewsAdapter extends ListAdapter<TodayNews, TodayNewsHolder> {

    public TodayNewsAdapter(@NonNull DiffUtil.ItemCallback<TodayNews> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public TodayNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TodayNewsHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayNewsHolder holder, int position) {
        TodayNews todayNews = getItem(position);
        holder.bind(todayNews.baseNews.title, todayNews.baseNews.content);
    }

    public static class NewsDiff extends DiffUtil.ItemCallback<TodayNews> {

        @Override
        public boolean areItemsTheSame(@NonNull TodayNews oldItem, @NonNull TodayNews newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull TodayNews oldItem, @NonNull TodayNews newItem) {
            return oldItem.guid.equals(newItem.guid);
        }
    }
}
