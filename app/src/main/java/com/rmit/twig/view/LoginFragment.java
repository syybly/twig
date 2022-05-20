package com.rmit.twig.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rmit.twig.controller.SignInController;
import com.rmit.twig.R;

public class LoginFragment extends Fragment {

    private EditText Email;
    private EditText password;
    private Button signin;
    private Button signup;
    private Context context;
    private TextView forgetpass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.loginpage, container, false);
        context=view.getContext();
        signin=view.findViewById(R.id.Login);
        signin.setOnClickListener(new SignInController(getActivity()));
        forgetpass=view.findViewById(R.id.Fgtpawd);
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
