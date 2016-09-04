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
