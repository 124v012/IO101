package com.makotojava.learn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
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
    String filesDir = "files";
    String inputPath = filesDir + File.separator + filename;

    InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputPath);

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

    String filename = "lines_Stream.txt";
    String filesDir = "files";
    String inputPath = filesDir + File.separator + filename;
    OutputStream outputStream = new FileOutputStream(inputPath);
    classUnderTest.writeLines(lines, outputStream);
  }

  @Test
  public void testCopy() throws Exception {
    String inputFilename = "lorem.txt";
    String outputFilename = "lorem_copyStream.txt";
    String filesDir = "files";
    String inputPath = filesDir + File.separator + inputFilename;
    String outputPath = filesDir + File.separator + outputFilename;
    InputStream inputStream =
        Thread.currentThread().getContextClassLoader().getResourceAsStream(inputPath);
    OutputStream outputStream = new FileOutputStream(outputPath);
    classUnderTest.copy(inputStream, outputStream);
    // Now verify the file was correctly copied
    InputStream inputStreamOrig =
        Thread.currentThread().getContextClassLoader().getResourceAsStream(inputPath);
    InputStream inputStreamCopy =
        Thread.currentThread().getContextClassLoader().getResourceAsStream(outputPath);
    List<String> origLines = classUnderTest.readIntoLines(inputStreamOrig);
    List<String> copyLines = classUnderTest.readIntoLines(inputStreamCopy);
    assertEquals(origLines, copyLines);
  }

}
