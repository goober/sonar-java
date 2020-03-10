package checks;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Collection;

class StandardCharsetsConstantsCheck_java6 {
  private Charset charset;
  private byte[] bytes;
  private char[] chars;
  private int offset;
  private int length;
  private int bufferSize;
  private int blockSize;
  private boolean append;
  private boolean writeImmediately;
  private String dataString;
  private String inputString;
  private String lineEndingString;
  private String lockDirString;
  private String charsetName;
  private InputStream inputStream;
  private OutputStream outputStream;
  private Reader reader;
  private Writer writer;
  private StringBuffer stringBuffer;
  private CharSequence charSequence;
  private Collection<?> collection;
  private File file;
  private Path path;
  private URI uri;
  private URL url;
  private ReadableByteChannel readableByteChannel;

  void myMethod() throws Exception {
    charset = com.google.common.base.Charsets.ISO_8859_1;
    charset = com.google.common.base.Charsets.US_ASCII;
    charset = com.google.common.base.Charsets.UTF_16;
    charset = com.google.common.base.Charsets.UTF_16BE;
    charset = com.google.common.base.Charsets.UTF_16LE;
    charset = com.google.common.base.Charsets.UTF_8;

    // Canonical names of java.nio API and java.io/java.lang API
    Charset.forName("ISO-8859-1");
    Charset.forName("ISO_8859_1");
    Charset.forName("US-ASCII");
    Charset.forName("ASCII");
    Charset.forName("UTF-16");
    Charset.forName("UTF-16BE");
    Charset.forName("UnicodeBigUnmarked");
    Charset.forName("UTF-16LE");
    Charset.forName("UnicodeLittleUnmarked");
    Charset.forName("UTF-8");
    Charset.forName("UTF8");
    Charset.forName("utf-8");
    Charset.forName("Utf-8");

    org.apache.commons.codec.Charsets.toCharset("UTF-8");

    org.apache.commons.io.Charsets.toCharset("UTF-8");

    org.apache.commons.io.IOUtils.toString(bytes, "UTF-8");
    org.apache.commons.io.IOUtils.toString(inputStream, "UTF-8");
    org.apache.commons.io.IOUtils.toString(uri, "UTF-8");
    org.apache.commons.io.IOUtils.toString(url, "UTF-8");

    "".getBytes("UTF-8");

    new String(bytes, org.apache.commons.lang.CharEncoding.UTF_8);
    new String(bytes, offset, length, org.apache.commons.lang.CharEncoding.UTF_8);

    new InputStreamReader(inputStream, org.apache.commons.lang.CharEncoding.UTF_8);
    new OutputStreamWriter(outputStream, org.apache.commons.lang.CharEncoding.UTF_8);

    new org.apache.commons.codec.binary.Hex("UTF-8");
    new org.apache.commons.codec.net.QuotedPrintableCodec("UTF-8");

    org.apache.commons.io.FileUtils.readFileToString(file, "UTF-8");
    org.apache.commons.io.FileUtils.readLines(file, "UTF-8");
    org.apache.commons.io.FileUtils.write(file, charSequence, "UTF-8");
    org.apache.commons.io.FileUtils.write(file, charSequence, "UTF-8", append);
    org.apache.commons.io.FileUtils.writeStringToFile(file, dataString, "UTF-8");
    org.apache.commons.io.FileUtils.writeStringToFile(file, dataString, "UTF-8", append);
    org.apache.commons.io.IOUtils.copy(inputStream, writer, "UTF-8");
    org.apache.commons.io.IOUtils.copy(reader, outputStream, "UTF-8");
    org.apache.commons.io.IOUtils.lineIterator(inputStream, "UTF-8");
    org.apache.commons.io.IOUtils.readLines(inputStream, "UTF-8");
    org.apache.commons.io.IOUtils.toByteArray(reader, "UTF-8");
    org.apache.commons.io.IOUtils.toCharArray(inputStream, "UTF-8");
    org.apache.commons.io.IOUtils.toInputStream(charSequence, "UTF-8");
    org.apache.commons.io.IOUtils.toInputStream(inputString, "UTF-8");
    org.apache.commons.io.IOUtils.write(bytes, writer, "UTF-8");
    org.apache.commons.io.IOUtils.write(chars, outputStream, "UTF-8");
    org.apache.commons.io.IOUtils.write(charSequence, outputStream, "UTF-8");
    org.apache.commons.io.IOUtils.write(dataString, outputStream, "UTF-8");
    org.apache.commons.io.IOUtils.write(stringBuffer, outputStream, "UTF-8");
    org.apache.commons.io.IOUtils.writeLines(collection, lineEndingString, outputStream, "UTF-8");
    new org.apache.commons.io.input.CharSequenceInputStream(charSequence, "UTF-8");
    new org.apache.commons.io.input.CharSequenceInputStream(charSequence, "UTF-8", bufferSize);
    new org.apache.commons.io.input.ReaderInputStream(reader, "UTF-8");
    new org.apache.commons.io.input.ReaderInputStream(reader, "UTF-8", bufferSize);
    new org.apache.commons.io.input.ReversedLinesFileReader(file, blockSize, "UTF-8");
    new org.apache.commons.io.output.LockableFileWriter(file, "UTF-8");
    new org.apache.commons.io.output.LockableFileWriter(file, "UTF-8", append, lockDirString);
    new org.apache.commons.io.output.WriterOutputStream(writer, "UTF-8");
    new org.apache.commons.io.output.WriterOutputStream(writer, "UTF-8", bufferSize, writeImmediately);

    // Compliant
    charset = StandardCharsets.ISO_8859_1;
    charset = StandardCharsets.US_ASCII;
    charset = StandardCharsets.UTF_16;
    charset = StandardCharsets.UTF_16BE;
    charset = StandardCharsets.UTF_16LE;
    charset = StandardCharsets.UTF_8;

    "".getBytes(charsetName);
    "".getBytes("Windows-1252");

    Charset charset = Charset.forName(charsetName);
    "".getBytes(charset);

    new String(bytes, charsetName);
    new String(bytes, offset, length, charsetName);

    new InputStreamReader(inputStream, charsetName);
    new OutputStreamWriter(outputStream, charsetName);
  }
}
