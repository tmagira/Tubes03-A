package com.example.tubes03_a;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailsFragment extends Fragment {
    private FragmentListener listener;
    private TextView tvTitle, tvType, tvDate, tvAddress, tvDesc;
    private ImageView ivPic;
    private BikeReport report;

    public DetailsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.details_fragment,container, false);

        //Assign variables
        this.tvTitle = view.findViewById(R.id.tv_title);
        this.tvType = view.findViewById(R.id.tv_type);
        this.tvDate = view.findViewById(R.id.tv_date);
        this.tvAddress = view.findViewById(R.id.tv_address);
        this.tvDesc = view.findViewById(R.id.tv_desc);
        this.ivPic = view.findViewById(R.id.iv_picture);

        //Membaca Bundle
        Bundle b = getArguments();
        if (b != null) {
            this.report = b.getParcelable("report");

            //Reformat UNIX Timestamp
            long unixSeconds = this.report.getOccurredAt();
            Date date = new java.util.Date(unixSeconds*1000L);
            SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = simpleDateFormat.format(date);

            //Set texts
            this.tvTitle.setText(this.report.getTitle());
            this.tvType.setText(this.report.getType());
            this.tvDate.setText(String.valueOf(formattedDate));
            this.tvAddress.setText(this.report.getAddress());
            this.tvDesc.setText(this.report.getDesc());



            Picasso.get().load(this.report.getLink()).into(this.ivPic);
        } else {
            Toast.makeText(getActivity(), "Report Not Found",Toast.LENGTH_LONG).show();
        }

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

    public static DetailsFragment newInstance(){
        DetailsFragment fragment = new DetailsFragment();
        return fragment;
    }

}
