package a1.djorgensen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Random ran = new Random();
    private static final FoodItems[] foodItems = FoodItems.values();
    private static final Tools[] tools = Tools.values();
    private static final ToolUses[] toolUses = ToolUses.values();
    private static final Armors[] armors = Armors.values();
    private static final Weapons[] weapons = Weapons.values();

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        System.out.print("How many items do you want?");
        int itemCnt = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < itemCnt; i++) {
            int type = ran.nextInt(4);
            switch (type) {
                case 0 -> items.add(genFood());
                case 1 -> items.add(genTool());
                case 2 -> items.add(genArmor());
                case 3 -> items.add(genWeapon());
            }
        }

        for(Item i : items) {
            System.out.println(i);
        }
    }

    public static Food genFood() {
        int foodIndex = ran.nextInt(foodItems.length);
        String foodName = foodItems[foodIndex].toString();
        float foodPrice = ran.nextFloat(10);
        int foodQty = ran.nextInt(30);
        int foodUses = ran.nextInt(6);
        float healthGain = ran.nextFloat(20);
        return new Food(foodName, foodPrice, foodQty, foodUses, healthGain);
    }

    public static Tool genTool() {
        int toolIndex = ran.nextInt(tools.length);
        String toolName = tools[toolIndex].toString();
        float toolPrice = ran.nextFloat(200);
        int toolQty = ran.nextInt(15);
        String use = toolUses[toolIndex].toString();
        return new Tool(toolName, toolPrice, toolQty, use);
    }

    public static Armor genArmor() {
        int armorIndex = ran.nextInt(armors.length);
        String armorName = armors[armorIndex].toString();
        float armorPrice = ran.nextFloat(2000);
        int armorQty = ran.nextInt(3);
        float defence = ran.nextFloat(1000);
        return new Armor(armorName, armorPrice, armorQty, defence);
    }

    public static Weapon genWeapon() {
        int weaponIndex = ran.nextInt(weapons.length);
        String weaponName = weapons[weaponIndex].toString();
        float weaponPrice = ran.nextFloat(1200);
        int weaponQty = ran.nextInt(5);
        float damage = ran.nextFloat(1000);
        return new Weapon(weaponName, weaponPrice, weaponQty, damage);
    }
}
