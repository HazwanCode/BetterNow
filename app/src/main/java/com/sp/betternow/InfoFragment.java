package com.sp.betternow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class InfoFragment extends Fragment {

    private CardView GoWeb, GoWeb1, GoWeb2, GoWeb3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        GoWeb = view.findViewById(R.id.web);
        GoWeb.setOnClickListener(goWeb);

        GoWeb1 = view.findViewById(R.id.web1);
        GoWeb1.setOnClickListener(goWeb1);

        GoWeb2 = view.findViewById(R.id.web2);
        GoWeb2.setOnClickListener(goWeb2);

        GoWeb3 = view.findViewById(R.id.web3);
        GoWeb3.setOnClickListener(goWeb3);

        return view;
    }

    private View.OnClickListener goWeb = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Web.class);
            intent.putExtra("url", "https://www.healthline.com/health/depression/");
            startActivity(intent);
        }
    };

    private View.OnClickListener goWeb1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Web.class);
            intent.putExtra("url","https://www.channelnewsasia.com/news/cnainsider/under-pressure-at-home-and-in-school-youths-battle-depression-10226122");
            startActivity(intent);
        }
    };

    private View.OnClickListener goWeb2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Web.class);
            intent.putExtra("url","https://www.mountelizabeth.com.sg/healthplus/article/dealing-with-depression/");
            startActivity(intent);
        }
    };

    private View.OnClickListener goWeb3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Web.class);
            intent.putExtra("url","https://www.healthline.com/health/how-to-help-a-depressed-friend#listen/");
            startActivity(intent);
        }
    };
}
