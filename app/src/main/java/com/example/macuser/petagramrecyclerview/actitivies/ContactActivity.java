package com.example.macuser.petagramrecyclerview.actitivies;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.macuser.petagramrecyclerview.R;

import java.util.Properties;

/**
 * Created by macuser on 12/3/17.
 */

public class ContactActivity extends AppCompatActivity {

    TextInputEditText etFullname;
    TextInputEditText etEmail;
    TextInputEditText etMessage;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_form);

        this.initializeUIElements();
    }


    void initializeUIElements(){
        this.etFullname = (TextInputEditText) findViewById(R.id.et_fullname);
        this.etEmail   = (TextInputEditText) findViewById(R.id.et_email);
        this.etMessage = (TextInputEditText) findViewById(R.id.et_message);
        this.btnSend = (Button) findViewById(R.id.btn_next);

        this.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Properties properties = new Properties();
                //properties.put()

            }
        });

    }

}
