package chat.mumu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import chat.mumu.R;
import chat.mumu.adapter.HomeTabAdapter;
import chat.mumu.adapter.HomeTabPagerAdapter;
import chat.mumu.widget.SlideTabPager;

/**
 * Created by Jan on 10/20/2015.
 */
public class HomeActivity extends AppCompatActivity {

    private SlideTabPager		mSlideTabPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);

        mSlideTabPager = (SlideTabPager) findViewById(R.id.slideTabPager);
        initPagers(mSlideTabPager);
    }

    private void initPagers(SlideTabPager stp) {
        final int[] srcData = new int[] { R.drawable.rc_add_people,
		/* R.drawable.tab_btn_price_selector, */R.drawable.rc_an_voice_sent,
                R.drawable.rc_bg_albums_spinner };

        final int[] bgData = null;

        final String[] txtData = { "附近", "消息", "个人" };

        stp.setTabAdapter(new HomeTabAdapter(this, R.layout.home_tab_item, R.id.txt, R.id.img, txtData,
                srcData, bgData));
        stp.setPagerAdapter(new HomeTabPagerAdapter(this, getSupportFragmentManager()));
    }

}
