package a1.djorgensen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random ran = new Random();
        List<Item> items = new ArrayList<>();
        FoodItems[] foodItems = FoodItems.values();
        Tools[] tools = Tools.values();
        ToolUses[] toolUses = ToolUses.values();
        Armors[] armors = Armors.values();
        Weapons[] weapons = Weapons.values();

        System.out.print("How many items do you want?");
        int itemCnt = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < itemCnt; i++) {
            int type = ran.nextInt(4);
            switch (type) {
                case 0 -> {
                    int foodIndex = ran.nextInt(foodItems.length);
                    String foodName = foodItems[foodIndex].toString();
                    float foodPrice = ran.nextFloat(10);
                    int foodQty = ran.nextInt(30);
                    int foodUses = ran.nextInt(6);
                    float healthGain = ran.nextFloat(20);
                    Food tmpFood = new Food(foodName, foodPrice, foodQty, foodUses, healthGain);
                    items.add(tmpFood);
                }
                case 1 -> {
                    int toolIndex = ran.nextInt(tools.length);
                    String toolName = tools[toolIndex].toString();
                    float toolPrice = ran.nextFloat(200);
                    int toolQty = ran.nextInt(15);
                    String use = toolUses[toolIndex].toString();
                    Tool tmpTool = new Tool(toolName, toolPrice, toolQty, use);
                    items.add(tmpTool);
                }
                case 2 -> {
                    int armorIndex = ran.nextInt(armors.length);
                    String armorName = armors[armorIndex].toString();
                    float armorPrice = ran.nextFloat(2000);
                    int armorQty = ran.nextInt(3);
                    float defence = ran.nextFloat(1000);
                    Armor tmpArmor = new Armor(armorName, armorPrice, armorQty, defence);
                    items.add(tmpArmor);
                }
                case 3 -> {
                    int weaponIndex = ran.nextInt(weapons.length);
                    String weaponName = weapons[weaponIndex].toString();
                    float weaponPrice = ran.nextFloat(1200);
                    int weaponQty = ran.nextInt(5);
                    float damage = ran.nextFloat(1000);
                    Armor tmpWeapon = new Armor(weaponName, weaponPrice, weaponQty, damage);
                    items.add(tmpWeapon);
                }
            }
        }

        for(Item i : items) {
            System.out.println(i);
        }
    }
}
