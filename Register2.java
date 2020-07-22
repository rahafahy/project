package com.example.lastone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.santalu.maskedittext.MaskEditText;


public class Register2 extends AppCompatActivity {
    EditText email,pass1,pass2;
    MaskEditText license,phone;

    Button but;
    AwesomeValidation awesomeValidation;
    //db
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        auth=FirebaseAuth.getInstance();
        updateUI();
    }
    private void updateUI() {
        license=(MaskEditText)findViewById(R.id.license);
        email=(EditText)findViewById(R.id.email);
        phone=(MaskEditText)findViewById(R.id.phone);
        pass1=(EditText)findViewById(R.id.pass1);
        pass2=(EditText)findViewById(R.id.pass2);
        but=(Button)findViewById(R.id.but);
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        String Regex = ("[0-9]{10}");

        awesomeValidation.addValidation(Register2.this,R.id.license, Regex, R.string.licensee);
        awesomeValidation.addValidation(Register2.this,R.id.email,android.util.Patterns.EMAIL_ADDRESS, R.string.emailrr);
        awesomeValidation.addValidation(Register2.this,R.id.phone, RegexTemplate.TELEPHONE, R.string.phonen);
        awesomeValidation.addValidation(Register2.this,R.id.pass1,regexPassword,R.string.pass11);
        awesomeValidation.addValidation(Register2.this,R.id.pass2,R.id.pass1,R.string.pass22);


        but.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
        {
          rootNode=FirebaseDatabase.getInstance();
          reference=rootNode.getReference("users");
          //get all the values
            String lic=license.getText().toString();
            String phone_nubmer=phone.getText().toString();
            String email1=email.getText().toString();
            String password=pass1.getText().toString();

           UserHelperClass helper=new UserHelperClass(lic,phone_nubmer,email1,password);
           reference.child(lic).setValue(helper);

           if(lic.isEmpty()||phone_nubmer.isEmpty()||email1.isEmpty()||password.isEmpty()){
               Toast.makeText(Register2.this,"fields cannot be empty",Toast.LENGTH_SHORT).show();
           }
           else if(!(lic.isEmpty() && phone_nubmer.isEmpty() && email1.isEmpty() && password.isEmpty()))
               {
               auth.createUserWithEmailAndPassword(email1,password).addOnCompleteListener(Register2.this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(!task.isSuccessful()){
                           Toast.makeText(Register2.this,"ERROR",Toast.LENGTH_SHORT).show();

                       }else{
                           startActivity(new Intent(Register2.this,Login.class));
                       }

                   }
               });
           }
           else
               {
            Toast.makeText(Register2.this,"حصل خطأ!!!",Toast.LENGTH_SHORT).show();

        }

           if(awesomeValidation.validate())
            {
                Toast.makeText(Register2.this, "Data Received Successfully", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(Register2.this, "Error", Toast.LENGTH_SHORT).show();
        }
    });
        }

    }

