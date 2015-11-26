package chat.mumu.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import chat.mumu.R;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

            @Override
            public UserInfo getUserInfo(String userId) {

                return findUserById(userId);//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
            }

        }, true);

        String token = "DF3Qy1rqxejNdOG5UiOTPwIdlv+4aA3Wom5Oa0mOqyCIdmoBlRs4Ub6Ncf9PrmWZOPpVqyDzMHWt0T7P7nsYMw==";

        /**
         * IMKit SDK调用第二步 
         *
         * 建立与服务器的连接 
         *
         */
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                //Connect Token 失效的状态处理，需要重新获取 Token
            }

            @Override
            public void onSuccess(String userId) {
                Log.e("MainActivity", "——onSuccess— -" + userId);
                Toast.makeText(MainActivity.this, "connect success", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("MainActivity", "——onError— -"+errorCode);
                Toast.makeText(MainActivity.this, "connect faild", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public UserInfo findUserById(String userId) {
        UserInfo user = null;
        if("123".equals(userId)){
            user = new UserInfo(userId, "abcd", Uri.parse("http://v1.qzone.cc/avatar/201308/22/10/36/521579394f4bb419.jpg!200x200.jpg"));
        }else if("1234".equals(userId)){
            user = new UserInfo(userId, "abc", Uri.parse("http://www.qq1234.org/uploads/allimg/140610/3_140610105824_9.jpg"));
        }else if("1".equals(userId)){
            user = new UserInfo(userId, "Jan", Uri.parse("http://p3.wmpic.me/article/2015/11/18/1447822025_NHqxYhmQ.jpg"));
        }
        return user;
    }
}
