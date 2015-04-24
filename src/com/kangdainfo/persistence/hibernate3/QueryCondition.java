package com.kangdainfo.persistence.hibernate3;

public interface QueryCondition {

	public String AND = "and";

	public String OR = "or";

	public String EQ = "=";

	public String NOT_EQ = "<>";

	public String GE = ">";

	public String LE = "<";

	public String GE_EQ = ">=";

	public String LE_EQ = "<=";

	public String LIKE_MORE = "like"; // like '%value%'

	public String NOT_LIKE_MORE = "not like";

	public String LIKE = "like"; // like 'value%'

	public String NOT_LIKE = "not like";

	public String IN = "in";

	public String NOT_IN = "not in";

	public String BETWEEN = "between";

	public String NOT_BETWEEN = "not between";

	public String IS_NULL = "is null";

	public String IS_NOT_NULL = "is not null";
}