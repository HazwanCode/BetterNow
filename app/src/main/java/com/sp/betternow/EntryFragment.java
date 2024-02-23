package com.sp.betternow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class EntryFragment extends Fragment {

    private Cursor model = null;
    private DataAdapter adapter = null;
    private ListView list;
    private DataHelper helper = null;
    private TextView empty, empty1 = null;
    //private ImageButton cardButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry, container, false);

        empty = view.findViewById(R.id.empty);
        empty1 = view.findViewById(R.id.empty1);
        helper = new DataHelper(this.getContext());
        list = view.findViewById(R.id.list);
        model = helper.getAll();
        adapter = new DataAdapter(this.getContext(), model, 0);
        list.setOnItemClickListener(onListClick);
        list.setAdapter(adapter);

        return view;
    }

//    public void showPopup(View v) {
//        PopupMenu popup = new PopupMenu(this.getContext(), v);
//        popup.setOnMenuItemClickListener(this);
//        popup.inflate(R.menu.cardoptions);
//        popup.show();
//    }

//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.update:
//                Toast.makeText(this.getContext(), "Update", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.delete:
//                Toast.makeText(this.getContext(), "Delete", Toast.LENGTH_SHORT).show();
//                return true;
//            default:.
//                return false;
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        if (model != null) {
            model.close();
        }
        model = helper.getAll();
        if (model.getCount() > 0) {
            empty.setVisibility(View.INVISIBLE);
            empty1.setVisibility(View.INVISIBLE);

        }
        adapter.swapCursor(model);
    }

    @Override
    public void onDestroy() {
        helper.close();
        super.onDestroy();
    }

        private AdapterView.OnItemClickListener onListClick =  new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {

            android.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            //alertDialog.setMessage("Call Number?");
            alertDialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    model.moveToPosition(position);
                    String recordID = helper.getID(model);
                    Intent intent;
                    intent = new Intent(getActivity(), EntryInput.class);
                    intent.putExtra("ID", recordID);
                    startActivity(intent);

                }
            });

            alertDialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                      helper.remove(id);

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, new EntryFragment());
                    fragmentTransaction.commit();


                }
            });

            alertDialog.show();

//            model.moveToPosition(position);
//            String recordID = helper.getID(model);
//            Intent intent;
//            intent = new Intent(getActivity(), EntryInput.class);
//            intent.putExtra("ID", recordID);
//            startActivity(intent);
        }
    };

    static class RestaurantHolder {
        private TextView entName, entDesc, entDate, entMood = null;
        private ImageView pic = null;

        RestaurantHolder(View row) {
            entName = row.findViewById(R.id.enName);
            entDesc = row.findViewById(R.id.enDesc);
            pic = row.findViewById(R.id.icon);
            entDate = row.findViewById(R.id.enDate);
            entMood = row.findViewById(R.id.enMood);
        }

        void populateFrom(Cursor c, DataHelper helper) {
//            if (helper.getActivityName(c)!= null)
//            {
//                Toast.makeText(helper.c,helper.getActivityName(c),Toast.LENGTH_LONG).show();
//            }
            entName.setText(helper.getActivityName(c));
            entDesc.setText(helper.getActivityDesc(c));

            byte[] image = helper.getImageData(c);
            try {
                Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
                pic.setImageBitmap(bmp);
                pic.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {

                pic.setVisibility(View.GONE);
            }

            //entDate.setText(helper.getActivityDate(c));
            String temp = helper.getActivityDate(c) + ", " + helper.getActivityTime(c);
            entDate.setText(temp);
            entMood.setText(helper.getActivityMood(c));

            if (helper.getActivityMood(c).equals("Happy")) {
                entMood.setTextColor(Color.parseColor("#AAF200"));
            } else if (helper.getActivityMood(c).equals("Neutral")) {
                entMood.setTextColor(Color.parseColor("#FF6600"));
            } else if (helper.getActivityMood(c).equals("Sad")) {
                entMood.setTextColor(Color.parseColor("#66CCFF"));
            }

        }
    }
    class DataAdapter extends CursorAdapter {
        DataAdapter(Context context, Cursor cursor, int flags) { super(context, cursor, flags); }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            RestaurantHolder holder = (RestaurantHolder) view.getTag();
            holder.populateFrom(cursor, helper);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.row, parent, false);
            RestaurantHolder holder = new RestaurantHolder(row);
            row.setTag(holder);
            return(row);
        }
    }
}
