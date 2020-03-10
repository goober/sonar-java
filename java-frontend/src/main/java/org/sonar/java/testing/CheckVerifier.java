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
package org.sonar.java.testing;

import java.io.File;
import java.util.Collection;
import org.sonar.plugins.java.api.JavaFileScanner;

public interface CheckVerifier {

  static CheckVerifier newVerifier() {
    return InternalCheckVerifier.newInstance();
  }

  CheckVerifier withCheck(JavaFileScanner check);

  CheckVerifier withChecks(JavaFileScanner... checks);

  CheckVerifier withClassPath(Collection<File> classpath);

  CheckVerifier withJavaVersion(int javaVersionAsInt);

  CheckVerifier onFile(String filename);

  CheckVerifier onFiles(String... otherFilenames);

  CheckVerifier onFiles(Collection<String> filenames);

  CheckVerifier withoutSemantic();

  void verifyIssues();

  void verifyIssueOnFile(String expectedIssueMessage);

  void verifyIssueOnProject(String expectedIssueMessage);

  void verifyNoIssues();
}
