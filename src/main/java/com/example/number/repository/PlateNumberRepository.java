package com.example.number.repository;

import com.example.platenumber.converter.PlateNumberConverter;
import com.example.platenumber.mapper.PlateRowMapper;
import com.example.platenumber.model.PlateNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PlateNumberRepository {
    private final NamedParameterJdbcTemplate template;
    private final PlateRowMapper rowMapper = new PlateRowMapper(new PlateNumberConverter());

    public List<PlateNumber> getAll() {
        return template.query("select id, plateNumber  from numbers order by id limit 5",
                rowMapper
        );
    }

    public PlateNumber getById(long id) {
        return template.queryForObject("select id, plateNumber from numbers where id = :id",
                Map.of("id", id),
                rowMapper
        );
    }

    public Boolean isExists(String plateNumber) {

        Long count = template.queryForObject("select count(plateNumber) as count from numbers" +
                        " where plateNumber = :plateNumber",
                Map.of("plateNumber", plateNumber),
                ((resultSet, i) -> resultSet.getLong("count"))
        );
        if (count > 0) {
            return true;
        }
        return false;
    }

    public PlateNumber save(PlateNumber item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update("insert into numbers(plateNumber)" +
                        " VALUES (:plateNumber)",
                new MapSqlParameterSource(Map.of(
                        "plateNumber", item.getFirstLetter() + item.getNumber()
                                + item.getSecondLetter() + item.getThirdLetter()
                )),
                keyHolder
        );
        long id = keyHolder.getKey().longValue();
        return getById(id);
    }

    public PlateNumber getLast() {
        return template.query("select id, plateNumber from numbers order by id desc limit 1",
                rowMapper
        ).get(0);
    }

}
