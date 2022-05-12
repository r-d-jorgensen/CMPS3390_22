package a11.djorgensen.FarmForWinter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private EditText textUserNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textUserNameInput = findViewById(R.id.textUserNameInput);
    }

    public void onPlayClicked(View view) {
        String userName = textUserNameInput.getText().toString();
        boolean nameIsValid = userName.matches("^\\w{3,9}[a-zA-Z0-9]");

        if (nameIsValid) {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
        } else {
            Snackbar snackbar = Snackbar.make(textUserNameInput,
                    "Username must be 3-10 characters and alpha numeric only!",
                    Snackbar.LENGTH_LONG);
            snackbar.setDuration(5000);
            snackbar.setAnchorView(textUserNameInput);
            snackbar.show();
        }

    }
}