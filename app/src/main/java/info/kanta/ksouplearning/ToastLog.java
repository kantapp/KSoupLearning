package info.kanta.ksouplearning;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class ToastLog
{
    public static void show(Activity activity,String message){
        Toast.makeText(activity, ""+message, Toast.LENGTH_LONG).show();
        Log.d(activity.getPackageName(), "show: "+message);
    }
}
