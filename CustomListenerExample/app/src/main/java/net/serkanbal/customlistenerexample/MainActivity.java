package net.serkanbal.customlistenerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textview = (TextView) findViewById(R.id.text);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChildObject object = new ChildObject(MainActivity.this);

                object.setCustomObjectListener(new ChildObject.MyCustomObjectListener() {
                    @Override
                    public void onObjectReady(String title) {
                        textview.setText(title);
                    }
                });
            }
        });
    }
}
