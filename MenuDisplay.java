import java.util.ArrayList;

public class MenuDisplay{
    public static void main(String[] args){
        ArrayList<MenuItem> MenuItems = new ArrayList<MenuItem>();
        ArrayList<MenuItem> Mains = new ArrayList<MenuItem>();
        ArrayList<MenuItem> Sides = new ArrayList<MenuItem>();
        ArrayList<MenuItem> Drinks = new ArrayList<MenuItem>();

        //Adding menu items
        //Mains
        MenuItem VEGAN_BURGER = new MenuItem("Vegan Burger", 399, false, false, true);
        MenuItem VEGGIE_BURGER = new MenuItem("Veggie Burger", 349, false, true, true);
        MenuItem BEEF_BURGER = new MenuItem("Beef Burger", 299, true, true, false);
        MenuItem PORK_SAUSAGE = new MenuItem("Pork Sausage", 199, true, true, false);
        Mains.add(VEGAN_BURGER);
        Mains.add(VEGGIE_BURGER);
        Mains.add(BEEF_BURGER);
        Mains.add(PORK_SAUSAGE);
        for(MenuItem ITEM: Mains){
            MenuItems.add(ITEM);
        }

        //Sides
        MenuItem CHIPS = new MenuItem("Chips", 199, false, false, true);
        MenuItem ONION_RINGS = new MenuItem("Onion Rings", 149, false, false, true);
        MenuItem CHICKEN_THIGH = new MenuItem ("Chicken Thigh", 199, true, true, false);
        Sides.add(CHIPS);
        Sides.add(ONION_RINGS);
        Sides.add(CHICKEN_THIGH);
        for(MenuItem ITEM: Sides){
            MenuItems.add(ITEM);
        }

        //Drinks
        MenuItem FIZZY_POP = new MenuItem("Fizzy Pop", 129, false, false, true);
        MenuItem MILKSHAKE = new MenuItem("Milkshake", 149, false, true, true);
        MenuItem APPLE_JUICE = new MenuItem("Apple Juice", 115, false, false, true);
        Drinks.add(FIZZY_POP);
        Drinks.add(MILKSHAKE);
        Drinks.add(APPLE_JUICE);
        for(MenuItem ITEM: Drinks){
            MenuItems.add(ITEM);
        }

        //Displaying the whole menu
        double PRICE;//Used to print prices
        for(MenuItem ITEM : MenuItems){
            //Printing name
            System.out.print(ITEM.getName());

            //Printing price
            PRICE = ITEM.getPrice();
            System.out.println(": £" + PRICE/100);  
        }
        System.out.println("");
            
        //Doing a dietary requirement check
        System.out.println("Vegan options:");
        for(MenuItem ITEM : MenuItems){
            if(ITEM.getAnimalProduct() == false){
            //Printing name
            System.out.print(ITEM.getName());

            //Printing price
            PRICE = ITEM.getPrice();
            System.out.println(": £" + PRICE/100);  
            }
        }
    }
}