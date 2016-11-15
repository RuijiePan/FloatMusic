package com.jiepier.floatmusic.ui;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jiepier.floatmusic.R;
import com.jiepier.floatmusic.adapter.MusicAdapter;
import com.jiepier.floatmusic.base.App;
import com.jiepier.floatmusic.base.BaseActivity;
import com.jiepier.floatmusic.bean.Music;
import com.jiepier.floatmusic.util.MusicUtil;
import com.jiepier.floatmusic.util.RecyclerViewDivider;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JiePier on 16/11/12.
 */

public class MainActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener{

    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.play_progress)
    SeekBar playProgress;
    @BindView(R.id.tv_play_title)
    TextView tvPlayTitle;
    @BindView(R.id.tv_play_artist)
    TextView tvPlayArtist;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    private boolean isPause;
    private MusicAdapter mMusicAdapter;

    @Override
    public int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initUiAndListener() {

        mMusicAdapter = new MusicAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mMusicAdapter);
        recyclerView.addItemDecoration(new RecyclerViewDivider(
                this, RecyclerViewDivider.VERTICAL_LIST));
        mMusicAdapter.setOnItemClickLisetener(
                new MusicAdapter.OnItemClickLisetener() {
                    @Override
                    public void onItemClick(int position) {
                        mPlayService.play(position);
                        Intent intent = new Intent(App.sContext,PlayActivity.class);
                        startActivity(intent);
                    }
                });

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
        if (isPause)
            return;
        playProgress.setProgress(percent);
    }

    @Override
    public void onChange(int position) {
        Music music = MusicUtil.sMusicList.get(position);
        tvPlayTitle.setText(music.getTitle());
        tvPlayArtist.setText(music.getArtist());
    }

    @Override
    protected void onStart() {
        super.onStart();
        allowBindService();
    }

    //解除绑定歌曲播放服务
    @Override
    protected void onStop() {
        super.onStop();
        allowUnBindService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @OnClick({R.id.iv_play_icon, R.id.iv_pre, R.id.iv_play, R.id.iv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_play_icon:
                break;
            case R.id.iv_pre:
                mPlayService.pre();
                break;
            case R.id.iv_play:
                if (mPlayService.isPlaying()) {
                    mPlayService.pause();
                    isPause = true;
                    ivPlay.setImageResource(android.R.drawable.ic_media_pause);
                } else {
                    mPlayService.resume();
                    isPause = false;
                    ivPlay.setImageResource(android.R.drawable.ic_media_play);
                }
                break;
            case R.id.iv_next:
                getPlayService().next();
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        seekBar.setProgress(seekBar.getProgress());
    }
}
