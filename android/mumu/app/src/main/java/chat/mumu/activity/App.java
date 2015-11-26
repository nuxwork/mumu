package chat.mumu.activity;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by Jan on 10/20/2015.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化融云
         */
        RongIM.init(this);
    }
}
