package com.epam.jwd.util;

import com.epam.jwd.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JSONParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONParser.class);

    public String parseCategoriesToJSON(List<Category> categories) {
        LOGGER.trace("Parsing categories to JSON");

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Category u : categories) {
            sb.append("{");
            sb.append("\"id\": \"" + u.getId() + "\",");
            sb.append("\"name\": \"" + u.getName() + "\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    public String parseManufacturersToJSON(List<Manufacturer> manufacturers) {
        LOGGER.trace("Parsing manufacturers to JSON");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Manufacturer m : manufacturers) {
            sb.append("{");
            sb.append("\"id\": \"" + m.getId() + "\",");
            sb.append("\"name\": \"" + m.getName() + "\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    public String parseCardsToJSON(List<CreditCard> cards) {
        LOGGER.trace("Parsing cards to JSON");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (CreditCard c : cards) {
            sb.append("{");
            sb.append("\"id\": \"").append(c.getId()).append("\",");
            sb.append("\"owner\": \"").append(c.getOwner()).append("\",");
            sb.append("\"price\": \"").append(c.getNumber()).append("\",");
            sb.append("\"expiration\": \"").append(c.getExpiration()).append("\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    public String parseProductsToJSON(List<Product> products) {
        LOGGER.trace("Parsing products to JSON");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Product p : products) {
            sb.append("{");
            sb.append("\"id\": \"").append(p.getId()).append("\",");
            sb.append("\"name\": \"").append(p.getName()).append("\",");
            sb.append("\"price\": \"").append(p.getPrice()).append("\",");
            sb.append("\"manufacturer\": \"").append(p.getManufacturer().getName()).append("\",");
            sb.append("\"description\": \"").append(p.getDescription()).append("\",");
            sb.append("\"category\": \"").append(p.getCategory().getName()).append("\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    public String parseUsersToJSON(List<User> users) {
        LOGGER.trace("Parsing users to JSON");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (User u : users) {
            sb.append("{");
            sb.append("\"id\": \"" + u.getId() + "\",");
            sb.append("\"name\": \"" + u.getName() + "\",");
            sb.append("\"surname\": \"" + u.getSurname() + "\",");
            sb.append("\"status\": \"" + u.getStatus().getName() + "\",");
            int count = 1;
            for (UserRole r : u.getRoles()) {
                sb.append("\"role" + count + "\": \"" + r.getName() + "\",");
                count++;
            }
            sb.append("\"email\": \"" + u.getEmail() + "\",");
            sb.append("\"balance\": \"" + u.getBalance() + "\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    public String parseRentalsToJSON(List<Rental> rentals) {
        LOGGER.trace("Parsing rentals to JSON");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Rental r : rentals) {
            sb.append("{");
            sb.append("\"id\": \"" + r.getId() + "\",");
            sb.append("\"rentalDate\": \"" + r.getRentalDate() + "\",");
            sb.append("\"returnDate\": \"" + r.getReturnDate() + "\",");
            sb.append("\"status\": \"" + r.getStatus() + "\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

}
