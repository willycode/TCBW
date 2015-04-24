package com.kangdainfo.util.io.file;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Iterator;
import java.util.List;

/* opencsv 1.6 */
public class CSVWriter {
    
    private Writer rawWriter;

    private PrintWriter pw;

    private char separator;

    private char quotechar;
    
    private String lineEnd;

    /** The character used for escaping quotes. */
    public static final char ESCAPE_CHARACTER = '"';

    /** The default separator to use if none is supplied to the constructor. */
    public static final char DEFAULT_SEPARATOR = ',';

    /**
     * The default quote character to use if none is supplied to the
     * constructor.
     */
    public static final char DEFAULT_QUOTE_CHARACTER = '"';
    
    /** The quote constant to use when you wish to suppress all quoting. */
    public static final char NO_QUOTE_CHARACTER = '\u0000';
    
    /** Default line terminator uses platform encoding. */
    public static final String DEFAULT_LINE_END = "\n";

    /**
     * Constructs CSVWriter using a comma for the separator.
     *
     * @param writer
     *            the writer to an underlying CSV source.
     */
    public CSVWriter(Writer writer) {
        this(writer, DEFAULT_SEPARATOR);
    }

    /**
     * Constructs CSVWriter with supplied separator.
     *
     * @param writer
     *            the writer to an underlying CSV source.
     * @param separator
     *            the delimiter to use for separating entries.
     */
    public CSVWriter(Writer writer, char separator) {
        this(writer, separator, DEFAULT_QUOTE_CHARACTER);
    }

    /**
     * Constructs CSVWriter with supplied separator and quote char.
     *
     * @param writer
     *            the writer to an underlying CSV source.
     * @param separator
     *            the delimiter to use for separating entries
     * @param quotechar
     *            the character to use for quoted elements
     */
    public CSVWriter(Writer writer, char separator, char quotechar) {
    	this(writer, separator, quotechar, "\n");
    }

    /**
     * Constructs CSVWriter with supplied separator and quote char.
     *
     * @param writer
     *            the writer to an underlying CSV source.
     * @param separator
     *            the delimiter to use for separating entries
     * @param quotechar
     *            the character to use for quoted elements
     * @param lineEnd
     * 			  the line feed terminator to use
     */
    public CSVWriter(Writer writer, char separator, char quotechar, String lineEnd) {
        this.rawWriter = writer;
        this.pw = new PrintWriter(writer);
        this.separator = separator;
        this.quotechar = quotechar;
        this.lineEnd = lineEnd;
    }
    
    /**
     * Writes the entire list to a CSV file. The list is assumed to be a
     * String[]
     *
     * @param allLines
     *            a List of String[], with each String[] representing a line of
     *            the file.
     */
    public void writeAll(List allLines)  {

        for (Iterator iter = allLines.iterator(); iter.hasNext();) {
            String[] nextLine = (String[]) iter.next();
            writeNext(nextLine);
        }

    }

    protected void writeColumnNames(ResultSetMetaData metadata)
    	throws SQLException {
    	
    	int columnCount =  metadata.getColumnCount();
    	
    	String[] nextLine = new String[columnCount];
		for (int i = 0; i < columnCount; i++) {
			nextLine[i] = metadata.getColumnName(i + 1);
		}
    	writeNext(nextLine);
    }
    
    /**
     * Writes the entire ResultSet to a CSV file.
     *
     * The caller is responsible for closing the ResultSet.
     *
     * @param rs the recordset to write
     * @param includeColumnNames true if you want column names in the output, false otherwise
     *
     */
    public void writeAll(java.sql.ResultSet rs, boolean includeColumnNames)  throws SQLException, IOException {
    	
    	ResultSetMetaData metadata = rs.getMetaData();
    	
    	
    	if (includeColumnNames) {
			writeColumnNames(metadata);
		}

    	int columnCount =  metadata.getColumnCount();
    	
    	while (rs.next())
    	{
        	String[] nextLine = new String[columnCount];
        	
        	for (int i = 0; i < columnCount; i++) {
				nextLine[i] = getColumnValue(rs, metadata.getColumnType(i + 1), i + 1);
			}
        	
    		writeNext(nextLine);
    	}
    }
    
    private static String getColumnValue(ResultSet rs, int colType, int colIndex)
    		throws SQLException, IOException {

    	String value = "";
    	
		switch (colType)
		{
			case Types.BIT:
				Object bit = rs.getObject(colIndex);
				value = String.valueOf(bit);
			break;
			case Types.BOOLEAN:
				boolean b = rs.getBoolean(colIndex);
				if (!rs.wasNull()) {
				value = Boolean.valueOf(b).toString();
			}
			break;
			case Types.CLOB:
				value = read(rs.getClob(colIndex));
			break;
			case Types.BIGINT:
			case Types.DECIMAL:
			case Types.DOUBLE:
			case Types.FLOAT:
			case Types.REAL:
			case Types.NUMERIC:
				value = "" + rs.getBigDecimal(colIndex).doubleValue();
			break;
			case Types.INTEGER:
			case Types.TINYINT:
			case Types.SMALLINT:
				int intValue = rs.getInt(colIndex);
				if (!rs.wasNull()) {
					value = "" + intValue;
				}
			break;
			case Types.JAVA_OBJECT:
				Object obj = rs.getObject(colIndex);
				if (obj != null) {
					value = String.valueOf(obj);
				}
			break;
			case Types.DATE:
				value = rs.getDate(colIndex).toString();
			break;
			case Types.TIME:
				Time t = rs.getTime(colIndex);
				value = t.toString();
			break;
			case Types.TIMESTAMP:
				Timestamp tstamp = rs.getTimestamp(colIndex);
				value = tstamp.toString();
			break;
			case Types.LONGVARCHAR:
			case Types.VARCHAR:
			case Types.CHAR:
				value = rs.getString(colIndex);
			break;
			default:
				value = "";
		}

		return value;
    	
    }

	private static String read(Clob c) throws SQLException, IOException
	{
		StringBuffer sb = new StringBuffer( (int) c.length());
		Reader r = c.getCharacterStream();
		char[] cbuf = new char[2048];
		int n = 0;
		while ((n = r.read(cbuf, 0, cbuf.length)) != -1) {
			if (n > 0) {
				sb.append(cbuf, 0, n);
			}
		}
		r.close();
		return sb.toString();
	}
    
    /**
     * Writes the next line to the file.
     *
     * @param nextLine
     *            a string array with each comma-separated element as a separate
     *            entry.
     */
    public void writeNext(String[] nextLine) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < nextLine.length; i++) {

            if (i != 0) {
                sb.append(separator);
            }

            String nextElement = nextLine[i];
            if (nextElement == null)
                continue;
            if (quotechar !=  NO_QUOTE_CHARACTER)
            	sb.append(quotechar);
            for (int j = 0; j < nextElement.length(); j++) {
                char nextChar = nextElement.charAt(j);
                if (nextChar == quotechar) {
                    sb.append(ESCAPE_CHARACTER).append(nextChar);
                } else if (nextChar == ESCAPE_CHARACTER) {
                    sb.append(ESCAPE_CHARACTER).append(nextChar);
                } else {
                    sb.append(nextChar);
                }
            }
            if (quotechar != NO_QUOTE_CHARACTER)
            	sb.append(quotechar);
        }
        
        sb.append(lineEnd);
        pw.write(sb.toString());

    }

    /**
     * Close the underlying stream writer flushing any buffered content.
     *
     * @throws IOException if bad things happen
     *
     */
    public void close() throws IOException {
        pw.flush();
        pw.close();
        rawWriter.close();
    }

}
