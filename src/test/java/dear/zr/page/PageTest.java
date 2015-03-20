package dear.zr.page;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PageTest {

    @Test
    public void get_page_name() throws Exception {
        Page page = new Page() {
            @Override
            public String name() {
                return "fake page name";
            }
        };

        assertThat(page.name(), is("fake page name"));
    }
}