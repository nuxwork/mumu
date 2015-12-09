package chat.mumu.activity;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

import io.rong.imkit.RongIM;

/**
 * Created by Jan on 10/20/2015.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "xeUSR0WWkYieIImIOxcSMnvo-gzGzoHsz", "od6y0BxHrpTs3LLn45m4Npi3");

        /**
         * 初始化融云
         */
        RongIM.init(this);
    }
}
