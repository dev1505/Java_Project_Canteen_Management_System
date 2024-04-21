import java.util.*;

class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class CartItem {
    private MenuItem item;
    private int quantity;

    public CartItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

public class Canteen_Management_System {
    private ArrayList<MenuItem> menu = new ArrayList<>();
    private ArrayList<CartItem> cart = new ArrayList<>();

    public Canteen_Management_System() {
        initializeMenu();
    }

    private void initializeMenu() {
        // Initialize menu items
        menu.add(new MenuItem("Sandwich", 350));
        menu.add(new MenuItem("Burger", 250));
        menu.add(new MenuItem("Pizza", 550));
        menu.add(new MenuItem("Noodles", 300));
        menu.add(new MenuItem("French Fries", 200));
        menu.add(new MenuItem("Soft Drink", 150));
        menu.add(new MenuItem("Ice Cream", 100));
        menu.add(new MenuItem("Coffee", 50));
        menu.add(new MenuItem("Tea", 30));
        menu.add(new MenuItem("Milkshake", 150));
        menu.add(new MenuItem("Salad", 200));
        menu.add(new MenuItem("Soup", 100));
        menu.add(new MenuItem("Cake", 250));
        menu.add(new MenuItem("Cookies", 50));
        menu.add(new MenuItem("Donut", 50));
        menu.add(new MenuItem("Chocolate", 50));
        menu.add(new MenuItem("Muffin", 50));
    }

    public void addToCart(MenuItem menuItem, int quantity) {
        // Check if the item is already in the cart
        for (CartItem cartItem : cart) {
            if (cartItem.getItem().getName().equals(menuItem.getName())) {
                // Update the quantity of the existing item
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;
            }
        }
        // If the item is not in the cart, add it
        cart.add(new CartItem(menuItem, quantity));
    }

    public void removeItem(String itemName) {
        cart.removeIf(cartItem -> cartItem.getItem().getName().equals(itemName));
    }

    public void editItem(String itemName, int newQuantity) {
        for (CartItem cartItem : cart) {
            if (cartItem.getItem().getName().equals(itemName)) {
                cartItem.setQuantity(newQuantity);
                return;
            }
        }
    }

    public double showTotalBill() {
        double total = 0.0;
        for (CartItem item : cart) {
            total += item.getItem().getPrice() * item.getQuantity();
        }
        return total;
    }

    public void printMenu() {
        System.out.println("Menu:");
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - " + item.getPrice());
        }
    }

    public void printCart() {
        System.out.println("Cart:");
        for (CartItem item : cart) {
            System.out.println(item.getItem().getName() + " x " + item.getQuantity());
        }
    }

    public static void main(String[] args) {
        Canteen_Management_System cms = new Canteen_Management_System();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Canteen Management System");
        int choice;
        boolean exit = true;
        while (exit) {
            System.out.println("Enter 1 to add to cart");
            System.out.println("Enter 2 to remove from cart");
            System.out.println("Enter 3 to edit cart");
            System.out.println("Enter 4 to checkout");
            System.out.println("Enter 5 to exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    cms.printMenu();
                    System.out.println("Enter the item number to add to cart");
                    int itemNumber = scanner.nextInt();
                    System.out.println("Enter the quantity");
                    int quantity = scanner.nextInt();
                    cms.addToCart(cms.menu.get(itemNumber - 1), quantity);
                    break;
                case 2:
                    System.out.println("Enter the item name to remove from cart");
                    String itemName = scanner.next();
                    cms.removeItem(itemName);
                    break;
                case 3:
                    System.out.println("Enter the item name to edit");
                    String editItemName = scanner.next();
                    System.out.println("Enter the new quantity");
                    int newQuantity = scanner.nextInt();
                    cms.editItem(editItemName, newQuantity);
                    break;
                case 4:
                    System.out.println("Total Bill: " + cms.showTotalBill());
                    System.out.println("Thank you for shopping with us!");
                    break;
                case 5:
                    System.out.println("Exiting...");
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

        }
    }
}