package com.venky.wiprotask.ui.itemdetails;

import com.venky.wiprotask.data.network.model.TitleResponseModel;
import com.venky.wiprotask.ui.base.MvpView;

import java.util.List;

public interface ItemDetailsMvpView extends MvpView {

    void showLoading();

    void hideLoading();

    void swipeToRefresh();

    void updateRows(List<TitleResponseModel.Row> rowList);

    void updateTitle(String title);

    void clear(List<TitleResponseModel.Row> rowList);
}
