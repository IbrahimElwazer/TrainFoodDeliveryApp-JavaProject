package train.food.delivery.app;

public class Menu {

    String item;
    String price;
    String image;

    public Menu() {
    }

    public Menu(String item, String price,  String image) {
        this.item = item;
        this.price = price;
        this.image = image;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() { return image; }

    public void setImage(String image) {
        this.image = image;
    }
}
