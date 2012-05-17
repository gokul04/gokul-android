package my.battery.ns;


import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class BatteryStatusActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent batteryIntent = this.getApplicationContext().registerReceiver(null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        if(level >=0 && scale > 0)
        {
        	float batteryPct = (level / (float)scale)*100;
        	TextView tv = (TextView) findViewById(R.id.textView2); 
        	//Toast.makeText(this,"Battery status:"+batteryPct+"%", Toast.LENGTH_LONG).show();
        	tv.setText(" "+batteryPct+"% charged");
        }
        
    }
}