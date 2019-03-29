package de.metamorphant.examples;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedRegexTest {
  private static final String REGEX = "[-+]?\\d+(\\.\\d+)?([eE][-+]?\\d+)?";

  @Parameters(name = "{index} {2}: is {0} well-formed? {1}")
  public static Collection<Object[]> examples() {
    return Arrays.asList(new Object[][] {
        {"", false, "empty string"},
        {"a", false, "single non-digit"},
        {"1", true, "single digit"},
        {"123", true, "integer"},
        {"-123", true, "integer, negative sign"},
        {"+123", true, "integer, positive sign"},
        {"123.12", true, "float"},
        {"123.12e", false, "float with exponent extension but no value"},
        {"123.12e12", true, "float with exponent"},
        {"123.12E12", true, "float with uppercase exponent"},
        {"123.12e12.12", false, "float with non-integer exponent"},
        {"123.12e+12", true, "float with exponent, positive sign"},
        {"123.12e-12", true, "float with exponent, negative sign"},
    });
  }

  @Parameter(0)
  public String input;

  @Parameter(1)
  public boolean isMatchExpected;

  @Parameter(2)
  public String description;

  @Test
  public void regexTest() {
    Boolean matches = input.matches(REGEX);
    assertEquals(isMatchExpected, matches);
  }
}
