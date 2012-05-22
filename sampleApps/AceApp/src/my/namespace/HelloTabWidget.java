package my.namespace;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;


public class HelloTabWidget extends TabActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, PeopleActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("people").setIndicator("", //People
	                      res.getDrawable(R.drawable.ic_tab_people))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, LocationActivity.class);
	    spec = tabHost.newTabSpec("location").setIndicator("", //Location
	                      res.getDrawable(R.drawable.ic_tab_location))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, NewsActivity.class);
	    spec = tabHost.newTabSpec("news").setIndicator("", //News
	                      res.getDrawable(R.drawable.ic_tab_news))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, ProfileActivity.class);
	    spec = tabHost.newTabSpec("ProfileTab").setIndicator("", //Profile
	                      res.getDrawable(R.drawable.ic_tab_profile))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    tabHost.setCurrentTab(2);
	}
}
