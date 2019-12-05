package com.venky.wiprotask.ui.itemdetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.venky.wiprotask.R;
import com.venky.wiprotask.data.network.model.TitleResponseModel;
import com.venky.wiprotask.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemDetailsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<TitleResponseModel.Row> mRowList;

    public ItemDetailsAdapter(List<TitleResponseModel.Row> mRowList) {
        this.mRowList = mRowList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_title_layout, viewGroup, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.empty_item_view, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.onBind(i);
    }


    @Override
    public int getItemViewType(int position) {
        if (mRowList != null && mRowList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mRowList != null && mRowList.size() > 0) {
            return mRowList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<TitleResponseModel.Row> rowList) {
        mRowList.addAll(rowList);
        notifyDataSetChanged();
    }

    public void clearList(){
        mRowList.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.titleTextView)
        TextView titleTextView;

        @BindView(R.id.descTextView)
        TextView descTextView;

        @BindView(R.id.coverImageView)
        ImageView coverImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {
            coverImageView.setImageDrawable(null);
            titleTextView.setText("");
            descTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);
            final TitleResponseModel.Row row = mRowList.get(position);

            if (row.getTitle() != null) {
                titleTextView.setText(row.getTitle());
            }

            if (row.getDescription() != null) {
                descTextView.setText(row.getDescription());
            }

            Picasso.with(itemView.getContext())
                    .load(row.getImageHref())
                    .resize(200,250)
                    .centerCrop()
                    .error(R.drawable.ic_placeholder)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(coverImageView);
        }
    }


    public class EmptyViewHolder extends BaseViewHolder {

        @BindView(R.id.btn_retry)
        Button retryButton;

        @BindView(R.id.tv_message)
        TextView messageTextView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @OnClick(R.id.btn_retry)
        void onRetryClick() {
            if (mCallback != null)
                mCallback.onRowEmptyViewRetryClick();
        }
    }

    public interface Callback {
        void onRowEmptyViewRetryClick();
    }
}
