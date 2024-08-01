/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.impl.util;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;
import static java.nio.file.Files.copy;
import static java.nio.file.Files.createDirectories;
import static java.nio.file.Files.walkFileTree;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {

  private FileUtils() {
    // Nothing to do
  }

  public static void copyFolder(Path source, Path target)
      throws IOException {
    walkFileTree(source, new SimpleFileVisitor<Path>() {

      @Override
      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
          throws IOException {
        // Skip hidden directories
        if (dir.getFileName().toString().startsWith(".")) {
          return SKIP_SUBTREE;
        }
        createDirectories(target.resolve(source.relativize(dir).toString()));
        return CONTINUE;
      }

      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
          throws IOException {
        // Skip hidden files
        if (file.getFileName().toString().startsWith(".")) {
          return CONTINUE;
        }
        copy(file, target.resolve(source.relativize(file).toString()));
        return CONTINUE;
      }

    });
  }

}
