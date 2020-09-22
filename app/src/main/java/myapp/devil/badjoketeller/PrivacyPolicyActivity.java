package myapp.devil.badjoketeller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;


public class PrivacyPolicyActivity extends AppCompatActivity {

    WebView web;
    Button goHome;
    public String privacyFile = "BJT_Privacy_Policy.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_privacy_policy);

        goHome = findViewById(R.id.homeButton);
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        web = findViewById(R.id.webView);
        web.loadUrl("file:///android_asset/"+privacyFile);
    }
}
