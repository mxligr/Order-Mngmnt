package Model;
/**
 *
 * @author Grama Malina Bianca
 * @since May 13, 2021
 * Orders -> resembles the orders table from the database
 */
public class Orders {

    private int idOrder;
    private int idClient;
    private int idProduct;
    private int quantity;

    public Orders(int idClient, int idProduct, int quantity) {
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public Orders(int idOrder, int idClient, int idProduct, int quantity) {
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
