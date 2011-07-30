package ninja.ehime.nangei;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class NandemoGeigerActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources res = getResources();
        
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        
        Intent intent = new Intent(this, Setting.class);
        
        spec = tabHost.newTabSpec("tab1")
            .setIndicator(getString(R.string.hello), res.getDrawable(R.drawable.icon))
            .setContent(intent);
        tabHost.addTab(spec);
    }
}