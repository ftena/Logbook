package com.tarlic.logbook;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * The container parameter passed to onCreateView() is the parent ViewGroup (from the activity's layout)
 * in which your fragment layout will be inserted. The savedInstanceState parameter is a Bundle that
 * provides data about the previous instance of the fragment, if the fragment is being resumed
 * (restoring state is discussed more in the section about Handling the Fragment Lifecycle).
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class TopFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
   	
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_main_top, container, false);
        
    }
}
