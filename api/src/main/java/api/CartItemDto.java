package api;
public class CartItemDto {
    private Long id;
    private String title;
    private int price;
    private int amount;
    private int totalPrice;

    public CartItemDto() {
    }

    public CartItemDto(Long id, String title, int price, int amount, int totalPrice) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void calcTotalPrice(){
        totalPrice=price*amount;
    }

    @Override
    public String toString() {
        return "CartItemDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
