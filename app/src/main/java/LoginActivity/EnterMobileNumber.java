package LoginActivity;

import static com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken;
import static com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sportbazaar.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class EnterMobileNumber extends AppCompatActivity {

    EditText enternumber;
    Button getotpbutton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_moblie_number);
        enternumber = findViewById(R.id.numberHint);
        getotpbutton = findViewById(R.id.textResentOtp);

        ProgressBar progressBar = findViewById(R.id.progressbar_sending_otp);
        getotpbutton.setOnClickListener(v -> {
            if (!enternumber.getText().toString().trim().isEmpty()) {
                if ((enternumber.getText().toString().trim()).length() == 10) {
                    progressBar.setVisibility(View.VISIBLE);
                    getotpbutton.setVisibility(View.INVISIBLE);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + enternumber.getText().toString(),
                            30,
                            TimeUnit.SECONDS,
                            EnterMobileNumber.this,
                            new OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(View.GONE);
                                    getotpbutton.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBar.setVisibility(View.GONE);
                                    getotpbutton.setVisibility(View.VISIBLE);
                                    Toast.makeText(EnterMobileNumber.this, "Error please Check Internet connection", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String backendotp, @NonNull ForceResendingToken forceResendingToken) {

                                    progressBar.setVisibility(View.GONE);
                                    getotpbutton.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent(EnterMobileNumber.this.getApplicationContext(), OtpVerificationActivity.class);
                                    intent.putExtra("mobile", enternumber.getText().toString());
                                    intent.putExtra("backendotp", backendotp);
                                    startActivity(intent);
                                }

                            }
                    );
                } else {
                    Toast.makeText(EnterMobileNumber.this, "Please enter correct number ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(EnterMobileNumber.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
            }
        });
    }
}