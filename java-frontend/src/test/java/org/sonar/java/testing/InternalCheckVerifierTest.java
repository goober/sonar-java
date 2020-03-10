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

import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;

import static org.assertj.core.api.Assertions.assertThat;

public class InternalCheckVerifierTest {

  private static final String TEST_FILE = "src/test/files/testing/A.java";

  @Nested
  class VerifierInitialConfiguration {

    @Test
    public void setting_checks_is_required() {
      try {
        InternalCheckVerifier.newInstance()
          .withJavaVersion(11)
          .onFile(TEST_FILE)
          .verifyNoIssues();
      } catch (AssertionError e) {
        assertThat(e).hasMessage("Set check(s) before calling any verification method!");
      }
    }

    @Test
    public void setting_files_is_required() {
      try {
        InternalCheckVerifier.newInstance()
          .withoutSemantic()
          .withCheck(new NoEffectCheck())
          .verifyNoIssues();
      } catch (AssertionError e) {
        assertThat(e).hasMessage("Set file(s) before calling any verification method!");
      }
    }

    @Test
    public void setting_multiple_times_java_version_fails() {
      try {
        InternalCheckVerifier.newInstance()
          .withJavaVersion(6)
          .withJavaVersion(7);
        Fail.fail("Should have failed");
      } catch (AssertionError e) {
        assertThat(e).hasMessage("Do not set java version multiple times!");
      }
    }

    @Test
    public void setting_multiple_times_one_files_fails() {
      try {
        InternalCheckVerifier.newInstance()
          .onFile(TEST_FILE)
          .onFile(TEST_FILE);
        Fail.fail("Should have failed");
      } catch (AssertionError e) {
        assertThat(e).hasMessage("Do not set file(s) multiple times!");
      }
    }

    @Test
    public void setting_multiple_times_multiple_files_fails() {
      try {
        InternalCheckVerifier.newInstance()
          .onFiles(TEST_FILE)
          .onFile(TEST_FILE);
        Fail.fail("Should have failed");
      } catch (AssertionError e) {
        assertThat(e).hasMessage("Do not set file(s) multiple times!");
      }
    }
  }

  @Nested
  class ProjectIssues {

    @Test
    void verify_should_work() {
      InternalCheckVerifier.newInstance()
        .onFile(TEST_FILE)
        .withCheck(new ProjectIssueCheck())
        .verifyIssueOnProject("issueOnProject");
    }

    @Test
    void not_raising_issues_should_fail() {
      try {
        InternalCheckVerifier.newInstance()
          .onFile(TEST_FILE)
          .withCheck(new NoEffectCheck())
          .verifyIssueOnProject("issueOnProject");
        Fail.fail("Should have failed");
      } catch (AssertionError e) {
        assertThat(e).hasMessage("A single issue is expected on the project, but none has been raised");
      }
    }

    @Test
    void raising_too_many_issues_should_fail() {
      try {
        InternalCheckVerifier.newInstance()
          .onFile(TEST_FILE)
          .withChecks(new ProjectIssueCheck(), new ProjectIssueCheck())
          .verifyIssueOnProject("issueOnProject");
        Fail.fail("Should have failed");
      } catch (AssertionError e) {
        assertThat(e).hasMessage("A single issue is expected on the project, but 2 issues have been raised");
      }
    }

    @Test
    void raising_an_issue_on_file_instead_of_project_should_fail() {
      try {
        InternalCheckVerifier.newInstance()
          .onFile(TEST_FILE)
          .withChecks(new ProjectIssueCheck())
          .verifyIssueOnProject("expected");
        Fail.fail("Should have failed");
      } catch (AssertionError e) {
        assertThat(e).hasMessage(String.format("%s%n%s%n%s%n%s",
          "Expected the issue message to be:",
          "\t\"expected\"",
          "but was:",
          "\t\"issueOnProject\""));
      }
    }

    @Test
    void raising_an_issue_line_instead_of_project_should_fail() {
      try {
        InternalCheckVerifier.newInstance()
          .onFile(TEST_FILE)
          .withChecks(new FileLineIssueCheck())
          .verifyIssueOnProject("issueOnProject");
        Fail.fail("Should have failed");
      } catch (AssertionError e) {
        assertThat(e).hasMessage("Expected an issue directly on project but was raised on line 1");
      }
    }

    @Test
    void raissing_a_different_message_should_fail() {
      try {
        InternalCheckVerifier.newInstance()
          .onFile(TEST_FILE)
          .withChecks(new FileIssueCheck())
          .verifyIssueOnProject("issueOnProject");
        Fail.fail("Should have failed");
      } catch (AssertionError e) {
        assertThat(e).hasMessage("Expected the issue to be raised at project level, not on the file");
      }
    }
  }

  @Rule(key = "NoEffectCheck")
  private static final class NoEffectCheck implements JavaFileScanner {

    @Override
    public void scanFile(JavaFileScannerContext context) {
      // do nothing
    }
  }

  @Rule(key = "FileIssueCheck")
  private static final class FileIssueCheck implements JavaFileScanner {

    @Override
    public void scanFile(JavaFileScannerContext context) {
      context.addIssueOnFile(this, "issueOnFile");
    }
  }

  @Rule(key = "FileLineIssueCheck")
  private static final class FileLineIssueCheck implements JavaFileScanner {

    @Override
    public void scanFile(JavaFileScannerContext context) {
      context.addIssue(1, this, "issueOnLine");
    }
  }

  @Rule(key = "ProjectIssueCheck")
  private static final class ProjectIssueCheck implements JavaFileScanner {

    @Override
    public void scanFile(JavaFileScannerContext context) {
      context.addIssueOnProject(this, "issueOnProject");
    }
  }
}
