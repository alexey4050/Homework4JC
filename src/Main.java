

enum Gender {MALE, FEMALE}

enum Discount {
    ZERO(0), FIVE(5), TEN(10), FIFTEEN(15), TWENTY(20);

    private final int value;

    Discount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

class Customer {
    private String name;
    private int age;
    private Gender gender;

    public Customer(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}


class Product {
    private String name;
    private double price;
    private String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

class Order {
    private Customer customer;
    private Product product;
    private int quantity;
    private double cost;
    private Discount discount;

    public Order(Customer customer, Product product, int quantity, double cost, Discount discount) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.cost = cost;
        this.discount = discount;
        calculateCost();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
        calculateCost();
    }

    private  void calculateCost(){
        double price = product.getPrice() * quantity;
        double discountAmount = price * discount.getValue() / 100;
        cost = price - discountAmount;
        if (product.getCategory() == "Премиум" && discount.getValue() > 15) {
            throw new TooMuchSaleException("Скидка на товар Премиум превышает 15%");
        }
    }
}

class TooMuchSaleException extends RuntimeException {
    public TooMuchSaleException(String message){
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Пертров Петр", 35, Gender.MALE);
        Product product = new Product("Планшет", 25000.0, "Премиум");
        Order order = new Order(customer, product, 1, 25000.0, Discount.TWENTY);

        System.out.println("Стоимость заказа " + product.getCategory() + " " + product.getName() + " - " + product.getPrice());

        setRandomDiscount(product);

        System.out.println("Стоимость заказа со скидкой " + order.getCost());
    }
    private static void setRandomDiscount(Product product) {
        Discount[] discountValues = Discount.values();
        Discount randomDiscount = discountValues[(int) (Math.random() * discountValues.length)];
        product.setPrice(product.getPrice() * (100 - randomDiscount.getValue()) / 100);
    }
}