package com.example.tubes03_a;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class WelcomeFragment extends Fragment implements View.OnClickListener{
    private FragmentListener listener;
    private Button btnSearch;

    public WelcomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.welcome_fragment,container, false);

        this.btnSearch = view.findViewById(R.id.btn_search);

        this.btnSearch.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
        }else{
            throw new ClassCastException(context.toString()+ " Must Implement Fragment Listener");
        }
    }

    public static WelcomeFragment newInstance(){
        WelcomeFragment fragment = new WelcomeFragment();
        return fragment;
    }

    @Override
    public void onClick(View v) {
        listener.changePage(2);
    }
}
