package com.somnics.livedatademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mMainViewModel;

    private TextView mHelloTextView;
    private Button mClickButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelloTextView = findViewById(R.id.hello_world_text_view);
        mClickButton = findViewById(R.id.click_button);

        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        if (mMainViewModel.setUp(this)) {

            //region 當接收到 button click Times 數值改變時 進行對應動作
            mMainViewModel.output.getButtonClickTimes().observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer times) {
                    Log.d("///", "getButtonClickTimes: " + times);
                    mHelloTextView.setText(String.valueOf(times));
                }
            });
            //endregion

            //region 模擬 將點擊事件傳遞給ViewModel
            mClickButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("///", "Button Click");
                    mMainViewModel.input.getReceiveButtonClick().setValue(view);
                }
            });
            //endregion
        }
    }
}