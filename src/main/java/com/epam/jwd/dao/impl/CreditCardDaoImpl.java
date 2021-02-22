package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.CreditCardDao;
import com.epam.jwd.domain.CreditCard;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CreditCardDaoImpl extends AbstractDao<CreditCard> implements CreditCardDao {
    private static CreditCardDaoImpl instance;

    private static final String SQL_GET_BY_USER_ID =
            "SELECT card.id, card.owner_name, card.number, card.CVV, card.expiration FROM card \n" +
                    "JOIN user_card on card_id = card.id WHERE user_id = ?";

    private static final String SQL_INSERT_CARDS_BY_USER_ID =
            "INSERT INTO user_card(user_id, card_id) VALUES (?, ?)";

    private static final String SQL_INSERT_CARD =
            "INSERT INTO card(owner_name, number, CVV, expiration) VALUES (?, ?, ?, ?)";

    private static final String SQL_REMOVE_USER_CARDS =
            "DELETE FROM user_card WHERE user_id = ?";

    private static final String SQL_REMOVE_CARD =
            "DELETE FROM card WHERE card.id = ?";

    private static final String SQL_REMOVE_LINK =
            "DELETE FROM user_card WERE card_id = ?";

    public static CreditCardDaoImpl getInstance() {
        if (instance == null) {
            instance = new CreditCardDaoImpl();
        }
        return instance;
    }

    @Override
    public List<CreditCard> getAll() throws DaoException {
        throw new UnsupportedOperationException("Getting all cards unsupported");
    }

    @Override
    public Optional<CreditCard> getById(int id) throws DaoException {
        throw new UnsupportedOperationException("Getting by id is unsupported");
    }

    @Override
    public void insert(Connection connection, CreditCard entity) throws DaoException {
        super.insert(connection, SQL_INSERT_CARD, entity);
    }

    @Override
    public void update(Connection connection, CreditCard entity) throws DaoException {
        throw new UnsupportedOperationException("Updating cards is unsupported");
    }

    @Override
    public void remove(Connection connection, int id) throws DaoException {
        super.deleteByField(connection, SQL_REMOVE_CARD, id);
    }

    @Override
    public List<CreditCard> getUserCards(int userId) throws DaoException {
        List<CreditCard> userCards = new ArrayList<>();
        Connection connection = this.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SQL_GET_BY_USER_ID)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CreditCard card = mapToEntity(rs);
                userCards.add(card);
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return userCards;
    }

    @Override
    public boolean insertAllByUserId(Connection connection, List<CreditCard> userCards, int userId) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_CARDS_BY_USER_ID)) {
            for (CreditCard card : userCards) {
                ps.setInt(1, userId);
                ps.setInt(2, card.getId());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public void removeUserCards(Connection con, Integer userId) throws DaoException {
        try (PreparedStatement ps = con.prepareStatement(SQL_REMOVE_USER_CARDS)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    protected CreditCard mapToEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("card.id");
        String owner = rs.getString("card.owner_name");
        String number = rs.getString("card.number");
        String CVV = rs.getString("card.cvv");
        Date expirationDate = rs.getDate("card.expiration");
        return new CreditCard(id, owner, number, CVV, expirationDate);
    }

    @Override
    protected void mapFromEntity(PreparedStatement ps, CreditCard entity) throws SQLException {
        ps.setString(1, entity.getOwner());
        ps.setString(2, entity.getNumber());
        ps.setString(3, entity.getCVV());
        ps.setDate(4, new java.sql.Date(entity.getExpiration().getTime()));
    }

}
