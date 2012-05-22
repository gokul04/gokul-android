package my.namespace;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

public class PeopleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textview = new TextView(this);
        textview.setText("Got one");
        setContentView(textview);
        
//        setContentView(R.layout.main);
//        Resources res = getResources();
//        TabHost tabHost = getTabHost();
//
//        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Tab1 title",
//                res.getDrawable(R.drawable.man_icon)).setContent(R.id.tab1Layout));

        
    }
}