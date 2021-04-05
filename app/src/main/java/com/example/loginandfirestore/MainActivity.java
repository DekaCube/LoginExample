package com.example.loginandfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText pw;
    private EditText un;
    private Button login;
    private Button signup;
    private TextView error;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Attach views
        signup = findViewById(R.id.signup);
        pw = findViewById(R.id.pw);
        un = findViewById(R.id.un);
        login = findViewById(R.id.login);
        error = findViewById(R.id.error);

        //Init firebase
        mAuth = FirebaseAuth.getInstance();


    }

    public void onLoginClick(View v){
        String email = un.getText().toString();
        String password = pw.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            mUser = mAuth.getCurrentUser();
                            error.setText("signInWithEmail:success " +  mUser.getEmail().toString());

                        } else {
                            // If sign in fails, display a message to the user.
                            error.setText("signInWithEmail:failure" +  task.getException().toString());

                        }
                    }
                });


    }


    public void onSignupClick(View v){
        String email = un.getText().toString();
        String password = pw.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            error.setText("createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            error.setText("createUserWithEmail:failure" + task.getException().toString());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }
}