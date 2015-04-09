package dear.zr.utils;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JSGeneratorTest {

    private JSGenerator jsGenerator;

    @Before
    public void setUp() throws Exception {
        jsGenerator = new JSGenerator();
    }

    @Test
    public void define_variable() throws Exception {
        String tempVariable = jsGenerator.define("tempVariable").generate();

        assertThat(tempVariable, is("var tempVariable;"));
    }

    @Test
    public void define_more_than_one_variable() throws Exception {
        String tempVariable = jsGenerator.define("tempVariable").define("otherVariable").generate();

        assertThat(tempVariable, is("var tempVariable;\nvar otherVariable;"));
    }

    @Test
    public void assign_variable() throws Exception {
        String tempVariable = jsGenerator.assign("tempVariable", "'123'").generate();

        assertThat(tempVariable, is("tempVariable = '123';"));
    }

    @Test
    public void assign_more_than_one_variable() throws Exception {
        String tempVariable = jsGenerator.assign("tempVariable", "'123'").assign("other", "234").generate();

        assertThat(tempVariable, is("tempVariable = '123';\nother = 234;"));
    }
}