package com.github.schali.util.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Enhancement to the {@link ObjectOutputStream} which is capable of appending data to an existing file.
 * 
 * @see ObjectOutputStream
 * @author Florian Müller
 */
public class AppendableObjectOutputStream extends ObjectOutputStream{

	/**
	 * Private constructor, so that static factory methods must be used.
	 * 
	 * @param out the {@link OutputStream} to write to
	 * @throws IOException in case of error
	 * @see ObjectOutputStream#ObjectOutputStream(OutputStream)
	 */
	private AppendableObjectOutputStream(OutputStream out) throws IOException {
		super(out);
	}
	
	/**
	 * Overrides the default behavior and omits the header so that the stram/file is not 
	 * corrupted and data is appended correctly.
	 */
	@Override
	protected void writeStreamHeader() throws IOException {
		//omit header to avoid corrupting the stream/file
	}
	
	/**
	 * Creates a new {@link ObjectOutputStream} which writes to the specified filename. If the specified file
	 * does not yet exist it will be created. If the file already exists data will be appended.
	 * 
	 * @param filename the name of the file to write to
	 * @return the {@link ObjectOutputStream}
	 * @throws FileNotFoundException 	if the file exists but is a directory rather than a regular file, 
	 * 									does not exist but cannot be created, or cannot be opened for any other reason 
	 * @throws IOException if an I/O error occurs
	 */
	public static ObjectOutputStream createObjectOutputStreamForFile(String filename) throws FileNotFoundException, IOException {
		File file = new File(filename);
		return createObjectOutputStreamForFile(file);
	}
	
	/**
	 * Creates a new {@link ObjectOutputStream} which writes to the specified file. If the specified file
	 * does not yet exist it will be created. If the file already exists data will be appended.
	 * 
	 * @param file the file to write to
	 * @return the {@link ObjectOutputStream}
	 * @throws FileNotFoundException 	if the file exists but is a directory rather than a regular file, 
	 * 									does not exist but cannot be created, or cannot be opened for any other reason 
	 * @throws IOException if an I/O error occurs
	 */
	public static ObjectOutputStream createObjectOutputStreamForFile(File file) throws FileNotFoundException, IOException {
		if(file.exists() && file.length() > 0)
			return new AppendableObjectOutputStream(new FileOutputStream(file, true));
		else
			return new ObjectOutputStream(new FileOutputStream(file));
	}
	
}
