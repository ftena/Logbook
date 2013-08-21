package com.tarlic.logbook;

import java.util.ArrayList;
import java.util.Date;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
public class LogListFragment extends Fragment {
	
	boolean mDualPane;
	int mCurCheckPosition = 0;
	
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

	    //setContentView(R.layout.activity_main_list);
	}
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_main_list, container, false);
		
		ArrayList<LogItem> logItems = GetLogItems();

		/*
		 * Specifically, the fragment can access the Activity instance with getActivity()
		 * and easily perform tasks such as find a view in the activity layout.		 
		 * In this case, getActivity() doesn't have the element "R.id.list", so we
		 * use "view"
		 */
		ListView lv = (ListView) view.findViewById(R.id.itemslist);

		if (lv == null)
		{
			/*
			 *  Fragments don't subclass the Context you have to use the getActivity() method to get
			 *  the parent activity.
			 */
			Toast.makeText(getActivity(),
					"ERROR",
					Toast.LENGTH_SHORT).show();
		} else {
			lv.setAdapter(new LogListAdapter(getActivity(), logItems));				
		}
		
		// Inflate the layout for this fragment
		return view;

	}

	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        ListView lv = (ListView) getView().findViewById(R.id.itemslist);
        
        lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position,
	                long id) {
	            
				LogListAdapter item = (LogListAdapter) parent.getAdapter();
				
				LogItem logItem = (LogItem) item.getItem(position);
				
				showDetails(logItem, position);
				
			}
		});
        
        // Check to see if we have a frame in which to embed the details
        // fragment directly in the containing UI.
        View detailsFrame = getActivity().findViewById(R.id.viewer);
        
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
        
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDualPane) {
            // In dual-pane mode, the list view highlights the selected item.
        	lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        	
        	LogListAdapter item = (LogListAdapter) lv.getAdapter();
			
			LogItem logItem = (LogItem) item.getItem(mCurCheckPosition);
        	
            // Make sure our UI is in the correct state.
            showDetails(logItem, mCurCheckPosition);
        }
        

	}
	
	protected void showDetails(LogItem logItem, int index) {

		mCurCheckPosition = index;

		ListView lv = (ListView) getView().findViewById(R.id.itemslist);

		if (mDualPane) {
			// We can display everything in-place with fragments, so update
			// the list to highlight the selected item and show the data.
			lv.setItemChecked(index, true);

			// Check what fragment is currently shown, replace if needed.
			DetailsFragment details = (DetailsFragment)
					getFragmentManager().findFragmentById(R.id.viewer);
			
			if (details == null || details.getShownIndex() != index) {
				// Make new fragment to show this selection.
				details = DetailsFragment.newInstance(index);

				// Execute a transaction, replacing any existing fragment
				// with this one inside the frame.
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				
				ft.replace(R.id.viewer, details);
				
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();				
			}

		} else {
			// Otherwise we need to launch a new activity to display
			// the dialog fragment with selected text.
			Intent intent = new Intent();
			intent.setClass(getActivity(), DetailsActivity.class);
			intent.putExtra("index", index);
			startActivity(intent);
		}
	}


	/*public void onPause ()
	{
		
	}*/
	
	private ArrayList<LogItem> GetLogItems() {

		ArrayList<LogItem> logItems = new ArrayList<LogItem>();

		LogItem li = new LogItem();
		li.setTitle("Title #1");
		li.setText("Text #1");
		long lo = System.currentTimeMillis();
		Date date = new Date(lo);
		li.setDate(date);
		logItems.add(li);
		
		li = new LogItem();
		li.setTitle("Title #2");
		li.setText("Text #2");
		lo = System.currentTimeMillis();
		date = new Date(lo);
		li.setDate(date);
		logItems.add(li);
		
		li = new LogItem();
		li.setTitle("Title #3");
		li.setText("Text #3");
		lo = System.currentTimeMillis();
		date = new Date(lo);
		li.setDate(date);
		logItems.add(li);

		return logItems;
	}
}
