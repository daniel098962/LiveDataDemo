package com.somnics.livedatademo;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

/**
 * Created by Daniel on 2021/4/1.
 */
public class MainViewModel extends ViewModel {

    public Input input = new Input();
    public Output output = new Output();

    private Integer mClickTimes = 0;

    public boolean setUp(LifecycleOwner lifecycleOwner) {

        boolean isSetUp = false;

        try {

            //region 訂閱 當按鈕點擊時 做對應動作
            input.getReceiveButtonClick().observe(lifecycleOwner, new Observer<View>() {
                @Override
                public void onChanged(View view) {
                    Log.d("///", "MainViewModel receive button click");
                    mClickTimes++;
                    output.getButtonClickTimes().setValue(mClickTimes);
                }
            });
            //endregion

            isSetUp = true;
        } catch (Exception e) {
            Log.d("///", "MainViewModel setUp error: " + e);
        }

        Log.d("///", "MainViewModel isSetUp: " + isSetUp);
        return isSetUp;
    }

    /**
     * 定義 ViewModel會接收到的部分
     */
    public class Input {

        private MutableLiveData<View> mReceiveButtonClick;
        public MutableLiveData<View> getReceiveButtonClick() {
            if (mReceiveButtonClick == null) {
                mReceiveButtonClick = new MutableLiveData<>();
            }
            return mReceiveButtonClick;
        }
    }

    /**
     * 定義 ViewModel會傳送出去的部分
     */
    public class Output {
        private MutableLiveData<Integer> mButtonClickTimes;
        public MutableLiveData<Integer> getButtonClickTimes() {
            if (mButtonClickTimes == null) {
                mButtonClickTimes = new MutableLiveData<>();
            }
            return mButtonClickTimes;
        }
    }
}
