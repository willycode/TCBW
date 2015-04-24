package com.kangdainfo.util.io.file;

import java.util.*;
import java.io.*;

public class LargeFileReader implements Iterable<String> {
	private BufferedReader _reader;

	public LargeFileReader(String filePath) throws Exception {
		_reader = new BufferedReader(new FileReader(filePath));
	}

	public LargeFileReader(java.io.File f, String encoding) throws Exception {
		InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(f), encoding);
		_reader = new BufferedReader(inputStreamReader);
	}	
	
	public LargeFileReader(String filePath, String encoding) throws Exception {
		InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath), encoding);
		_reader = new BufferedReader(inputStreamReader);
	}
	
	public void Close() {
		try	{
			_reader.close();
		} catch (Exception ex) {}
	}

	public Iterator<String> iterator() {
		return new FileIterator();
	}

	private class FileIterator implements Iterator<String> {
		private String _currentLine;
		public boolean hasNext() {
			try {
				_currentLine = _reader.readLine();
			} catch (Exception ex) {
				_currentLine = null;
				ex.printStackTrace();
			}
			return _currentLine != null;
		}

		public String next() {
			return _currentLine;
		}

		public void remove() {
		}
	}
}
