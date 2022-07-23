import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Users> users = new ArrayList<>();
    static List<Product> products = new ArrayList<>();

    public static void main(String[] args) {

        users.add(new Users("Elon", "Mask", 100.2));
        users.add(new Users("Steve", "jobs", 260.2));
        users.add(new Users("Taras", "Shevchenko", 500.2));


        products.add(new Product("soap", 5.20));
        products.add(new Product("pants", 10.80));
        products.add(new Product("bra", 7.91));

        start();

    }

    private static void start()  {
        boolean quit = false;
        printMenu();

        while(!quit) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 0 -> printMenu();
                case 1 -> displayUsersList();
                case 2 -> displayProductList();
                case 3 -> {
                    System.out.println("Enter id of the user who wants to buy a product");
                    int userId = scanner.nextInt();
                    System.out.println("Enter id of the product which user with " + userId + " id want`s to buy");
                    int productId = scanner.nextInt();
                    buyProduct(userId, productId);
                }
                case 4-> {
                    System.out.println("Enter User id whose purchased products you want to see");
                    int userId = scanner.nextInt();
                    System.out.println(showUserProductsById(userId));

                }
                case 5 -> {
                    System.out.println("Enter Product id and see which users bought with product");
                    int productId = scanner.nextInt();
                    System.out.println(showBoughtProducts(productId));
                }
                case 6 -> {
                     double amountOfMoney;
                     String firstName;
                     String lastName;
                    System.out.println("Enter User`s name");
                    firstName = scanner.next();
                    System.out.println("Enter User`s surname");
                    lastName = scanner.next();
                    System.out.println("Enter User`s amount of money");
                    amountOfMoney = scanner.nextDouble();
                    addNewUser(amountOfMoney, firstName, lastName);
                }

                case 7 -> {
                     String name;
                     double price;
                    System.out.println("Enter Product name");
                    name = scanner.next();
                    System.out.println("Enter Product name");
                    price = scanner.nextDouble();
                    addNewProduct(name,price);
                }
                case 8 ->{
                    System.out.println("Enter user`s id you want to delete");
                    int id = scanner.nextInt();
                    deleteUser(id);
                }
                case 9 -> {
                    System.out.println("Enter product`s id you want to delete");
                    int id = scanner.nextInt();
                    deleteProduct(id);
                }
                case 10 -> quit = true;
            }
        }

    }



    private static void printMenu() {
        System.out.println("""
                Marketplace
                0 - Print menu
                1 - Display list of all users
                2 - Display list of all products
                3 - To buy a product
                4 - Display list of user products by user id
                5 - Display list of users that bought product by product id
                6 - Add a new User
                7 - Add a new Product
                8 - Delete a User by id
                9 - Delete a Product by id
                10 - To quit
                """);
    }
    public static void buyProduct(int userId, int productId)  {

        Users user = null;
        Product product = null;

        for(Users u : users){
            if(u.getId() == userId)
                user = u;
        }

        for(Product p : products){
            if(p.getId() == productId)
                product = p;
        }

           try{
               assert product != null;
               assert user != null;
               user.buy(product);
           }catch (NoEnoughMoneyException ex){
               System.err.print(ex);
           }


    }
    private static void deleteProduct(int productId){
        Product product = null;
        for(Product p : products){
            if(p.getId() == productId)
                product = p;
        }
        if(product == null){
            System.out.println("There is not user with id " + productId + " in the list");
        }else{
            for(int i = 0; i < products.size(); i++){
                users.get(i).purchasedProducts.remove(product);
            }
            products.remove(product);
            System.out.println("User was successfully removed");
        }



    }
    private static void deleteUser(int userId){
        Users user = null;
        for(Users u : users){
            if(u.getId() == userId) {
                user = u;
            }
        }
        if(user == null){
            System.out.println("There is not user with id " + userId + " in the list");
        }else{
            users.remove(user);
            System.out.println("User was successfully removed");
        }

    }



    private static void addNewUser(double amountOfMoney,String firstName,String lastName){
        if(firstName == null || lastName == null){
            System.out.println("User cannot be added because of invalid field");
        }else{
            users.add(new Users(firstName, lastName, amountOfMoney));
            System.out.println("User was successfully added");
        }
    }

    private static void addNewProduct(String name, double price){
        if(name == null || price < 0){
            System.out.println("Product cannot be added because of invalid field");
        }else{
            products.add(new Product(name, price));
            System.out.println("Product was successfully added");
        }
    }

    private static String showBoughtProducts(int productId){
        List<Users> list = new ArrayList<>();
        Product product = null;
        for(Product p : products){
            if(p.getId() == productId)
                product = p;
        }

        for (Users user : users) {
            if (user.purchasedProducts.contains(product))
                list.add(user);
        }
        return list.toString();
    }

    private static String showUserProductsById(int userId){
        Users user = null;
        for(Users u : users){
            if(u.getId() == userId)
                user = u;
        }
        assert user != null;
        return user.getPurchasedProductsToString();
    }

    private static void displayUsersList(){
        for(Users u : users){
            System.out.println(u.toString());
        }
    }

    private static void displayProductList(){
        for(Product p : products){
            System.out.println(p.toString());
        }
    }
}