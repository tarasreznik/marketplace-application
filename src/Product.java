public class Product {
    private static int ID = 0;
    private int id;
    private String name;
    private double price;

    public Product(String name, double price) {
        this.id = ID;
        this.name = name;
        this.price = price;
        ID++;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
