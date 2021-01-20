package recycler.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import database.data.BreakingNews;
import recycler.holder.BreakingNewsHolder;

public class BreakingNewsAdapter extends ListAdapter<BreakingNews, BreakingNewsHolder> {

    public BreakingNewsAdapter(@NonNull DiffUtil.ItemCallback<BreakingNews> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public BreakingNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BreakingNewsHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakingNewsHolder holder, int position) {
        BreakingNews breakingNews = getItem(position);
        holder.bind(breakingNews.baseNews.title, breakingNews.baseNews.content, breakingNews.guid);
    }

    public static class NewsDiff extends DiffUtil.ItemCallback<BreakingNews> {

        @Override
        public boolean areItemsTheSame(@NonNull BreakingNews oldItem, @NonNull BreakingNews newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull BreakingNews oldItem, @NonNull BreakingNews newItem) {
            return oldItem.guid.equals(newItem.guid);
        }
    }
}
