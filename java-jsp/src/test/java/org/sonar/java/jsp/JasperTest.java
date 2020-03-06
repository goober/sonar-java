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
package org.sonar.java.jsp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.internal.DefaultInputFile;
import org.sonar.api.batch.fs.internal.TestInputFileBuilder;
import org.sonar.api.batch.sensor.internal.SensorContextTester;

import static org.assertj.core.api.Assertions.assertThat;

class JasperTest {

  @TempDir
  Path tempFolder;
  Path webInf;

  @TempDir
  Path workDir;

  @BeforeEach
  void setUp() throws Exception {
    webInf = tempFolder.resolve("WEB-INF");
    Files.createDirectory(webInf);
  }

  @Test
  void test_empty() throws Exception {
    SensorContextTester ctx = SensorContextTester.create(tempFolder);
    ctx.fileSystem().setWorkDir(workDir);
    List<InputFile> generatedFiles = new Jasper().generateFiles(ctx, Collections.emptyList());
    assertThat(generatedFiles).isEmpty();
  }

  @Test
  void test_compilation() throws Exception {
    String jspSource = "<html>\n" +
      "<body>\n" +
      "<h2>Hello World!</h2>\n" +
      "</body>\n" +
      "</html>";
    Path jsp = createJspFile(jspSource);
    SensorContextTester ctx = SensorContextTester.create(tempFolder);
    DefaultInputFile inputFile = TestInputFileBuilder.create("", tempFolder.toFile(), jsp.toFile())
      .setLanguage("jsp")
      .setContents(jspSource)
      .build();
    ctx.fileSystem().add(inputFile);
    ctx.fileSystem().setWorkDir(workDir);
    List<InputFile> generatedFiles = new Jasper().generateFiles(ctx, Collections.emptyList());

    assertThat(generatedFiles).hasSize(1);
    InputFile generatedFile = generatedFiles.iterator().next();
    List<String> generatedCode = Files.readAllLines(generatedFile.path());
    assertThat(generatedCode).contains("      out.write(\"<html>\\n<body>\\n<h2>Hello World!</h2>\\n</body>\\n</html>\");");
  }

  Path createJspFile(String content) throws IOException {
    Path path = webInf.resolve("test.jsp");
    Files.write(path, content.getBytes(StandardCharsets.UTF_8));
    return path;
  }

}
