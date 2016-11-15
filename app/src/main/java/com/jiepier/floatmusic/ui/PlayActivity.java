package com.jiepier.floatmusic.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.jiepier.floatmusic.R;
import com.jiepier.floatmusic.base.App;
import com.jiepier.floatmusic.base.BaseActivity;
import com.jiepier.floatmusic.util.ImageTools;
import com.jiepier.floatmusic.widget.CdView;
import com.jiepier.floatmusic.widget.RotateView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JiePier on 16/11/13.
 */

public class PlayActivity extends BaseActivity {

    @BindView(R.id.cdView)
    RotateView rotateView;

    @Override
    public int initContentView() {
        return R.layout.activity_play;
    }

    @Override
    public void initUiAndListener() {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);
        rotateView.setCdImage(ImageTools.scaleBitmap(bmp,
                (int) (App.sScreenWidth * 0.3)));
        rotateView.startRoll();
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return true;
    }

    @Override
    public void onPublish(int percent) {
        Log.w("haha",percent+"");
        rotateView.rotate(percent);
    }

    @Override
    public void onChange(int position) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        allowBindService();
    }

    @Override
    protected void onPause() {
        allowUnBindService();
        super.onPause();
    }

}
