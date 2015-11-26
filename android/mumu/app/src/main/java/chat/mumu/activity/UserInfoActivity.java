package chat.mumu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import chat.mumu.R;
import io.rong.imkit.RongIM;

/**
 * Created by Jan on 11/24/2015.
 */
public class UserInfoActivity extends Activity implements View.OnClickListener {
    private Button mBtnChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_userinfo);

        mBtnChat = (Button)findViewById(R.id.btn_chat);
        mBtnChat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (RongIM.getInstance() != null)
            RongIM.getInstance().startPrivateChat(this, "123", "title");
    }
}
