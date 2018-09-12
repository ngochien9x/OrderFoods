package com.android.nngochien.orderfoods;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.nngochien.orderfoods.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpController extends AppCompatActivity {

    EditText edtPhone, edtName, edtPassword, edtConfirmPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        edtConfirmPassword = (EditText)findViewById(R.id.edtConfirmPassword);
        edtName = (EditText)findViewById(R.id.edtName);
        edtPhone = (EditText)findViewById(R.id.edtPhone);
        edtPassword = (EditText)findViewById(R.id.edtPassword);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableUser = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(SignUpController.this);
                mDialog.setMessage("Please wait...");


                if(validateInput()) {
                    tableUser.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //Check user had already exist
                            if(dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                                Toast.makeText(SignUpController.this, "Phone Number had already exist!", Toast.LENGTH_SHORT).show();
                            } else {
                                mDialog.show();
                                User newUser = new User(edtName.getText().toString(), edtPassword.getText().toString());
                                tableUser.child(edtPhone.getText().toString()).setValue(newUser);
                                mDialog.dismiss();
                                Toast.makeText(SignUpController.this, "Register successfully, you can sign in now...", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

    }

    private boolean isEmpty(EditText input) {
        return input.getText().toString().isEmpty();
    }

    private boolean validateInput() {
        boolean valid = true;

        if(isEmpty(edtConfirmPassword) || isEmpty(edtName) || isEmpty(edtPassword) || isEmpty(edtPhone)) {
            valid = false;
            //Validate empty fields
            if(isEmpty(edtName)) {
                edtName.setError("This field is required!");
            }
            if(isEmpty(edtPassword)) {
                edtPassword.setError("This field is required!");
            }
            if(isEmpty(edtConfirmPassword)) {
                edtConfirmPassword.setError("This field is required!");
            }
            if(isEmpty(edtPhone)) {
                edtPhone.setError("This field is required!");
            }
        }

        if(valid) {
            if(!edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
                valid = false;
                edtConfirmPassword.setError("The password must be the same!");
            }
        }

        return valid;
    }

}
