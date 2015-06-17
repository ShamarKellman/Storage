package com.aitc.storage;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayPersonFragment extends Fragment {

    DBHelper dbHelper;

    TextView displayName;
    TextView displayPosition;
    TextView displayAge;
    TextView displayPhone;
    TextView displayEmail;
    TextView displayPlace;
    TextView displayStreet;

    public DisplayPersonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_display_person, container, false);
        dbHelper = new DBHelper(getActivity());
        int id = (int)this.getArguments().getLong("id");

        displayName = (TextView) rootView.findViewById(R.id.display_name);
        displayPosition = (TextView) rootView.findViewById(R.id.display_position);
        displayAge = (TextView) rootView.findViewById(R.id.display_age);
        displayPhone = (TextView) rootView.findViewById(R.id.display_phone);
        displayEmail = (TextView) rootView.findViewById(R.id.display_email);
        displayStreet = (TextView) rootView.findViewById(R.id.display_street);
        displayPlace = (TextView) rootView.findViewById(R.id.display_place);

        Cursor cursor = dbHelper.getData(id);

        displayName.setText(getValue(cursor, Const.DB_COLUMN_NAME));
        displayPosition.setText(cursor.getString(cursor.getColumnIndex(Const.DB_COLUMN_POSITION)));
        displayAge.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex(Const.DB_COLUMN_AGE))));
        displayEmail.setText(cursor.getString(cursor.getColumnIndex(Const.DB_COLUMN_EMAIL)));
        displayStreet.setText(cursor.getString(cursor.getColumnIndex(Const.DB_COLUMN_STREET)));
        displayPlace.setText(cursor.getString(cursor.getColumnIndex(Const.DB_COLUMN_PLACE)));

        // Inflate the layout for this fragment
        return rootView;
    }

    private String getValue(Cursor c, String col){
        return c.getString(c.getColumnIndex(col));
    }
}
