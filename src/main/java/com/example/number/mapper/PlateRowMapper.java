package com.example.number.mapper;

import com.example.platenumber.converter.PlateNumberConverter;
import com.example.platenumber.model.PlateNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class PlateRowMapper implements RowMapper<PlateNumber> {
    private final PlateNumberConverter converter;

    @Override
    public PlateNumber mapRow(ResultSet rs, int rowNum) throws SQLException {
//        return new PlateNumber(
        return converter.fromString(
                rs.getLong("id"),
                rs.getString("plateNumber"));

//                new (
//                        rs.getString("firstLetter").charAt(0),
//                        rs.getInt("number"),
//                        rs.getString("secondLetter").charAt(0),
//                        rs.getString("thirdLetter").charAt(0)
//                )
    }
}
