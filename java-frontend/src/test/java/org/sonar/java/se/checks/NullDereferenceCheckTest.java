/*
 * SonarQube Java
 * Copyright (C) 2012-2020 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.java.se.checks;

import org.junit.Test;
import org.sonar.java.testing.SEJavaCheckVerifier;

public class NullDereferenceCheckTest {

  @Test
  public void test() {
    SEJavaCheckVerifier.verify("src/test/files/se/NullDereferenceCheck.java", new NullDereferenceCheck());
  }

  @Test
  public void objectsMethodsTest() {
    SEJavaCheckVerifier.verify("src/test/files/se/ObjectsMethodsTest.java", new NullDereferenceCheck());
  }

  @Test
  public void null_array_access() {
    SEJavaCheckVerifier.verify("src/test/files/se/NullArrayAccess.java", new NullDereferenceCheck());
  }

  @Test
  public void chained_method_invocation_issue_order() {
    SEJavaCheckVerifier.verify("src/test/files/se/MethodParamInvocationOrder.java", new NullDereferenceCheck());
  }

  @Test
  public void invocation_leading_to_NPE() {
    SEJavaCheckVerifier.verify("src/test/files/se/MethodInvocationLeadingToNPE.java", new NullDereferenceCheck());
  }

  @Test
  public void reporting_test() {
    SEJavaCheckVerifier.verify("src/test/files/se/NPE_reporting.java", new NullDereferenceCheck());
  }

  @Test
  public void ruling() {
    SEJavaCheckVerifier.verifyNoIssue("src/test/files/se/NPEwithZeroTests.java", new NullDereferenceCheck());
  }

  @Test
  public void test_deferred_reporting() throws Exception {
    SEJavaCheckVerifier.verify("src/test/files/se/NPE_deferred.java", new NullDereferenceCheck());
  }

  @Test
  public void test_npe_transitive() throws Exception {
    SEJavaCheckVerifier.verify("src/test/files/se/NPE_transitive.java", new NullDereferenceCheck());
  }
}
