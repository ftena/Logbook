package com.tarlic.logbook;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * The container parameter passed to onCreateView() is the parent ViewGroup (from the activity's layout)
 * in which your fragment layout will be inserted. The savedInstanceState parameter is a Bundle that
 * provides data about the previous instance of the fragment, if the fragment is being resumed
 * (restoring state is discussed more in the section about Handling the Fragment Lifecycle).
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DetailsFragment extends Fragment {
	
	int mNum;
	
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	 
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	
        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }
    	
        View view = inflater.inflate(R.layout.activity_main_detail, container, false); 
        
        TextView tv = (TextView) view.findViewById(R.id.detailtext);
        
        tv.setText("Hooray! " + getShownIndex());
    	
        // Inflate the layout for this fragment
        return view;
        
    }
    
    /**
     * Create a new instance of DetailsFragment, initialized to
     * show the text at 'index'.
     */
    public static DetailsFragment newInstance(int index) {
        DetailsFragment f = new DetailsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        
        return f;
    }
    
    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }
}
