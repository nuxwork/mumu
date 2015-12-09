package chat.mumu.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

import chat.mumu.R;

/**
 * Created by Jan on 11/26/2015.
 */
public class RegisterActivity extends BaseActivity{

    private AVUser mUser = new AVUser();
    private int mStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showStep(mStep = 1);
    }

    private void showStep(int step){
        mStep = step;
        int id = getResources().getIdentifier("act_register_" + step, "layout", getPackageName());
        setContentView(id);

        switch (step){
            case 1:
                ((EditText) findViewById(R.id.nickname)).addTextChangedListener(mTextWatcher);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    public void doNext(View v) {
        saveValue();

        if(mStep == 3) {
            register();
            return;
        }

        if(mStep == 4){
            verify();
            return;
        }

        showStep(mStep + 1);
    }

    @Override
    public void doBack(View v) {
        if(mStep == 1)
            super.doBack(v);

        showStep(mStep - 1);
    }

    private void saveValue(){
        switch (mStep){
            case 1:
                mUser.put("nickname", ((EditText) findViewById(R.id.nickname)).getText().toString());
                break;
            case 2:
                mUser.put("birthday", ((EditText) findViewById(R.id.birthday)).getText().toString());
                mUser.put("location", ((EditText) findViewById(R.id.location)).getText().toString());
                int id = ((RadioGroup)findViewById(R.id.gender)).getCheckedRadioButtonId();
                mUser.put("gender", id == R.id.male ? "male" : "female");
                break;
            case 3:
                mUser.setMobilePhoneNumber(((EditText) findViewById(R.id.phone)).getText().toString());
                mUser.setUsername(((EditText) findViewById(R.id.phone)).getText().toString());
                mUser.setPassword(((EditText) findViewById(R.id.password)).getText().toString());
                break;
        }
    }

    private void validate(){
        switch (mStep){
            case 1:
                boolean enable = ((EditText) findViewById(R.id.nickname)).getText().toString().length() > 0;
                findViewById(R.id.next).setEnabled(enable);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    private void register(){
        mUser.signUpInBackground(new SignUpCallback() {
            public void done(AVException e) {
                if (e == null) {
                    // 接收验证码
                    showStep(mStep + 1);
                } else {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        //TODO 如果注册到一半，没有验证码
    }

    public void verify(){
        String smsCode = ((EditText) findViewById(R.id.code)).getText().toString();
        AVUser.verifyMobilePhoneInBackground(smsCode, new AVMobilePhoneVerifyCallback() {

            @Override
            public void done(AVException e) {
                if(e == null){
                    Toast.makeText(getBaseContext(), "注册成功", Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK);
                    finish();
                }else {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            validate();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
