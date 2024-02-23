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

public class MoreFragment extends Fragment {

    CardView createPass, goToCounselling, shareApp, helplines, about;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        createPass = view.findViewById(R.id.createpass);
        createPass.setOnClickListener(onCreate);

        goToCounselling = view.findViewById(R.id.gotocounsel);
        goToCounselling.setOnClickListener(onCounsel);

        shareApp = view.findViewById(R.id.share);
        shareApp.setOnClickListener(onShare);

        helplines = view.findViewById(R.id.helplines);
        helplines.setOnClickListener(onHelpline);

        about = view.findViewById(R.id.about);
        about.setOnClickListener(onAbout);

        return view;
    }

    private View.OnClickListener onCreate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), CreatePasswordActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener onCounsel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), CounsellingCentre.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener onShare = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String sharebody = "Hey guys, come try out this great new app that im using to learn and manage my crippling depression";
            //String sharesubj = "Test";
            intent.putExtra(Intent.EXTRA_TEXT, sharebody);
            startActivity(Intent.createChooser(intent, "Share using"));
        }
    };

    private View.OnClickListener onHelpline = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Helplines.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener onAbout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), About.class);
            startActivity(intent);
        }
    };
}
