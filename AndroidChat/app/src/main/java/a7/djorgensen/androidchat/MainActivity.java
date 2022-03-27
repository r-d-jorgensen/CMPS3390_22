package a7.djorgensen.androidchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    EditText txtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUserName = findViewById(R.id.txtUserName);
    }

    public void onLoginClicked(View v) {
        String userName = txtUserName.getText().toString();
        boolean nameIsValid = userName.matches("^\\w{2,9}[a-zA-Z0-9]");

        if (nameIsValid) {
            Intent intent = ChatActivity.createIntent(this, userName);
            startActivity(intent);
        } else {
            Snackbar snackbar = Snackbar.make(txtUserName,
                    "Username must be 3-10 characters and alpha numeric only!",
                    Snackbar.LENGTH_LONG);
            snackbar.setDuration(5000);
            snackbar.setAnchorView(txtUserName);
            snackbar.show();
        }
    }
}