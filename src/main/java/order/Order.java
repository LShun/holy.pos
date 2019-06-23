package order;

public class Order {
    public static void order() {
    }
}

//This is a visual cart to record the items purchased by customer/scan by staff
class Cart{
    private ArrayList<Product> items; //List of items in cart
    private int count;                //Total number of item in cart

    Cart(){
        this.items = new ArrayList<Product>();
        this.count = 0;
    }
    public void add(int index){}//Add an item into cart}
    public void del(int index){}//Del an item from cart}
    public void proceed(){}//Calculate price}
    public void clear(){}//Clear the cart}
}