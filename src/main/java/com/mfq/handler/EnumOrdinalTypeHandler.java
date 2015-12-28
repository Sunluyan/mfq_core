package com.mfq.handler;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public abstract class EnumOrdinalTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    @SuppressWarnings("unused")
    private Class<E> type;

    @SuppressWarnings("unused")
    private final Method values;

    public EnumOrdinalTypeHandler(Class<E> type) {
        this.type = type;
        try {
            this.values = type.getDeclaredMethod("values");
        } catch (Exception ex) {
            throw new IllegalArgumentException("Class " + type.getSimpleName()
                    + " is not an enumeration class.", ex);
        }
    }

    protected abstract Object getEnumValue(E parameter);

    protected abstract E getEnumFromValue(Object value);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter,
            JdbcType jdbcType) throws SQLException {
        ps.setObject(i, getEnumValue(parameter));
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Object i = rs.getObject(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return getEnumFromValue(i);
        }
    }

    public E getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Object i = rs.getObject(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return getEnumFromValue(i);
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Object i = cs.getObject(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return getEnumFromValue(i);
        }
    }
}