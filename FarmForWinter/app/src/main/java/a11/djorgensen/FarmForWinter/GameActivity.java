package a11.djorgensen.FarmForWinter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final float totalCycles = 15;
    private float growthCycle = 0;
    private float money = 20;
    private float harvestedValue = 0;
    private final List<Harvestable> harvestableArrayList = new ArrayList<>();
    private TextView txtMoney, txtHarvestedValue;
    private ProgressBar pBarWinter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_loop);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.txtMoney = findViewById(R.id.txtMoney);
        this.txtHarvestedValue = findViewById(R.id.txtHarvestedValue);
        this.pBarWinter = findViewById(R.id.pBarWinter);

        // declare and initialize all harvestable elements
        harvestableArrayList.add(new Harvestable(
                "potatoes", 0, 2, 1, 1, 1, 2,
                findViewById(R.id.txtPotatoesAmount), findViewById(R.id.txtPotatoesCost), findViewById(R.id.pBarPotatoes)));
        harvestableArrayList.add(new Harvestable(
                "carrots", 0, 3, 1, 2, 4, 7,
                findViewById(R.id.txtCarrotsAmount), findViewById(R.id.txtCarrotsCost), findViewById(R.id.pBarCarrots)));
        harvestableArrayList.add(new Harvestable(
                "chickens", 0, 4, 4, 4, 2, 12,
                findViewById(R.id.txtChickensAmount), findViewById(R.id.txtChickensCost), findViewById(R.id.pBarChickens)));
        harvestableArrayList.add(new Harvestable(
                "pigs", 0, 5, 5, 3, 0, 40,
                findViewById(R.id.txtPigsAmount), findViewById(R.id.txtPigsCost), findViewById(R.id.pBarPigs)));
        harvestableArrayList.add(new Harvestable(
                "cows", 0, 7, 6, 7, 2, 21,
                findViewById(R.id.txtCowsAmount), findViewById(R.id.txtCowsCost), findViewById(R.id.pBarCows)));

        //set default values
        txtMoney.setText(String.valueOf(money));
        txtHarvestedValue.setText(String.valueOf(harvestedValue));
        pBarWinter.setProgress(Math.round(growthCycle / totalCycles * 100));
    }

    //Grow all produce that have a value
    public void onGrowClicked(View view) throws InterruptedException {
        growthCycle++;

        //check, grow and harvest each item
        for (Harvestable harvestable: harvestableArrayList) {
            if (!(harvestable.getAmount() > 0)) continue;
            harvestable.grow();
            if (harvestable.getHarvestTimeLeft() == 1) harvestedValue += harvestable.harvest();
        }

        //update display elements
        money += harvestedValue;
        txtMoney.setText(String.valueOf(money));
        txtHarvestedValue.setText(String.valueOf(harvestedValue));
        pBarWinter.setProgress(Math.round(growthCycle / totalCycles * 100));

        //End Game Wrap-up
        if (growthCycle == totalCycles) {
            Score score = new Score(String.valueOf(money));
            String user = "User: " + Math.round(Math.random() * 100);
            try {
                String path = URLEncoder.encode(user, String.valueOf(StandardCharsets.UTF_8));
                DatabaseReference ref = database.getReference( "/" + path);
                ref.setValue(score);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(GameActivity.this, EndScreenActivity.class));
        }
    }

    //generic onBuy click function
    public void onBuy(View view) {
        int itemId;
        switch (view.getTag().toString()) { //get right element in array
            case "Potatoes": {
                itemId = 0;
                break;
            }
            case "Carrots": {
                itemId = 1;
                break;
            }
            case "Chickens": {
                itemId = 2;
                break;
            }
            case "Pigs": {
                itemId = 3;
                break;
            }
            case "Cows": {
                itemId = 4;
                break;
            }
            default: return;
        }

        //check if can buy then update
        if (money < harvestableArrayList.get(itemId).getCostNow()) return;
        if (harvestableArrayList.get(itemId).getHarvestTimeLeft() > .25) return;
        money -= harvestableArrayList.get(itemId).getCostNow();
        txtMoney.setText(String.valueOf(money));
        harvestableArrayList.get(itemId).buy();
    }
}