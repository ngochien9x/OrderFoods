package com.android.nngochien.orderfoods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });

    }

    private boolean isEmpty(EditText input) {
        return input.getText().toString().isEmpty();
    }
}
