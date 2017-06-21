package com.demo.android.mymvpdemo.model;


import com.demo.android.mymvpdemo.bean.User;

public interface OnLoginListener
{
    void loginSuccess(User user);

    void loginFailed();
}
