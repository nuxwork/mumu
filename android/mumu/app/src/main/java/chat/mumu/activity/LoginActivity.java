package chat.mumu.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.LogInCallback;

import java.util.List;

import chat.mumu.R;

/**
 * Created by Jan on 11/26/2015.
 */
public class LoginActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        ((TextView)findViewById(R.id.code)).setText(Html.fromHtml("<u>选择手机区号</u>"));
        ((TextView)findViewById(R.id.question)).setText(Html.fromHtml("<u>登录遇到困难?</u>"));
    }

    public void doLogin(View v){
        String account = ((EditText)findViewById(R.id.account)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();

        AVUser.logInInBackground(account, password, new LogInCallback() {
            public void done(AVUser user, AVException e) {
                if (user != null) {
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*AVQuery<AVObject> query = new AVQuery<>("user");
        query.whereEqualTo("account", account);
        query.whereEqualTo("password", password);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    if (list.size() == 1) {
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(getBaseContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "AVException", Toast.LENGTH_SHORT).show();

                }
            }
        });*/
    }
}
