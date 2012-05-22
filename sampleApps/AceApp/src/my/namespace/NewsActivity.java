package my.namespace;

import android.app.Activity;
import android.os.Bundle;

public class NewsActivity extends Activity {
    /** Called when the activity is first created. */
    String rssResult = "";
    boolean item = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    }
}