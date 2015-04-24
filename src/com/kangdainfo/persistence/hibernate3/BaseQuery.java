package com.kangdainfo.persistence.hibernate3;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateOperations;


import com.kangdainfo.persistence.PersistenceServiceGetter;

public class BaseQuery {

	private HibernateOperations hibernateTemplate;

	public void setHibernateTemplate(HibernateOperations hibernateTemplate) { this.hibernateTemplate = hibernateTemplate; }
	public HibernateOperations getHibernateTemplate() {
		if(hibernateTemplate==null) {
			return PersistenceServiceGetter.getInstance().getHibernateTemplate();			
		}
		return hibernateTemplate;
	}

	
	public String appendOrderBy(String queryString, String alias, String orderBy, boolean isAscending) {
		if(orderBy!=null) {
			String array[] = orderBy.split(",");
			orderBy = "";
			for(int i=0; i < array.length; i++) {
				orderBy += alias + "." + array[i];
				if(i!=array.length-1) {
					orderBy += ",";
				}
			}
		}
    	return new HQL(queryString).appendOrderBy(orderBy, isAscending).toString();
    }
	public String getOrderByString(String alias, String orderBy, Boolean isAscending) {
		if(orderBy==null) {
			return "";
		}
		String array[] = orderBy.split(",");
		StringBuffer sb = new StringBuffer();
		sb.append(" order by ");
		for(int i=0,max_i=array.length; i < max_i; i++) {
			sb.append(alias + "." + array[i]);
			if(i!=max_i-1) {
				sb.append(",");
			}
		}
		if(isAscending!=null) {
			if(isAscending) {
				sb.append(" asc");
			}
			else {
				sb.append(" desc");
			}
		}
		return sb.toString();
	}
	public void setQuery(Query query,final String fieldName, final Object fieldValue) {
   	 	if(fieldValue instanceof Integer) {
        	query.setInteger(fieldName, ((Integer) fieldValue).intValue());
        }
   	 	else if(fieldValue instanceof Long) {
        	query.setLong(fieldName, ((Long) fieldValue).longValue());
        }
        else if(fieldValue instanceof Short) {
        	query.setShort(fieldName, ((Short) fieldValue).shortValue());
        }
        else if(fieldValue instanceof Double) {
        	query.setDouble(fieldName, ((Double) fieldValue).doubleValue());
        }
        else if(fieldValue instanceof Float) {
        	query.setFloat(fieldName, ((Float) fieldValue).floatValue());
        }
        else if(fieldValue instanceof Boolean) {
        	query.setBoolean(fieldName, ((Boolean) fieldValue).booleanValue());
        }
        else if(fieldValue instanceof Character) {
        	query.setCharacter(fieldName, ((Character) fieldValue).charValue());
        }
        else if(fieldValue instanceof String) {
        	query.setString(fieldName, (String) fieldValue);
        }
        else if(fieldValue instanceof java.util.Date) {
        	query.setDate(fieldName, (java.util.Date) fieldValue);
        }
        else {
        	query.setEntity(fieldName,  fieldValue);
        }
   }    

    public Object toObject(Object var) {
    	return var;
    }

    public Object toObject(int var) {
    	return new Integer(var);
    }

    public Object toObject(long var) {
    	return new Long(var);
    }

    public Object toObject(short var) {
    	return new Short(var);
    }

    public Object toObject(double var) {
    	return new Double(var);
    }

    public Object toObject(float var) {
    	return new Float(var);
    }

    public Object toObject(boolean var) {
    	return new Boolean(var);
    }

    public Object toObject(char var) {
    	return new Character(var);
    }
	    
    public boolean isHibernateEmbededType(Object type) {
        if(type instanceof Boolean || type instanceof Character
                || type instanceof Byte || type instanceof Short
                || type instanceof Integer || type instanceof Long
                || type instanceof Float || type instanceof Double
                || type instanceof Calendar || type instanceof String
                || type instanceof Date || type instanceof Class) {
            return true;
        }
        return false;
    }
    
}
