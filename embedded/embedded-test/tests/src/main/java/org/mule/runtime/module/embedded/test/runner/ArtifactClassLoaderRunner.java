/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.test.runner;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.Filterable;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.JUnit4;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ArtifactClassLoaderRunner extends Runner implements Filterable {


  private static final Logger LOGGER = LoggerFactory.getLogger(ArtifactClassLoaderRunner.class);

  private static Exception errorCreatingClassLoaderTestRunner;
  private static boolean staticFieldsInjected = false;
  private static Throwable errorWhileSettingClassLoaders;
  private static ClassLoader currentClassLoader;

  private final Object delegate;
  private final Class<?> delegateClass;
  private static ClassLoader artifactClassLoader;

  /**
   * Creates a Runner to run {@code klass}
   *
   * @param clazz
   * @param builder
   * @throws Throwable if there was an error while initializing the runner.
   */
  public ArtifactClassLoaderRunner(Class<?> clazz, RunnerBuilder builder) throws Throwable {
    try {
      artifactClassLoader = new TestClassLoader(new URL[] {
          new File("/Users/rbourbon/.m2/repository/org/mule/runtime/mule-embedded-api/1.7.0/mule-embedded-api-1.7.0.jar").toURI()
              .toURL(),
          clazz.getProtectionDomain().getCodeSource().getLocation(),
          JUnit4.class.getProtectionDomain().getCodeSource().getLocation()},
                                                ArtifactClassLoaderRunner.class.getClassLoader());
      delegateClass = artifactClassLoader.loadClass(JUnit4.class.getName());
      Class<?> testClass = artifactClassLoader.loadClass(clazz.getName());
      delegate = delegateClass.cast(delegateClass.getConstructor(Class.class).newInstance(testClass));
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
      throw new InitializationError(e);
    }
  }

  static class TestClassLoader extends URLClassLoader {

    public TestClassLoader(URL[] urls, ClassLoader parent) {
      super(urls, parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
      if (name.startsWith("org.example.embedded.test") || name.equals(JUnit4.class.getName())) {
        return super.findClass(name);
      }
      return super.loadClass(name);
    }
  }

  /**
   * @return delegates to the internal runner to get the description needed by JUnit.
   */
  @Override
  public Description getDescription() {
    // return delegate.getDescription();
    try {
      return (Description) delegateClass.getMethod("getDescription").invoke(delegate);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * When the test is about to be executed the ThreadContextClassLoader is changed to use the application class loader that was
   * created so the execution of the test will be done using an isolated class loader that mimics the standalone container.
   *
   * @param notifier the {@link RunNotifier} from JUnit that will be notified about the results of the test methods invoked.
   */
  @Override
  public void run(RunNotifier notifier) {

    final Thread currentThread = Thread.currentThread();
    final ClassLoader currentClassLoader = currentThread.getContextClassLoader();

    currentThread.setContextClassLoader(artifactClassLoader);
    // delegate.run(notifier);
    try {
      delegateClass.getMethod("run", RunNotifier.class).invoke(delegate, notifier);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    currentThread.setContextClassLoader(currentClassLoader);
  }

  /**
   * Delegates to the inner runner to filter.
   *
   * @param filter the {@link Filter} from JUnit to select a single test.
   * @throws NoTestsRemainException
   */
  @Override
  public void filter(Filter filter) throws NoTestsRemainException {
    if (delegate instanceof Filterable) {
      // ((Filterable) delegate).filter(filter);
      try {
        delegateClass.getMethod("filter", Filter.class).invoke(delegate);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
