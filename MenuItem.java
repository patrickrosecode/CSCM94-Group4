public class MenuItem{
    //Constructor
    //Logistics
    String NAME; //The name of the item
    int PRICE; //The price in pence

    //Dietary preference
    boolean CONTAINS_MEAT; //Whether the product contains meat- for vegetarians
    boolean ANIMAL_PRODUCT; //Whether the product is any kind of animal product- for vegans
    boolean IS_HALAL; //Whether the product can be consumed by practicing Muslims

    public MenuItem(String NAME, int PRICE, boolean CONTAINS_MEAT, boolean ANIMAL_PRODUCT, boolean IS_HALAL){
        this.NAME = NAME;
        this.PRICE = PRICE;
        this.CONTAINS_MEAT = CONTAINS_MEAT;
        this.ANIMAL_PRODUCT = ANIMAL_PRODUCT;
        this.IS_HALAL = IS_HALAL;
    }

    //Setters
    public void setName(String NEW_NAME) {
        this.NAME = NEW_NAME;
    }

    public void setPrice(int NEW_PRICE){
        this.PRICE = NEW_PRICE;
    }

    public void setContaintsMeat(boolean NEW_CONTAINS_MEAT){
        this.CONTAINS_MEAT = NEW_CONTAINS_MEAT;
    }

    public void setAnimalProduct(boolean NEW_ANIMAL_PRODUCT){
        this.ANIMAL_PRODUCT = NEW_ANIMAL_PRODUCT;
    }

    public void setIsHalal(boolean NEW_IS_HALAL){
        this.IS_HALAL = NEW_IS_HALAL;
    }

    //Getters
    public String getName(){
        return this.NAME;
    }

    public int getPrice(){
        return this.PRICE;
    }

    public boolean getContainsMeat(){
        return this.CONTAINS_MEAT;
    }

    public boolean getAnimalProduct(){
        return this.ANIMAL_PRODUCT;
    }

    public boolean getIsHalal(){
        return this.IS_HALAL;
    }

}