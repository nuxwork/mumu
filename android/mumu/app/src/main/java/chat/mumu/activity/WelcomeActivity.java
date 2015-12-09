package chat.mumu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import chat.mumu.R;


/**
 * Created by Jan on 11/27/2015.
 */
public class WelcomeActivity extends BaseActivity{

    private Handler mHandler;
    private GridAdapter mAdapter;

    private List<String> mAnimPos = new ArrayList<String>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_welcome);

        mHandler = new WelcomeHandler();
        mAdapter = new GridAdapter(this);

        GridView grid = (GridView)findViewById(R.id.grid);
        grid.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.sendEmptyMessageDelayed(1, 1500);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeMessages(1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK){
            startActivity(new Intent(getBaseContext(), HomeActivity.class));
            finish();
        }
    }

    public void onButtonClicked(View v){
        int id = v.getId();
        switch (id){
            case R.id.register:
                startActivityForResult(new Intent(this, RegisterActivity.class), 1);
                break;
            case R.id.login:
                startActivityForResult(new Intent(this, LoginActivity.class), 2);
                break;
        }
    }

    private int getPos(){
        int pos = new Random().nextInt(mAdapter.getCount());
        while (pos ==4 || mAnimPos.contains(""+pos)) {
            pos = new Random().nextInt(mAdapter.getCount());
        }

        mAnimPos.add("" + pos);
        return pos;
    }

    private void startAnim(){
        final int pos = getPos();
        final View mask = mAdapter.getItem(pos);

        final AlphaAnimation animShow = new AlphaAnimation(1f, 0.0f);
        animShow.setDuration(1800);
        animShow.setFillAfter(true);
        animShow.setFillEnabled(true);
        animShow.setFillBefore(true);
        animShow.setRepeatCount(1);
        animShow.setRepeatMode(AlphaAnimation.REVERSE);
        animShow.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mask.clearAnimation();
                mAnimPos.remove("" + pos);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mask.startAnimation(animShow);
    }

    private class WelcomeHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    mHandler.sendEmptyMessageDelayed(1, 1800);
                    startAnim();
                    break;
                default:
                    break;
            }
        }
    }

    private class GridAdapter extends BaseAdapter {
        private Context mContext;
        private int mImgSize;
        private int mCount;
        private View[] mMasks;

        public GridAdapter(Context context){
            mContext = context;
            DisplayMetrics metric = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metric);
            mImgSize = metric.widthPixels / 3;
            mCount =  (int)(metric.heightPixels/(metric.widthPixels/3f)*3f);
            mMasks = new View[mCount];
        }

        @Override
        public int getCount() {
            return mCount;
        }

        @Override
        public View getItem(int position) {
            return mMasks[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(mContext, R.layout.act_welcome_grid_item, null);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(mImgSize, mImgSize);
            v.setLayoutParams(params);

            ImageView image = (ImageView)v.findViewById(R.id.image);
            View mask = v.findViewById(R.id.mask);
            int id = getResources().getIdentifier("ic_welcome_photo_"+(position+1), "mipmap", getPackageName());
            image.setImageResource(id);
            mMasks[position] = mask;

            if(position == 4){
                FrameLayout lay = (FrameLayout)v.findViewById(R.id.root);
                ImageView logo = new ImageView(mContext);
                FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(-1, -1);
                logo.setImageResource(R.mipmap.ic_welcome_logo);
                logo.setScaleType(ImageView.ScaleType.CENTER);
                logo.setPadding(mImgSize/4, mImgSize/4, mImgSize/4, mImgSize/4);
                lay.addView(logo);
            }
            return v;
        }
    }
}
