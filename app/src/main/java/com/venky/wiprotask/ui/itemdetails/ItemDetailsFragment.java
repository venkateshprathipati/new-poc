package com.venky.wiprotask.ui.itemdetails;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.venky.wiprotask.R;
import com.venky.wiprotask.data.network.model.TitleResponseModel;
import com.venky.wiprotask.di.component.ActivityComponent;
import com.venky.wiprotask.ui.base.BaseFragment;
import com.venky.wiprotask.ui.main.MainActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailsFragment extends BaseFragment implements ItemDetailsMvpView,
        ItemDetailsAdapter.Callback,
        SwipeRefreshLayout.OnRefreshListener{

    private static final String LIST_STATE = "recycler_state";
    private Parcelable mListState = null;

    @Inject
    ItemDetailsMvpPresenter<ItemDetailsMvpView> mPresenter;

    @Inject
    ItemDetailsAdapter mItemDetailsAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.titleRecyclerView)
    RecyclerView mRecyclerView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Restore RecyclerView state
        if(savedInstanceState != null)
            mListState = savedInstanceState.getParcelable(LIST_STATE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_details_activity, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mItemDetailsAdapter);
        mRecyclerView.setHasFixedSize(true);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mPresenter.loadData();
    }

    @Override
    public void swipeToRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void updateRows(List<TitleResponseModel.Row> rowList) {
        mItemDetailsAdapter.addItems(rowList);
    }

    @Override
    public void updateTitle(String title) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(title);
}

    @Override
    public void onRefresh() {
        mItemDetailsAdapter.clearList();
        mPresenter.loadData();
        hideLoading();
    }

    @Override
    public void clear(List<TitleResponseModel.Row> rowList) {
        rowList.clear();
    }

    @Override
    public void onRowEmptyViewRetryClick() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save list state
        mListState = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE, mListState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
