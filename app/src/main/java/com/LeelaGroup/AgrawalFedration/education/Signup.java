package com.LeelaGroup.AgrawalFedration.education;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.R;


public class Signup extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    EditText email;
    EditText username;
    EditText password;
    Button signup,btn_signup;



    TextInputLayout layout_name,layout_email,layout_username,layout_password;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        layout_name=(TextInputLayout) findViewById(R.id.layout_name);
        layout_email=(TextInputLayout) findViewById(R.id.layout_email);
        layout_username=(TextInputLayout) findViewById(R.id.layout_uesrname);
        layout_password=(TextInputLayout) findViewById(R.id.layout_password);

        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        username=(EditText)findViewById(R.id.uesrname);
        password=(EditText)findViewById(R.id.password);
        signup=(Button)findViewById(R.id.signup);
        //btn_signup=(Button)findViewById(R.id.btn_signup);

        layout=(LinearLayout)findViewById(R.id.parent);
        name.addTextChangedListener(new MyTextWatcher(name));
        email.addTextChangedListener(new MyTextWatcher(email));
        password.addTextChangedListener(new MyTextWatcher(password));



        signup.setOnClickListener(this);
       /* btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,Login_Medical.class));
            }
        });*/
    }


    @Override
    public void onClick(View v) {

        submitForm();

    }

    private boolean submitForm() {
        if(!validateName()){
            return false;
        }

        if(!validateEmail()){
            return false;
        }



        if(!validatePassword()){
            return false;
        }

       /* AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
        builder.setMessage("Thank You!")
                .create();
        builder.show();*/

        Toast.makeText(this, "Thank You!", Toast.LENGTH_SHORT).show();
        return true;

    }
    
    private boolean validateName(){
        
        if (name.getText().toString().trim().isEmpty())
        {
            name.setError(getString(R.string.err_msg_name));
            
            requestFocus(name);
            
            return false;
        }else{
            layout_name.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail(){
        String email1 = email.getText().toString().trim();
        
        if (email1.isEmpty() || !isValidEmail(email1)){

            layout_email.setError(getString(R.string.err_msg_email));

            requestFocus(email);
            return false;
        }else{
            layout_email.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isValidEmail(String email1) {

        return !TextUtils.isEmpty(email1) && android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches();
    }

    private boolean validatePassword(){

        if (password.getText().toString().trim().isEmpty())
        {
            password.setError(getString(R.string.err_msg_password));

            requestFocus(password);

            return false;
        }else{
            layout_password.setErrorEnabled(false);
        }
        return true;

    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;
        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()){

                case R.id.name: validateName();
                    break;

                case R.id.email: validateEmail();
                    break;

                case R.id.password: validatePassword();
                    break;
            }

        }
    }

}
