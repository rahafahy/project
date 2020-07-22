package com.example.lastone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private EditText email;
    private EditText pass;
    private Button lo;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);
        lo=(Button)findViewById(R.id.lo);
        firebaseAuth=FirebaseAuth.getInstance();

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user =firebaseAuth.getCurrentUser();
                if(firebaseAuth!=null){
                    Toast.makeText(Login.this,"you are logged in ",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Login.this,MainActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(Login.this,"Please login ",Toast.LENGTH_SHORT).show();
                }
            }
        };

        lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email1=email.getText().toString();
                String password=pass.getText().toString();

                if(email1.isEmpty()||password.isEmpty()){
                    Toast.makeText(Login.this,"fields cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else if(!(email1.isEmpty() && password.isEmpty()))
                {
                    firebaseAuth.signInWithEmailAndPassword(email1,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(!task.isSuccessful()){
                                Toast.makeText(Login.this,"Login failed",Toast.LENGTH_SHORT).show();
                            }else
                            {
                                Intent intHome = new Intent(Login.this,MapsActivity.class);
                                startActivity(intHome);
                            }

                        }
                    });
                }
                else
                {
                    Toast.makeText(Login.this,"حصل خطأ!!!",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

}
