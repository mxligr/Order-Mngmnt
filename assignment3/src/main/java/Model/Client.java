package Model;
/**
 *
 * @author Grama Malina Bianca
 * @since May 13, 2021
 * Client -> resembles the client table from the database
 */
public class Client {

    private int idClient;
    private String name;
    private String email;
    private String phoneNumber;

    public Client(int idClient, String name, String email, String phoneNumber) {
        this.idClient = idClient;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Client(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
