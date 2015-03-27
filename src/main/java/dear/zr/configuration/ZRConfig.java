package dear.zr.configuration;

import java.io.IOException;
import java.util.Properties;

public final class ZRConfig {

    public static final String SPELL_CHECKING = "spell-check";

    private static Properties properties;

    public static final Properties properties() throws IOException {
        if (properties == null) {
            properties = new Properties();
            properties.load(ZRConfig.class.getClassLoader().getResourceAsStream("zr.properties"));
        }

        return properties;
    }
}
