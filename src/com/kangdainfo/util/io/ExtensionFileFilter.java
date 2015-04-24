package com.kangdainfo.util.io;

import java.io.*;

/**
 *
 * @author shark
 */
public class ExtensionFileFilter implements java.io.FileFilter {
	private String ext;

	public ExtensionFileFilter(String fileExtension) {
		this.ext = fileExtension;
	}

    public boolean accept(File f) {
        // Always allow the user to see directories in order to navigate
        if (f.isDirectory()) {
            return true;
        }
        String extension = FileIO.getExtension(f);
        if ((extension != null) && (extension.equals(ext.toLowerCase()))) {
            return true;
        } else {
            return false;
        }
    }

    public String getDescription() {
        return (ext + "Format Files");
    }
}
