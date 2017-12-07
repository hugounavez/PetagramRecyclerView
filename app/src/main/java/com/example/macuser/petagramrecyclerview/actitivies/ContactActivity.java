package com.example.macuser.petagramrecyclerview.actitivies;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.macuser.petagramrecyclerview.R;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by macuser on 12/3/17.
 */

public class ContactActivity extends AppCompatActivity {

    TextInputEditText etFullname;
    TextInputEditText etEmail;
    TextInputEditText etMessage;
    Button btnSend;


    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    EditText reciep, sub, msg;
    String rec, subject, textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_form);

        this.context = this;

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


                Properties properties = new Properties();
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.socketFactory.port", "465");
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.port", "587");

                session = Session.getDefaultInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        String email = "petagramtest@gmail.com";
                        String clave = "peta2234%t7$";
                        return new PasswordAuthentication(email, clave);
                    }
                });

                pdialog = ProgressDialog.show(context, "", "Sending Mail...", true);

                RetreiveFeedTask task = new RetreiveFeedTask();
                task.execute();
            }
        });

        // correo
        // p&e&t&a&g&r&a&m&t&e&s&t&@&g&m&a&i&l&.&co&m
        // p&e&t&a&2&2&3&4&%&t&7&$

    }

    class RetreiveFeedTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params){

            try{
                String email = "petagramtest@gmail.com";
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(etEmail.getText().toString()));
                message.setSubject("prueba");
                message.setContent(etMessage.getText().toString(), "text/html; chartset=utf-8");
                Transport.send(message);

            } catch (MessagingException e){
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String result){
            pdialog.dismiss();
            etFullname.setText("");
            etEmail.setText("");
            etMessage.setText("");
            Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
        }
    }




    public void sendTemplateEmail() {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", 25);
        props.put("mail.smtp.socketFactory.port", 25);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");
        Session mailSession = null;

        mailSession = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("petagramtest", "peta2234%t7$");
                    }
                });


        try {

            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);

            message.setSubject("Sample Subject");
            message.setFrom(new InternetAddress("petagramtest@gmail.com"));
            String []to = new String[]{"petagramtest@gmail.com"};
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[0]));
            String body = "Sample text";
            message.setContent(body,"text/html");
            transport.connect();

            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (Exception exception) {

        }
    }
}
