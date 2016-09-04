package com.makotojava.learn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementation of the InputStreamProcessor interface.
 * 
 * @author sperry
 *
 */
public class StreamProcessorImpl implements StreamProcessor {

  private static final Logger log = Logger.getLogger(StreamProcessorImpl.class.getName());

  @Override
  public List<String> readIntoLines(InputStream inputStream) {
    List<String> ret = new ArrayList<String>();
    // Uses a BufferedReader, with InputStreamReader bridge creamy center
    // JDK 8 try with-resources is oh so nice. Resources get cleaned up when references go out of scope.
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      String line = reader.readLine();
      while (line != null) {
        ret.add(line);
        line = reader.readLine();
      }
    } catch (IOException e) {
      String message = "Error occurred while processing the InputStream: " + e.getLocalizedMessage();
      log.severe(message);
      e.printStackTrace();
      throw new RuntimeException(message, e);
    }
    return ret;
  }

  @Override
  public void writeLines(List<String> lines, OutputStream outputStream) {
    // Uses a BufferedWriter, with OutputStreamWriter bridge as creamy filling
    // JDK 8 try with-resources is oh so nice. Resources get cleaned up when references go out of scope.
    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
      int lineNumber = 0;
      for (String line : lines) {
        if (lineNumber++ > 0) {
          writer.newLine();
        }
        writer.write(line);
      }
    } catch (IOException e) {
      String message = "Error occurred while processing the OutputStream: " + e.getLocalizedMessage();
      log.severe(message);
      e.printStackTrace();
      throw new RuntimeException(message, e);
    }
  }

  @Override
  public void copy(InputStream inputStream, OutputStream outputStream) {
    // Copy the data from the InputStream to the OutputStream.
    List<String> inputLines = readIntoLines(inputStream);
    writeLines(inputLines, outputStream);
  }

}
