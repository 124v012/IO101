package com.makotojava.learn;

/*
 * Copyright 2016 Makoto Consulting Group, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementation of the FileProcessor interface.
 * 
 * @author sperry
 *
 */
public class FileProcessorImpl implements FileProcessor {

  private static final Logger log = Logger.getLogger(FileProcessorImpl.class.getName());

  @Override
  public List<String> readIntoLines(File inputFile) {
    List<String> ret = new ArrayList<String>();
    // Use a straight-up BufferedReader wrapping a FileReader
    // JDK 8 try with-resources is oh so nice. Resources get cleaned up when references go out of scope.
    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
      String line = reader.readLine();
      while (line != null) {
        ret.add(line);
        line = reader.readLine();
      }
    } catch (IOException e) {
      String message = "Error occurred while processing the file: " + e.getLocalizedMessage();
      log.severe(message);
      e.printStackTrace();
      throw new RuntimeException(message, e);
    }
    return ret;
  }

  @Override
  public void writeLines(List<String> lines, File outputFile) {
    // Use a straight-up BufferedWriter wrapping a FileWriter.
    // JDK 8 try with-resources is oh so nice. Resources get cleaned up when references go out of scope.
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
      int lineNumber = 0;
      for (String line : lines) {
        if (lineNumber++ > 0) {
          writer.newLine();
        }
        writer.write(line);
      }
    } catch (IOException e) {
      String message = "Error occurred while processing the file: " + e.getLocalizedMessage();
      log.severe(message);
      e.printStackTrace();
      throw new RuntimeException(message, e);
    }
  }

  @Override
  public void copy(File inputFile, File outputFile) {
    // Copy the inputFile to the outputFile
    List<String> lines = readIntoLines(inputFile);
    writeLines(lines, outputFile);
  }

}
