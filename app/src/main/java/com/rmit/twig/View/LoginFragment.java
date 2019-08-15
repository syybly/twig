package com.rmit.twig.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.rmit.twig.Controller.DataHolder;
import com.rmit.twig.Controller.SignInController;
import com.rmit.twig.R;

public class LoginFragment extends Fragment {

    private EditText Email;
    private EditText password;
    private Button signin;
    private Button signup;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DataHolder dataHolder=new DataHolder();
        View view=inflater.inflate(R.layout.loginpage, container, false);
        context=view.getContext();
        signin=view.findViewById(R.id.Login);
//        signup=getActivity().findViewById(R.id.SignUp);
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context, SignupFragment.class);
//                startActivity(intent);
//            }
//        });
        signin.setOnClickListener(new SignInController(getActivity()));
        return view;
    }

}
