package com.lsr.estudospringjdbc.repositories.filter;

import com.lsr.estudospringjdbc.DefaultSortProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FilterRepositoryImpl<T> implements FilterRepository<T> {

    private final JdbcTemplate jdbcTemplate;

    public FilterRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public Page<T> search(String termToSearch, Integer page, Integer size, String sortProperty, String sortDirection, Class<T> clazzType) {
        if (sortProperty == null || sortProperty.equals("undefined") || sortProperty.equals("null") || sortProperty.isEmpty()) {
            sortProperty = getDefaultSortProperty(clazzType);
        }

        if (size == null) {
            size = 10;
        }

        if (page == null) {
            page = 0;
        }
        Sort.Direction direction = sortDirection != null && sortDirection.equalsIgnoreCase("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(page, size, direction, sortProperty);

        StringBuilder sb = new StringBuilder("SELECT * FROM ").append(getTableName(clazzType)).append(" WHERE");
        Field[] fields = clazzType.getDeclaredFields();
        for (Field field : fields) {
            if (Collection.class.isAssignableFrom(field.getType()) || Map.class.isAssignableFrom(field.getType()) || field.isAnnotationPresent(Id.class)) {
                continue;
            }
            String columnName = getColumnName(field);
            sb.append(" ").append(columnName).append(" LIKE '%").append(termToSearch).append("%' OR");
        }
        String finalSql = sb.substring(0, sb.length() - 3);
        finalSql += " LIMIT " + page + ", " + size;
        System.out.println(finalSql);
        List<T> result = jdbcTemplate.query(finalSql, new BeanPropertyRowMapper<T>(clazzType));
        List<Map<String, Object>> result2 = jdbcTemplate.query(finalSql, new ColumnMapRowMapper());

        return new PageImpl<>(result, pageRequest, 10);
    }

    private String getDefaultSortProperty(Class<T> clazz) {
        String paramSort = "";

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DefaultSortProperty.class)) {
                paramSort = field.getName();
                break;
            }
        }

        return paramSort;
    }

    private String getTableName(Class<T> clazz) {
        return clazz.getAnnotation(Table.class).value();
    }

    private String getColumnName(Field field) {
        return field.getAnnotation(Column.class).value();
    }

}
