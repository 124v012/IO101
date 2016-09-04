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
import java.io.File;
import java.util.List;

/**
 * Interface that describes a contract to read a character-based file
 * into a List<String>, and write a List<String> to a character-based file.
 * The contract stipulates that the input or output file be represented
 * by a File object.
 * 
 * @author sperry
 *
 */
public interface FileProcessor {

  /**
   * Read a character-based file into a List<String>.
   * 
   * @param inputFile
   *          File object representing the input file.
   *          Caller is responsible for creating the File object.
   * 
   * @return List<String> - the contents of the file, one line
   *         at-a-time in a List of Strings.
   */
  public List<String> readIntoLines(File inputFile);

  /**
   * Write a List<String> to the specified output File.
   * Caller is responsible for creating the File object.
   * 
   * @param lines
   *          The List of Strings to write to the file.
   *          The order of lines in the List must be preserved in the
   *          output file.
   * 
   * @param outputFile
   *          The File object representing the output
   *          file. The caller is responsible for creating this object.
   */
  public void writeLines(List<String> lines, File outputFile);

  /**
   * Copies the specified input File to the specified output File.
   * 
   * @param inputFile
   *          The input File. The caller is responsible for creating
   *          the File object representing the input file.
   * 
   * @param outputFile
   *          The output File. The caller is responsible for creating
   *          the File object representing the output file.
   */
  public void copy(File inputFile, File outputFile);

}
