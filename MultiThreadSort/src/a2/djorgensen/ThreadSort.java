package a2.djorgensen;

import a1.djorgensen.Item;

import java.util.Random;

public class ThreadSort extends Thread {
    private Item[] tItems;

    public ThreadSort(Item[] items, int lowerBounds, int upperBounds) {
        this.tItems = new Item[upperBounds - lowerBounds];

        System.arraycopy(items, lowerBounds, this.tItems, 0, upperBounds - lowerBounds);
    }

    @Override
    public void run() {
        System.out.println("Thread Start");
        int n = this.tItems.length;
        Item tmp;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (this.tItems[j-1].getPrice() > this.tItems[j].getPrice()) {
                    tmp = this.tItems[j-1];
                    this.tItems[j-1] = this.tItems[j];
                    this.tItems[j] = tmp;
                }
            }
        }
        System.out.println("Thread Complete");
    }

    public Item[] gettItems() { return tItems; }
}
