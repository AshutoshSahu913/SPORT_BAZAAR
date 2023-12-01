package LoginActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sportbazaar.HomePage;
import com.example.sportbazaar.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class OtpVerificationActivity extends AppCompatActivity {

    EditText input1, input2, input3, input4, input5, input6;
    String getotpbackend;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        final Button verifybuttonclick = findViewById(R.id.btnVerify);
        input1 = findViewById(R.id.otp_digit1);
        input2 = findViewById(R.id.otp_digit2);
        input3 = findViewById(R.id.otp_digit3);
        input4 = findViewById(R.id.otp_digit4);
        input5 = findViewById(R.id.otp_digit5);
        input6 = findViewById(R.id.otp_digit6);


        EditText editText = findViewById(R.id.numberHint);
        editText.setText(String.format(
                "%s", getIntent().getStringExtra("mobile")
        ));

        getotpbackend = getIntent().getStringExtra("backendotp");

        final ProgressBar progressBarverifyotp = findViewById(R.id.progressbar_verify_otp);

        verifybuttonclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input1.getText().toString().trim().isEmpty() && !input2.getText().toString().trim().isEmpty() && !input3.getText().toString().trim().isEmpty() && !input4.getText().toString().trim().isEmpty() && !input5.getText().toString().trim().isEmpty() && !input6.getText().toString().trim().isEmpty()) {
                    String entercodeotp = input1.getText().toString() +
                            input2.getText().toString()
                            + input3.getText().toString()
                            + input4.getText().toString()
                            + input5.getText().toString()
                            + input6.getText().toString();
                    if (getotpbackend != null) {
                        progressBarverifyotp.setVisibility(View.VISIBLE);
                        verifybuttonclick.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getotpbackend, entercodeotp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(task -> {
                                    progressBarverifyotp.setVisibility(View.GONE);
                                    verifybuttonclick.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(OtpVerificationActivity.this.getApplicationContext(), HomePage.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        OtpVerificationActivity.this.startActivity(intent);
                                    } else {
                                        Toast.makeText(OtpVerificationActivity.this, "Enter the correct OTP ", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(OtpVerificationActivity.this, "Please Check internet connection", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OtpVerificationActivity.this, "please enter all number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView resendlabal = findViewById(R.id.textResentOtp);
        resendlabal.setOnClickListener(v -> PhoneAuthProvider
                .getInstance()
                .verifyPhoneNumber
                        ("+91" + getIntent()
                        .getStringExtra("mobile"),
                60,
                TimeUnit.SECONDS,
                OtpVerificationActivity.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(OtpVerificationActivity.this, "Error please Check Internet connection", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        getotpbackend = newbackendotp;
                        Toast.makeText(OtpVerificationActivity.this, "OTP Sended Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
        ));
        number_otp_move();
    }


    private void number_otp_move() {

        input1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}