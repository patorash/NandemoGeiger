package ninja.ehime.nangei;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class Setting extends Activity
	implements RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {
    private SeekBar seekBar1;
    private SeekBar seekBar2;
    private SeekBar seekBar3;
    private TextView value1;
    private TextView value2;
    private TextView value3;
    private RadioGroup radioAverage;
    private SharedPreferences sharedPref;
    private final int seekBar2_min = 1;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        
        Resources res = getResources();
        String appName = res.getString(R.string.app_name);
        sharedPref = getSharedPreferences(appName, Context.MODE_PRIVATE);
        
        radioAverage = (RadioGroup)findViewById(R.id.radio_average);
        radioAverage.setOnCheckedChangeListener(this);
    	RadioButton rb1 = (RadioButton)findViewById(R.id.rd_average1);
    	RadioButton rb2 = (RadioButton)findViewById(R.id.rd_average2);

        String keySeekBar1 = res.getString(R.string.pref_seekbar1_key);
        String keySeekBar2 = res.getString(R.string.pref_seekbar2_key);
        String keySeekBar3 = res.getString(R.string.pref_seekbar3_key);
        
        seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar)findViewById(R.id.seekBar3);
        
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);
        
        value1 = (TextView)findViewById(R.id.value1);
        value2 = (TextView)findViewById(R.id.value2);
        value3 = (TextView)findViewById(R.id.value3);
        
        int intSeekBar1 = sharedPref.getInt(keySeekBar1, 0);
        int intSeekBar2 = sharedPref.getInt(keySeekBar2, seekBar2_min);
        int intSeekBar3 = sharedPref.getInt(keySeekBar3, 0);
        
        seekBar1.setProgress(intSeekBar1);
        seekBar2.setProgress(intSeekBar2);
        seekBar3.setProgress(intSeekBar3);
        
        // シークバーの初期値をTextViewに表示
        value1.setText(String.valueOf(intSeekBar1));
        value2.setText(String.valueOf(intSeekBar2));
        value3.setText(String.valueOf(intSeekBar3));
        
    	String average = res.getString(R.string.pref_average_key);
        boolean blAverage = sharedPref.getBoolean(average, true);
      
        if (blAverage) {
        	if (rb1.isChecked() == false) {
            	rb1.setChecked(true);
        	}
        } else {
        	if (rb2.isChecked() == false) {
        		rb2.setChecked(true);
        	}
        }

    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
    	SharedPreferences.Editor editor = sharedPref.edit();
        Resources res = getResources();
        String average = res.getString(R.string.pref_average_key);

        switch (checkedId) {
	    	case R.id.rd_average1:
	        	editor.putBoolean(average, true);
	        	if (seekBar2.isEnabled() == false) {
	        		seekBar2.setEnabled(true);
	        	}
	        	break;
	    	case R.id.rd_average2:
        		editor.putBoolean(average, false);
	        	if (seekBar2.isEnabled() == true) {
		        	seekBar2.setEnabled(false);
	        	}
	        	break; 
	        default:
	        	editor.putBoolean(average, true);
	        	if (seekBar2.isEnabled() == false) {
	        		seekBar2.setEnabled(true);
	        	}
	        	break;
    	}
    	editor.commit();
    }

    public void onProgressChanged(SeekBar seekBar,
            int progress, boolean fromUser) {
        // ツマミをドラッグしたときに呼ばれる
    	SharedPreferences.Editor editor = sharedPref.edit();
        Resources res = getResources();
        
        switch (seekBar.getId()) {
        case R.id.seekBar1:
            String keySeekBar1 = res.getString(R.string.pref_seekbar1_key);
        	editor.putInt(keySeekBar1, progress);
            value1.setText(String.valueOf(progress));
            break;
        case R.id.seekBar2:
            String keySeekBar2 = res.getString(R.string.pref_seekbar2_key);
            int pgValue = progress + seekBar2_min;
        	editor.putInt(keySeekBar2, pgValue);
            value2.setText(String.valueOf(pgValue));
            break;
        case R.id.seekBar3:
            String keySeekBar3 = res.getString(R.string.pref_seekbar3_key);
        	editor.putInt(keySeekBar3, progress);
            value3.setText(String.valueOf(progress));
            break;
        }
    	editor.commit();

    }
	public void onStartTrackingTouch(SeekBar seekBar) {
	  // ツマミに触れたときに呼ばれる
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
	  // ツマミを離したときに呼ばれる
	}

}
