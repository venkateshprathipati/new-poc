package com.venky.wiprotask.ui;

import com.venky.wiprotask.data.DataManager;
import com.venky.wiprotask.data.network.model.TitleResponseModel;
import com.venky.wiprotask.rx.TestSchedulerProvider;
import com.venky.wiprotask.ui.itemdetails.ItemDetailsMvpView;
import com.venky.wiprotask.ui.itemdetails.ItemDetailsPresenter;
import com.venky.wiprotask.ui.main.MainMvpView;
import com.venky.wiprotask.ui.main.MainPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ItemDetailsPresenterTest {

    @Mock
    ItemDetailsMvpView mItemDetailsMvpView;
    @Mock
    DataManager mMockDataManager;

    private ItemDetailsPresenter<ItemDetailsMvpView> mItemDetailsPresenter;
    private TestScheduler mTestScheduler;

    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mItemDetailsPresenter = new ItemDetailsPresenter<>(
                mMockDataManager,
                testSchedulerProvider,
                compositeDisposable);
        mItemDetailsPresenter.onAttach(mItemDetailsMvpView);
    }

    @Test
    public void testServerLoginSuccess() {

        TitleResponseModel bankDetailsModel = new TitleResponseModel();

        doReturn(Single.just(bankDetailsModel))
                .when(mMockDataManager)
                .getTitleDetails();

        mItemDetailsPresenter.loadData();

        mTestScheduler.triggerActions();

        verify(mItemDetailsMvpView).showLoading();
        verify(mItemDetailsMvpView).hideLoading();
        verify(mItemDetailsMvpView).swipeToRefresh();

    }

    @After
    public void tearDown() throws Exception {
        mItemDetailsPresenter.onDetach();
    }


}
