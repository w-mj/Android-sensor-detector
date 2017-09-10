package wmj.sensordetector;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    HashMap<String, SensorInfo> mViewList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        LinearLayout content = (LinearLayout)findViewById(R.id.content);
        List<Sensor> list = sm.getSensorList(Sensor.TYPE_ALL);
        for(Sensor s: list) {
            Log.i("on create", "检测到传感器" + s.getName());
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
            SensorInfo info = new SensorInfo(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            content.addView(info);
            info.setLayoutParams(params);
            mViewList.put(s.getName(), info);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        mViewList.get(event.sensor.getName()).setText(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
