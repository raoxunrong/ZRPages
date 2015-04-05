package dear.zr.utils;

import dear.zr.domain.Range;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HtmlContentTest {
    @Test
    public void should_generate_js_script(){
        String testString = "A sentence with a errer";
        Range range = mock(Range.class);
        Range range1 = mock(Range.class);
        List<Range> rangeList = new ArrayList<Range>();
        rangeList.add(range);
        rangeList.add(range1);
        when(range.getStartColumn()).thenReturn(16);
        when(range.getEndColumn()).thenReturn(17);

        when(range1.getStartColumn()).thenReturn(18);
        when(range1.getEndColumn()).thenReturn(23);



        String newText = HtmlContent.addTag(testString, rangeList);
        assertThat(newText, is("A sentence with <span style='background-color:red'>a</span> <span style='background-color:red'>errer</span>"));

    }
}
