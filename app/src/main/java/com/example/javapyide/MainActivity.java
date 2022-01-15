package com.example.javapyide;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {

    TextView output;
    EditText CodeArea;
    Button Run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.output);
        CodeArea = findViewById(R.id.codearea);
        Run = findViewById(R.id.run);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }


        Run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Python py = Python.getInstance();

                PyObject pyobj = py.getModule("pyscript");
                PyObject obj = pyobj.callAttr("main", CodeArea.getText().toString());
                output.setText(obj.toString());
            }
        });


    }
}