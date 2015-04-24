package com.kangdainfo.util;

import java.util.Hashtable;
import java.util.Map;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class LdapUtil
{
	private String domain;
	private String ldapHost;
	private String searchBase;

	public LdapUtil()
	{
		this.domain = "<your domain>";
		this.ldapHost = "ldap://<your AD controller>";
		this.searchBase = "your AD root e.g. dc=abbl,dc=org";
	}

	public LdapUtil(String domain, String host, String dn)
	{
		this.domain = domain;
		this.ldapHost = host;
		this.searchBase = dn;
	}

	public Map<Object,Object> authenticate(String user, String pass)
	{
		String returnedAtts[] ={ "sn", "givenName", "mail" };
		String searchFilter = "(&(objectClass=user)(sAMAccountName=" + user + "))";

		//Create the search controls
		SearchControls searchCtls = new SearchControls();
		searchCtls.setReturningAttributes(returnedAtts);

		//Specify the search scope
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		Hashtable<Object,Object> env = new Hashtable<Object,Object>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapHost);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, user + "@" + domain);
		env.put(Context.SECURITY_CREDENTIALS, pass);

		LdapContext ctxGC = null;

		try
		{
			ctxGC = new InitialLdapContext(env, null);
			//Search objects in GC using filters
			NamingEnumeration<SearchResult> rs = ctxGC.search(searchBase, searchFilter, searchCtls);
			while (rs.hasMoreElements()) {
				SearchResult sr = rs.next();
				Attributes attrs = sr.getAttributes();
				if (attrs!=null && attrs.size()>0) {
					java.util.Map<Object, Object> h = new java.util.HashMap<Object, Object>();
					NamingEnumeration<?> ne = attrs.getAll();
					while (ne.hasMore()) {
						Attribute attr = (Attribute) ne.next();
						h.put(attr.getID(), attr.get());
					}
					ne.close();
					return h;
				}

			}      

		}
		catch (NamingException ex)
		{
			ex.printStackTrace();
		}

		return null;
	}
}
