package chat.mumu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import chat.mumu.lego.activity.ActivityStack;

/**
 * Created by Jan on 11/27/2015.
 */
public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.push(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.remove(this);
    }

    public void doBack(View v){
        finish();
    }
}
