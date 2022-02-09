package BLL;

import BLL.Vals.EmailValidator;
import BLL.Vals.PhoneNumberValidator;
import BLL.Vals.Val;
import DAO.ClientDAO;
import Model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Grama Malina Bianca
 * @since May 13, 2021
 * Class that calls the Client queries found in AbstractDAO and ClientDAO and implements the validators for the email address and phone number of the clients
 */

public class ClientBLL {
    private final List<Val<Client>> validators;

    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new PhoneNumberValidator());
    }

    public ArrayList<Client> listClients() throws Exception {
        ArrayList<Client> arr = ClientDAO.returnClients();
        if(arr == null) {
            throw new Exception("No clients in the table!");
        }
        return arr;
    }

    public Client findClientById(int id) {
        Client st = ClientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }

    public Client findClientByName(String name) {
        Client st = ClientDAO.findByName(name);
        if (st == null) {
            throw new NoSuchElementException("The client with name =" + name + " was not found!");
        }
        return st;
    }

    public int insertClient(Client client) {
        for (Val<Client> v : validators) {
            v.validate(client);
        }
        return ClientDAO.insert(client);
    }

    public int updateClient(Client client) {
        return ClientDAO.update(client);
    }

    public void deleteClient(Client client) {
        ClientDAO.delete(client);
    }
}
