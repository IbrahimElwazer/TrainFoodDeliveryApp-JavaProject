package train.food.delivery.app;

public class Restaurant {
    private String name;
    private int menuID;

    public Restaurant() {
    }

    public Restaurant(String name, int menuID) {
        this.name = name;
        this.menuID = menuID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }
}