package a11.djorgensen.FarmForWinter;

import android.widget.ProgressBar;
import android.widget.TextView;

public class Harvestable {
    private final String name;
    private float amount, costNow;
    private float timeGrown = 0;
    private final float harvestTime, costIncrease, costStart, carryOver, output;
    private final TextView txtAmount, txtCost;
    private final ProgressBar pBarPercent;

    public Harvestable(String name, float amount, float harvestTime, float costStart, float costIncrease, float carryOver, float output,
                       TextView txtAmount, TextView txtCost, ProgressBar pBarPercent) {
        this.name = name;
        this.amount = amount;
        this.harvestTime = harvestTime;
        this.costNow = costStart;
        this.costStart = costStart;
        this.costIncrease = costIncrease;
        this.carryOver = carryOver;
        this.output = output;
        this.txtAmount = txtAmount;
        this.txtCost = txtCost;
        this.pBarPercent = pBarPercent;
        updateElements();
    }

    public void buy() {
        amount++;
        costNow = Math.round(costNow + costIncrease);
        updateElements();
    }

    public void grow() {
        timeGrown++;
        costNow = costStart;
        updateElements();
    }

    public float harvest() {
        timeGrown = 0;
        float production = output * amount;
        if (carryOver == 0) amount = 0;
        else amount = Math.round(amount / carryOver);
        txtAmount.setText(String.valueOf(amount));
        return production;
    }

    public float getHarvestTimeLeft() {
        return timeGrown / harvestTime;
    }

    private void updateElements() {
        txtAmount.setText(String.valueOf(amount));
        txtCost.setText(String.valueOf(costNow));
        pBarPercent.setProgress(Math.round(getHarvestTimeLeft() * 100));
    }

    public String getName() {
        return name;
    }

    public float getCostNow() {
        return costNow;
    }

    public float getAmount() {
        return amount;
    }
}
