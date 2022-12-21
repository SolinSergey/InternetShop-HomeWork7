package api;
public class CartPositionDto {
    private Long id;
    private String title;
    private int price;
    private int amount;

    public CartPositionDto() {
    }

    public CartPositionDto(Long id, String title, int price, int amount) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.amount = amount;
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
}
