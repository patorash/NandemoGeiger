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
                .setIndicator(getString(R.string.measurement),
                        res.getDrawable(android.R.drawable.ic_menu_info_details))
                .setContent(intent);
        tabHost.addTab(spec);
        
        spec = tabHost.newTabSpec("tab2")
                .setIndicator(getString(R.string.preferences),
                        res.getDrawable(android.R.drawable.ic_menu_preferences))
                .setContent(intent);
        tabHost.addTab(spec);
            
        spec = tabHost.newTabSpec("tab3")
                .setIndicator(getString(R.string.analysis),
                        res.getDrawable(android.R.drawable.ic_menu_info_details))
                .setContent(intent);
        tabHost.addTab(spec);
        
        spec = tabHost.newTabSpec("tab4")
                .setIndicator(getString(R.string.help),
                        res.getDrawable(android.R.drawable.ic_menu_help))
                .setContent(intent);
        tabHost.addTab(spec);
    }
}