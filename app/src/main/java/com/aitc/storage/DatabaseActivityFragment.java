package com.aitc.storage;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

public class DatabaseActivityFragment extends Fragment {
    ListView list;
    DBHelper db;

    private OnItemClickEventListener mListener;

    public DatabaseActivityFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnItemClickEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_database, container, false);
        list = (ListView) rootview.findViewById(R.id.database_list_view);

        db = new DBHelper(getActivity());

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.db_row_item, db.getAllPersons(), Const.LIST_COULMNS, Const.LIST_LAYOUT_IDS, 0);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("CLICKED", String.valueOf(id));
                mListener.onItemSelected(id);
            }
        });

        list.setAdapter(adapter);
        return rootview;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnItemClickEventListener {
        public void onItemSelected(long id);
    }
}
