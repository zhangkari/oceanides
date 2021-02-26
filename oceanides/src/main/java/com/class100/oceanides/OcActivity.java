package com.class100.oceanides;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.class100.atropos.env.context.AtViewPieces;
import com.class100.atropos.generic.AtTracker;

public class OcActivity extends AppCompatActivity {
    protected AtViewPieces viewPiece;

    @Override
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isFullScreen()) {
            setFullScreen();
        }
        if (isHiddenNavigationBar()) {
            hideNavigationBar();
        }
        setContentView(R.layout.oc_activity);
        viewPiece = new AtViewPieces(findViewById(android.R.id.content));
        incubate();
        init();
        AtTracker.track(getPageName(), "onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        AtTracker.track(getPageName(), "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        AtTracker.track(getPageName(), "onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AtTracker.track(getPageName(), "onDestroy");
    }

    public void showProgress() {
        viewPiece.setVisibility(R.id.oc_layout_progress, View.VISIBLE);
    }

    public void hideProgress() {
        viewPiece.setVisibility(R.id.oc_layout_progress, View.GONE);
    }

    private void incubate() {
        int titleId = getTitleLayout();
        if (titleId != 0) {
            viewPiece.attach(R.id.oc_layout_title, titleId);
        }

        int contentId = getContentLayout();
        if (contentId != 0) {
            viewPiece.attach(R.id.oc_layout_content, contentId);
        }

        int progressId = getProgressLayout();
        if (progressId != 0) {
            viewPiece.attach(R.id.oc_layout_progress, progressId);
        }
    }

    protected void init() {

    }

    protected int getTitleLayout() {
        return R.layout.oc_layout_title;
    }

    protected int getContentLayout() {
        return 0;
    }

    protected int getProgressLayout() {
        return R.layout.oc_layout_progress;
    }

    protected String getPageName() {
        return getClass().getSimpleName();
    }

    protected void setFullScreen() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    protected boolean isFullScreen() {
        return true;
    }

    protected void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    protected boolean isHiddenNavigationBar() {
        return true;
    }
}
