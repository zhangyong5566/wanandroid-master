package com.zhang.wanandroiod.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.victor.loading.rotate.RotateLoading;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.mvp.contract.LoginContract;
import com.zhang.wanandroiod.mvp.contract.RegistContract;
import com.zhang.wanandroiod.mvp.model.bean.UserInfo;
import com.zhang.wanandroiod.mvp.presenter.LoginPresenter;
import com.zhang.wanandroiod.mvp.presenter.RegistPresenter;
import com.zhang.wanandroiod.mvp.ui.base.BaseActivity;
import com.zhang.wanandroiod.views.PlayAndroidToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements RegistContract.View, LoginContract.View {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.bt_regist)
    Button btRegist;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.userName_editText)
    TextInputEditText etUserName;
    @BindView(R.id.password_editText)
    TextInputEditText etUserPwd;
    @BindView(R.id.rotateloading)
    RotateLoading rotateloading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.bt_login, R.id.bt_regist})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.bt_login:
                String loginUsername = etUserName.getText().toString().trim();
                String loginPwd = etUserPwd.getText().toString().trim();
                if (!TextUtils.isEmpty(loginUsername)&&!TextUtils.isEmpty(loginPwd)) {
                    rotateloading.start();
                    new LoginPresenter(this).login(loginUsername,loginPwd);
                }else {
                    PlayAndroidToast.warning("账户或密码不能为空");
                }
                break;
            case R.id.bt_regist:
                String regUsername = etUserName.getText().toString().trim();
                String regPwd = etUserPwd.getText().toString().trim();
                if (!TextUtils.isEmpty(regUsername)&&!TextUtils.isEmpty(regPwd)) {
                    rotateloading.start();
                    new RegistPresenter(this).userRegist(regUsername,regPwd);
                }else {
                    PlayAndroidToast.warning("账户或密码不能为空");
                }
                break;
            default:
                break;
        }

    }

    /**
     * 注册成功
     * @param userInfo
     */
    @Override
    public void userRegistSucc(UserInfo userInfo) {
        rotateloading.stop();
        if (userInfo.getErrorCode()==0){
            PlayAndroidToast.success("注册成功");
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("registinfo",userInfo.getData());
            intent.putExtra("regist",bundle);
            setResult(RESULT_OK,intent);
            finish();
        }else if (userInfo.getErrorCode()==-1){
            PlayAndroidToast.error("注册失败"+userInfo.getErrorMsg());
        }else {
            PlayAndroidToast.error("注册失败"+userInfo.getErrorMsg());
        }
    }

    /**
     * 注册失败
     *
     * @param ex
     */
    @Override
    public void userRegistFail(Throwable ex) {
        rotateloading.stop();
        PlayAndroidToast.error("注册失败"+ex.getMessage());
    }

    /**
     * 登录成功
     * @param userInfo
     */
    @Override
    public void loginSucc(UserInfo userInfo) {
        rotateloading.stop();
        if (userInfo.getErrorCode()==0){
            PlayAndroidToast.success("登录成功");
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("registinfo",userInfo.getData());
            intent.putExtra("regist",bundle);
            setResult(RESULT_OK,intent);
            finish();
        }else if (userInfo.getErrorCode()==-1){
          PlayAndroidToast.error("密码错误");
        }else {
            PlayAndroidToast.error("登录失败"+userInfo.getErrorMsg());
        }

    }

    /**
     * 登录失败
     * @param ex
     */
    @Override
    public void logingFail(Throwable ex) {
        Log.i("Oking","登录失败"+ex.getMessage());

        rotateloading.stop();
    }
}
