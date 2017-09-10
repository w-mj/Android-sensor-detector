package wmj.sensordetector;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

import java.util.Locale;

public class SensorInfo extends android.support.v7.widget.AppCompatTextView {
    public SensorInfo(Context context) {
        super(context);
    }

    public void setText(SensorEvent sensorEvent) {
        float values[] = sensorEvent.values;
        Sensor sensor = sensorEvent.sensor;
        setText(String.format("name:%s\n", sensor.getName()));
        for (int i = 0; i < values.length; i++) {
            append(String.format(Locale.CHINA, "value%d = %f\n", i, values[i]));
        }
    }
}
