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

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        rongIM();
    }



    void rongIM(){
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

            @Override
            public UserInfo getUserInfo(String userId) {

                return findUserById(userId);//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
            }

        }, true);

        String token = "bHL8xfQwAZczXrYn200IvGsHEzGpwWKPzZuUJK9DqItW2hJI4/oCJ6SvCa5uPHNiYEggAxe6T6fZv2byPN54eyyQ57+ebcd+llW2DwIlDQrO+cEQSHP0sw==";
        /**
         * IMKit SDK调用第二步
         *
         * 建立与服务器的连接
         *
         */
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.e("MainActivity", "——onTokenIncorrect— -");
                //Connect Token 失效的状态处理，需要重新获取 Token
                Toast.makeText(MainActivity.this, "onTokenIncorrect", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String userId) {
                Log.e("MainActivity", "——onSuccess— -" + userId);
                Toast.makeText(MainActivity.this, "connect success", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
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
        if("5666f8f960b215d620fdd6f3".equals(userId)){
            user = new UserInfo(userId, "1", Uri.parse("http://v1.qzone.cc/avatar/201512/08/22/58/5666f01d7d9aa342.jpg!200x200.jpg"));
        }else if("1234".equals(userId)){
            user = new UserInfo(userId, "abc", Uri.parse("http://www.qq1234.org/uploads/allimg/140610/3_140610105824_9.jpg"));
        }else if("1".equals(userId)){
            user = new UserInfo(userId, "Jan", Uri.parse("http://p3.wmpic.me/article/2015/11/18/1447822025_NHqxYhmQ.jpg"));
        }
        return user;
    }
}
