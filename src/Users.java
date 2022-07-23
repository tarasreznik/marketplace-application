import java.util.ArrayList;
import java.util.List;

public class Users {
    private static int ID = 0;
    private int id;
    private double amountOfMoney;
    private String firstName;
    private String lastName;

    List<Product> purchasedProducts;


    public Users(String firstName, String lastName,double amountOfMoney) {
        this.id = ID;
        ID++;

        this.firstName = firstName;
        this.lastName = lastName;

        if(amountOfMoney < 0){
            this.amountOfMoney = 0.0;
        }else{
            this.amountOfMoney = amountOfMoney;
        }

        this.purchasedProducts = new ArrayList<>();

    }

    public void buy(Product product) throws NoEnoughMoneyException {
        if(amountOfMoney < product.getPrice()){
            throw new NoEnoughMoneyException("User does`t have enough money for the purchase\n" +
                    "User id " + id);
        }else{
            amountOfMoney -= product.getPrice();
            purchasedProducts.add(product);
            System.out.println("Your perches was successful");
        }
    }


    public int getId() {
        return id;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPurchasedProductsToString() {
        return purchasedProducts.toString();
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", amountOfMoney=" + amountOfMoney +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
