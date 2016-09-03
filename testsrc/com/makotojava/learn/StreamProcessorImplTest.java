package com.makotojava.learn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StreamProcessorImplTest {

  private StreamProcessor classUnderTest;

  @Before
  public void setUp() throws Exception {
    classUnderTest = new StreamProcessorImpl();
  }

  @Test
  public void testReadIntoLines() {
    // Under Run Configuration > Classpath tab
    // Make sure to add the files directory to User Entries
    String filename = "lorem.txt";

    InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);

    List<String> lines = classUnderTest.readIntoLines(inputStream);
    assertNotNull(lines);
    assertEquals(49, lines.size());
  }

  private static final String[] LINES = {
      "LINE 1",
      "LINE 2",
      "LINE 3",
      "LINE 4",
      "LINE 5"
  };

  @Test
  public void testWriteLines() throws Exception {
    List<String> lines = Arrays.asList(LINES);

    String filename = "./files/lines_Stream.txt";
    OutputStream outputStream = new FileOutputStream(filename);
    classUnderTest.writeLines(lines, outputStream);
  }

  @Test
  public void testCopy() throws Exception {
    String inputFilename = "lorem.txt";
    String outputFilename = "lorem_copyStream.txt";
    String filesDir = "files/";
    InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputFilename);
    OutputStream outputStream = new FileOutputStream(filesDir + outputFilename);
    classUnderTest.copy(inputStream, outputStream);
    // Now verify the file was correctly copied
    InputStream inputStreamOrig = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputFilename);
    InputStream inputStreamCopy = Thread.currentThread().getContextClassLoader().getResourceAsStream(outputFilename);
    List<String> origLines = classUnderTest.readIntoLines(inputStreamOrig);
    List<String> copyLines = classUnderTest.readIntoLines(inputStreamCopy);
    assertEquals(origLines, copyLines);
  }

}
