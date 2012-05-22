package gokul.apps.ns;

import java.io.IOException;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

	public class CheckWhereYouAreOnMapActivity extends MapActivity {
	
		 LocationManager locMgr;
		 MyLocationListener locLstnr;
		 Location mloc;
		 private TextView tvlat;
		 private TextView tvlong;
		 private MapView mapView;
		 private MapController mc;
		 
		 private final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
		 private final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
		 int maxResults = 2;
			
		 
		
		 /** Called when the activity is first created. */
		 @Override
		 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.main);
		
		 mapView = (MapView) findViewById(R.id.mapview1);
		 mc = mapView.getController();
		 mapView.setBuiltInZoomControls(true);
		 
		 tvlat = (TextView)findViewById(R.id.tv_lat);
		 tvlong = (TextView)findViewById(R.id.tv_long);
		 
		 tvlat.setText("0");
		 tvlong.setText("0 ");
		 
		 locMgr = (LocationManager) getSystemService (Context.LOCATION_SERVICE);
		 locLstnr = new MyLocationListener();
		 locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, MINIMUM_TIME_BETWEEN_UPDATES, MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, locLstnr);
		 }
 
	 @Override
	 protected boolean isRouteDisplayed() {
		 return false;
	 }
	 
	 public class MyLocationListener implements LocationListener
	 {
		@Override
		public void onLocationChanged(Location loc)
		{
		 
			loc.getLatitude();
			loc.getLongitude();

//			String Text = "You are at: " + "Latitud = " + loc.getLatitude() + "Longitud = " + loc.getLongitude();
//			Toast.makeText( getApplicationContext(), Text, Toast.LENGTH_SHORT).show();
			 
			List<Address> myAddress = null;
			try {
				myAddress = new Geocoder(getBaseContext()).getFromLocation(loc.getLatitude(), loc.getLongitude(), maxResults);
				if(myAddress.size()>0) {
					Toast.makeText(getBaseContext(), "You are at "+myAddress.get(0).getThoroughfare(), Toast.LENGTH_LONG).show();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	         
			tvlat.setText(""+loc.getLatitude());
			tvlong.setText(""+loc.getLongitude());
				 
			this.gpsCurrentLocation();
		}

		public void gpsCurrentLocation() {
			String coordinates[] = {""+tvlat.getText(), ""+tvlong.getText()};
			
			double lat = Double.parseDouble(coordinates[0]);
			double lng = Double.parseDouble(coordinates[1]);
			 
			GeoPoint p = new GeoPoint(
			(int) (lat * 1E6),
			(int) (lng * 1E6));
			 
			mc.animateTo(p);
			mc.setZoom(17);
			 
			MyMapOverlays marker = new MyMapOverlays(p) ;
			List listOfOverLays = mapView.getOverlays();
			listOfOverLays.clear();
			listOfOverLays.add(marker);
			 
			mapView.invalidate();
			
		}

		@Override
		public void onProviderDisabled(String provider)
		{
			Toast.makeText( getApplicationContext(),  "Gps Disabled", Toast.LENGTH_SHORT ).show();
		}
	
		@Override
		public void onProviderEnabled(String provider)
		{
			Toast.makeText( getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();
		}
	
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras)
		{
		}

	}
	 
	/*My overlay Class starts*/
	class MyMapOverlays extends Overlay
	{
		GeoPoint location = null;
	
		public MyMapOverlays(GeoPoint location)
		{
			super();
			this.location = location;
		}
	
		@Override
		public void draw(Canvas canvas, MapView mapView, boolean shadow)
		{
	
			super.draw(canvas, mapView, shadow);
			//translate the screen pixels
			Point screenPoint = new Point();
			mapView.getProjection().toPixels(this.location, screenPoint);
		
			//add the image
			canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cross),
			screenPoint.x, screenPoint.y , null); //Setting the image &nbsp;location on the screen (x,y).
		}
		

		@Override
		public boolean onTouchEvent(MotionEvent event, MapView mapView)
		{
			//---when user lifts his finger---
			if (event.getAction() == 1) {
				GeoPoint p = mapView.getProjection().fromPixels(
				(int) event.getX(),
				(int) event.getY());
//				Toast.makeText(getBaseContext(), p.getLatitudeE6() / 1E6 + "," +p.getLongitudeE6() /1E6 ,
//				Toast.LENGTH_SHORT).show();
				List<Address> myAddress = null;
					try {
						myAddress = new Geocoder(getBaseContext()).getFromLocation(p.getLatitudeE6()/ 1E6, p.getLongitudeE6()/ 1E6, 2);
						if(myAddress.size()>0) {
							Toast.makeText(getBaseContext(), myAddress.get(0).getThoroughfare(), Toast.LENGTH_LONG).show();
						}
						else
							Toast.makeText(getBaseContext(), "Unknown address", Toast.LENGTH_SHORT).show();
					} catch (IOException e) {
						e.printStackTrace();
						Log.e(this.toString(), "Caught IOException in onTouch method", e);
					}
			}
			return false;
		}
	} 	/*My overlay Class ends*/
	
}

