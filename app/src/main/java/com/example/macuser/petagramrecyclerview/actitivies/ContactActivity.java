package com.example.macuser.petagramrecyclerview.actitivies;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.macuser.petagramrecyclerview.R;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

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

//                Properties properties = new Properties();
//                properties.put("mail.smtp.host", "smtp.gmail.com");
//                properties.put("mail.smtp.socketFactory.port", "465");
//                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//                properties.put("mail.smtp.auth", "true");
//                properties.put("mail.smtp.port", "465");
//
//                session = Session.getDefaultInstance(properties, new Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        String email = "p&e&t&a&g&r&a&m&t&e&s&t&@&g&m&a&i&l&.&co&m";
//                        String clave = "p&e&t&a&2&2&3&4&%&t&7&$";
//                        return new PasswordAuthentication(email.replace("&", ""), clave.replace("&", ""))
//                    }
//                });
//
//                pdialog = ProgressDialog.show(context, "", "Sending Mail...", true);

            }
        });

        // correo
        // p&e&t&a&g&r&a&m&t&e&s&t&@&g&m&a&i&l&.&co&m
        // p&e&t&a&2&2&3&4&%&t&7&$

    }

}
