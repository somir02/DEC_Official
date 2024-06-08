package com.example.decofficial;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.decofficial.databinding.ActivityRegisterBinding;
import com.example.decofficial.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

//    private ActivityRegisterBinding binding;
    private TextInputEditText registerFullname, registerEmail, registerContactnumber, registerPass, registerConfirmPass;
    private ProgressBar progressBar;
    private AppCompatButton register_button;
    private TextView textView;
    private static final String TAG = "Register Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");
        
        Toast.makeText(RegisterActivity.this, "You can register Now", Toast.LENGTH_SHORT).show();
        
        registerFullname = findViewById(R.id.fullname);
        registerEmail = findViewById(R.id.email);
        registerContactnumber = findViewById(R.id.contact_number);
        registerPass = findViewById(R.id.register_pass);
        registerConfirmPass = findViewById(R.id.register_c_pass);
        progressBar = findViewById(R.id.loading);

        register_button = findViewById(R.id.registerBtn);
        textView = findViewById(R.id.textView60);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textFullName = registerFullname.getText().toString();
                String textEmail = registerEmail.getText().toString();
                String textContact = registerContactnumber.getText().toString();
                String textPassword = registerPass.getText().toString();
                String textConfirmPass = registerConfirmPass.getText().toString();

                if (TextUtils.isEmpty(textFullName)){
                    Toast.makeText(RegisterActivity.this, "Please enter your full name", Toast.LENGTH_SHORT).show();
                    registerFullname.setError("Full Name is Required");
                    registerFullname.requestFocus();
                } else if (TextUtils.isEmpty(textEmail)){
                    Toast.makeText(RegisterActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    registerEmail.setError("Email is Required");
                    registerEmail.requestFocus();
                } else if (TextUtils.isEmpty(textContact)){
                    Toast.makeText(RegisterActivity.this, "Please enter your Contact Number", Toast.LENGTH_SHORT).show();
                    registerContactnumber.setError("Contact Number is Required");
                    registerConfirmPass.requestFocus();
                } else if (TextUtils.isEmpty(textPassword)){
                    Toast.makeText(RegisterActivity.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
                    registerPass.setError("Password is Required");
                    registerPass.requestFocus();
                } else if (textPassword.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Password should be atleast 6 digits", Toast.LENGTH_SHORT).show();
                    registerPass.setError("Password too weak");
                    registerPass.requestFocus();
                }
                else if (TextUtils.isEmpty(textConfirmPass)){
                    Toast.makeText(RegisterActivity.this, "Please Confirm your Password", Toast.LENGTH_SHORT).show();
                    registerConfirmPass.setError("Password confirmation is required");
                    registerConfirmPass.requestFocus();
                } else if (!textPassword.equals(textConfirmPass)){
                    Toast.makeText(RegisterActivity.this, "Please enter the same password", Toast.LENGTH_SHORT).show();
                    registerConfirmPass.setError("Password confirmation is required");
                    registerConfirmPass.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    registerUser(textFullName, textEmail, textContact, textPassword, textConfirmPass);
                }
            }
        });

        


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

//        final AppCompatButton registerButton = binding.register;
//        final TextView textView = binding.textView60;

//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser(String textFullName, String textEmail, String textContact, String textPassword, String textConfirmPass) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(textEmail, textPassword).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(textFullName).build();
                    firebaseUser.updateProfile(profileChangeRequest);


                    ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(textFullName, textEmail, textContact);

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Registered Users");
                    reference.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "User Registered Succesfully. Please verify your email", Toast.LENGTH_SHORT).show();

                                firebaseUser.sendEmailVerification();

                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(RegisterActivity.this, "User Registration failed. Please try again", Toast.LENGTH_SHORT).show();

                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                } else {
                    try {
                        throw Objects.requireNonNull(task.getException());
                    } catch (FirebaseAuthWeakPasswordException e) {
                        registerPass.setError("Your password is too weak. Kindly use a mix of alphabets, numbers and special characters");
                        registerPass.requestFocus();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        registerPass.setError("Your email is invalid or already in use. Kindly re-enter.");
                        registerPass.requestFocus();
                    } catch (FirebaseAuthUserCollisionException e) {
                        registerPass.setError("User is already registered with this email. Use another email.");
                        registerPass.requestFocus();
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}