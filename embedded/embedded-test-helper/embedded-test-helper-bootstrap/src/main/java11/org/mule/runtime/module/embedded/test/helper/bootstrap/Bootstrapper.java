/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.test.helper.bootstrap;

import static org.mule.runtime.module.embedded.test.helper.bootstrap.util.DependencyResolver.getLibs;

import static java.io.File.separator;
import static java.lang.ClassLoader.getSystemClassLoader;
import static java.lang.ModuleLayer.boot;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.lang.module.ModuleFinder.of;
import static java.lang.module.ModuleFinder.ofSystem;
import static java.nio.file.Files.walk;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Bootstrapper {

  private static final Path outputDirectory = Path.of(getProperty("user.dir"));

  static Class<?> getHelperClass() throws Exception {
    final Set<String> bootModules = boot().modules().stream().map(Module::getName).collect(toSet());
    ModuleFinder finder = of(getLibs("", outputDirectory));
    List<ModuleReference> rootModules = finder.findAll().stream()
        .filter(moduleRef -> !bootModules.contains(moduleRef.descriptor().name())).collect(toList());
    Path[] filteredModulesPaths = rootModules.stream().map(ModuleReference::location).filter(Optional::isPresent)
        .map(Optional::get).map(Paths::get).toArray(Path[]::new);
    ModuleFinder filteredModulesFinder = of(filteredModulesPaths);
    ModuleLayer parent = boot();
    final List<String> roots = rootModules.stream().map(moduleRef -> moduleRef.descriptor().name())
        .collect(toList());
    Configuration configuration = parent.configuration().resolve(filteredModulesFinder, ofSystem(), roots);

    ModuleLayer layer = parent.defineModulesWithOneLoader(configuration, getSystemClassLoader());
    openToModule(layer, "org.mule.runtime.jpms.utils", "java.base", asList("java.lang", "java.lang.reflect"));

    return layer.findLoader("org.mule.distribution.embedded.test.helper")
        .loadClass("org.mule.runtime.module.embedded.test.helper.EmbeddedTestHelper");
  }

  public static void openToModule(ModuleLayer layer, String moduleName, String bootModuleName,
                                  List<String> packages) {
    layer.findModule(moduleName).ifPresent(module -> boot().findModule(bootModuleName).ifPresent(bootModule -> {
      for (String pkg : packages) {
        bootModule.addOpens(pkg, module);
      }
    }));
  }

}
