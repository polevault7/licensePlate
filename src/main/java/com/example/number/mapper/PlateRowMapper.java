package com.example.number.mapper;


import com.example.number.converter.PlateNumberConverter;
import com.example.number.model.PlateNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class PlateRowMapper implements RowMapper<PlateNumber> {
    private final PlateNumberConverter converter;

    @Override
    public PlateNumber mapRow(ResultSet rs, int rowNum) throws SQLException {
        return converter.fromString(
                rs.getLong("id"),
                rs.getString("plateNumber"));
    }
}
