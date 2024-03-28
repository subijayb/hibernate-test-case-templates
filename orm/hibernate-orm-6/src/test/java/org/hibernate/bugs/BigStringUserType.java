package org.hibernate.bugs;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.metamodel.spi.ValueAccess;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.usertype.UserType;

public class BigStringUserType implements CompositeUserType<UserValue>, UserType<UserValue>{

    private static final int MAX_VARCHAR_CHARACTER_LENGTH = 1333;

    private static final Logger logger = LogManager.getLogger(BigStringUserType.class);

    static private final int[] SQL_TYPES = {Types.VARCHAR, Types.CLOB};
    
    public static class BigStringMapper {
		String uservalue;
		String uservalue_clob;
	}


    @Override
    public boolean equals(UserValue x, UserValue y) throws HibernateException {
        if ((x == null) && (y == null)) {
            return true;
        }
        if ((x == null) || (y == null)) {
            return false;
        }

        return x.equals(y);
    }

    @Override
    public UserValue deepCopy(UserValue value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public UserValue assemble(Serializable cached, Object owner)
            throws HibernateException {
    	final String[] parts = (String[]) cached;
		return new UserValue( parts[0], parts[1] );
    }

    @Override
    public Serializable disassemble(UserValue value)
            throws HibernateException {
        return new UnsupportedOperationException();//(String) value;
    }

    @Override
    public Object getPropertyValue(UserValue component, int property) throws HibernateException {
    	return switch (property) {  // Hibernate sorts the properties by their names alphabetically
		case 0 -> component.getUservalue();
		case 1 -> component.getUservalue_clob();
		default -> null;
	};
	
    }

    @Override
    public int hashCode(UserValue x) throws HibernateException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public UserValue instantiate(ValueAccess values, SessionFactoryImplementor sessionFactory) {
        // TODO Auto-generated method stub
    	final String property_value = values.getValue(0, String.class);
		final String property_value_clob = values.getValue(1, String.class);
		return new UserValue(property_value, property_value_clob);
    }

    @Override public Class<?> embeddable() { return BigStringMapper.class; }

	@Override public Class<UserValue> returnedClass() { return UserValue.class; }
	
    @Override
    public UserValue replace(UserValue detached, UserValue managed, Object owner) {
       return detached;//deepCopy(detached);
    }

	@Override
	public int getSqlType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserValue nullSafeGet(ResultSet resultSet, int position, SharedSessionContractImplementor session, Object owner)
			throws SQLException {
		 if (resultSet.wasNull()) {
	            return null;
	        }
		 if(position==0) {
			 return new UserValue(resultSet.getString(position),null);
		 }
		 else
			 return new UserValue(null, resultSet.getString(position)); 
	}

	@Override
	public void nullSafeSet(PreparedStatement statement, UserValue value, int index, SharedSessionContractImplementor session)
			throws SQLException {
		if (value == null) {
            statement.setNull(index, Types.VARCHAR);
            statement.setNull(index + 1, Types.CLOB);
        } else {
            String userValueClob = (String) value.getUservalue_clob();
            String userValue = (String) value.getUservalue();
            if (userValue.length() < userValueClob.length()) {
                statement.setString(index, userValueClob);
                statement.setNull(index + 1, Types.CLOB);
            } else {
                statement.setNull(index, Types.VARCHAR);
                statement.setString(index + 1, userValue);
            }
        }
		
	}

//	@Override
//	public int getSqlType() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public Object nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
//			throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
//			throws SQLException {
//		// TODO Auto-generated method stub
//		
//	}
}
