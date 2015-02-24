package be.ordina.workshop.spring4.java8.repository;

import be.ordina.workshop.spring4.java8.model.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private static final String SELECT_BEER_BY_NAME_AND_ALCOHOL_PERCENTAGE = "select * from beer where name like ? and alcoholPercentage>?";
    private static final String SELECT_BEER_LAST_MODIFIED_TIMESTAMP_LATER_THAN =
            "select * from beer where modifiedTimestamp > ?";

    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcBeerRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Beer> getAllBeers() {
        List<Beer> beers = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcOperations.queryForList(SELECT_ALL_BEERS);

        beers = rows.stream().map((map)-> {
            Beer beer = new Beer();
            beer.setId((Long)map.get("id"));
            beer.setName((String)map.get("name"));
            beer.setDescription((String)map.get("description"));
            beer.setAlcoholPercentage((BigDecimal)map.get("alcoholPercentage"));
            beer.setModifiedTimestamp((Timestamp)map.get("modifiedTimestamp"));
            return beer;
        }).collect(Collectors.toList());

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
                    rs.getBigDecimal("alcoholPercentage"),
                    rs.getTimestamp("modifiedTimestamp")
                );
            },
            name);

//        Beer beer = jdbcOperations.queryForObject(SELECT_BEER_BY_NAME,
//                this::mapRow,
//                name);

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
                    rs.getBigDecimal("alcoholPercentage"),
                    rs.getTimestamp("modifiedTimestamp")
                );
            },
            id);

        return beer;
    }

    public List<Beer> getBeerByNameAndAlcoholPercentage(String name, BigDecimal alcoholPercentage) {
        //SELECT_BEER_BY_NAME_AND_ALCOHOL_PERCENTAGE
        List<Beer> beers = jdbcOperations.query(SELECT_BEER_BY_NAME_AND_ALCOHOL_PERCENTAGE,
                ps -> {
                    ps.setString(1, "%" + name + "%");
                    ps.setBigDecimal(2, alcoholPercentage);
                },
                (rs, rowNum) -> new Beer(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBigDecimal("alcoholPercentage"),
                        rs.getTimestamp("modifiedTimestamp")
                )
        );

        return beers;
    }

    @Override
    public List<Beer> getBeersLastModifiedTimestampGreaterThan(Timestamp timestamp) {
        return jdbcOperations.query(SELECT_BEER_LAST_MODIFIED_TIMESTAMP_LATER_THAN,
                ps -> ps.setTimestamp(1, timestamp),
                this::mapRow);
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

    //Method that can be used as method reference (because it adheres to the contract of RowMapper<T>
    private Beer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Beer(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBigDecimal("alcoholPercentage"),
                rs.getTimestamp("modifiedTimestamp")
        );
    }

    //'Old' rowmapper used in the jdbcOperations.queryForObject method
    private static final class BeerRowMapper implements RowMapper<Beer> {
        public Beer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Beer(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBigDecimal("alcoholPercentage"),
                rs.getTimestamp("modifiedTimestamp")
            );
        }
    }
}
