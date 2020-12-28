package com.example.tubes03_a;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {
    private FragmentListener listener;
    private TextView tvTitle;
    private BikeReport report;

    public DetailsFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.details_fragment,container, false);

        this.tvTitle = view.findViewById(R.id.tv_title);
        Log.d("okk", "increate: "+this.tvTitle);

        Bundle b = getArguments();
        if (b != null) {
            this.report = b.getParcelable("report");
            this.tvTitle.setText(this.report.getTitle());

//            String tags = "";
//            for (int i = 0; i < menu.getTag().length(); i++) {
//                if (i != menu.getTag().length() - 1) {
//                    tags = menu.getTag() ;
//                } else {
//                    tags = menu.getTag();
//                }
//            }
//            this.tvTag.setText(tags);

        } else {
            Log.d("debug", "onCreateView: Report Not Found");
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
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

    public static DetailsFragment newInstance(){
        DetailsFragment fragment = new DetailsFragment();
        return fragment;
    }

    public void renderDetails(BikeReport report) {
        Log.d("okk", "renderDetails: "+this.tvTitle);
        this.tvTitle.setText("cek");
    }
}
