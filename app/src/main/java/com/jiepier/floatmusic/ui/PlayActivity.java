package com.jiepier.floatmusic.ui;

import android.os.Bundle;

import com.jiepier.floatmusic.R;
import com.jiepier.floatmusic.base.BaseActivity;
import com.jiepier.floatmusic.widget.RotateView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JiePier on 16/11/13.
 */

public class PlayActivity extends BaseActivity {

    @BindView(R.id.rotateView)
    RotateView rotateView;

    @Override
    public int initContentView() {
        return R.layout.activity_play;
    }

    @Override
    public void initUiAndListener() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return false;
    }

    @Override
    public void onPublish(int percent) {
        rotateView.rotate(percent*360);
    }

    @Override
    public void onChange(int position) {

    }

}
