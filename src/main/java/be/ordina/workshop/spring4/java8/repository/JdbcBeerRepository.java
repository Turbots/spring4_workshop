package be.ordina.workshop.spring4.java8.repository;

import be.ordina.workshop.spring4.java8.model.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by stevedezitter on 19/02/15.
 */
@Repository
public class JdbcBeerRepository implements BeerRepository {

    private static final String INSERT_BEER = "insert into beer(name, description, alcoholPercentage) values (?, ?, ?)";
    private static final String UPDATE_BEER = "update beer set name=?, description=?, alcoholPercentage=? where id=?";
    private static final String SELECT_BEER_BY_NAME = "select * from beer where name=?";
    private static final String SELECT_BEER_BY_ID = "select * from beer where id=?";
    private static final String SELECT_ALL_BEERS = "select * from beer";

    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcBeerRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Beer> getAllBeers() {
        List<Beer> beers = jdbcOperations.queryForList(SELECT_ALL_BEERS, Beer.class);

        return beers;
    }

    @Override
    public Beer getBeerByName(String name) {
        Beer beer = jdbcOperations.queryForObject(SELECT_BEER_BY_NAME,
            (rs, rowNum) -> {
                return new Beer(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getBigDecimal("alcoholPercentage")
                );
            },
            name);

        return beer;
    }

    @Override
    public Beer getBeerById(Long id) {
        Beer beer = jdbcOperations.queryForObject(SELECT_BEER_BY_ID,
            (rs, rowNum) -> {
                return new Beer(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getBigDecimal("alcoholPercentage")
                );
            },
            id);

        return beer;
    }

    @Override
    public void insertBeer(Beer beer) {
        jdbcOperations.update(INSERT_BEER,
                beer.getName(),
                beer.getDescription(),
                beer.getAlcoholPercentage());
    }

    @Override
    public void updateBeer(Beer beer) {
        jdbcOperations.update(UPDATE_BEER,
                beer.getName(),
                beer.getDescription(),
                beer.getAlcoholPercentage(),
                beer.getId());
    }

    //'Old' rowmapper used in the jdbcOperations.queryForObject method
    private static final class BeerRowMapper implements RowMapper<Beer> {
        public Beer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Beer(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBigDecimal("alcoholPercentage")
            );
        }
    }
}
