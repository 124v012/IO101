package com.makotojava.learn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FileProcessorImplTest {

  private FileProcessor classUnderTest;

  @Before
  public void setUp() throws Exception {
    classUnderTest = new FileProcessorImpl();
  }

  @Test
  public void testReadIntoLines() {
    // Under Run Configuration > Arguments tab
    // Make sure to set working directory to ${workspace_loc:IO101/files}
    String filename = "lorem.txt";
    String filesDir = "files";
    String inputPath = filesDir + File.separator + filename;

    File inputFile = new File(inputPath);

    List<String> lines = classUnderTest.readIntoLines(inputFile);
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
  public void testWriteLines() {
    List<String> lines = Arrays.asList(LINES);
    // Under Run Configuration > Arguments tab
    // Make sure to set working directory to ${workspace_loc:IO101/files}
    String filename = "lines_File.txt";
    String filesDir = "files";
    String outputPath = filesDir + File.separator + filename;

    File outputFile = new File(outputPath);
    if (outputFile.exists()) {
      outputFile.delete();
    }
    classUnderTest.writeLines(lines, outputFile);
  }

  @Test
  public void testCopy() {
    String inputFilename = "lorem.txt";
    String outputFilename = "lorem_copyFile.txt";
    String filesDir = "files";
    String inputPath = filesDir + File.separator + inputFilename;
    String outputPath = filesDir + File.separator + outputFilename;

    File inputFile = new File(inputPath);
    File outputFile = new File(outputPath);
    classUnderTest.copy(inputFile, outputFile);
    // Now verify the file was correctly copied
    List<String> linesOrig = classUnderTest.readIntoLines(inputFile);
    List<String> linesCopy = classUnderTest.readIntoLines(outputFile);
    assertEquals(linesOrig, linesCopy);
  }

}
