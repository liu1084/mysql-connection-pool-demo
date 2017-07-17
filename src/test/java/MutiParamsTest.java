import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jim on 2017/7/13.
 * This class is ...
 */
@Test
public class MutiParamsTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(MutiParamsTest.class);

	public static String replaceByMatcher(String xpath, Pattern xpathPattern) {
		Matcher matcher = xpathPattern.matcher(xpath);
		if (matcher.find()) {
			String paramName = matcher.group(1);
			if (StringUtils.isEmpty(paramName)) {
				LOGGER.error("parameter name:" + paramName + " not be found.");
			}

			ResourceBundle resourceBundle = ResourceBundle.getBundle("utf", new Locale("zh_CN"));

			xpath = matcher.replaceFirst(resourceBundle.getString(paramName));
		}
		return xpath;
	}

	@Test
	public void test() {
		String xpath = "${\u8DEF\u7531} - ${\u6B65\u9AA4} - ${eventLog}";
		Pattern xpathPattern = Pattern.compile("\\$\\{([a-zA-Z0-9\\u4e00-\\u9fa5]+)\\}");
		Matcher matcher = xpathPattern.matcher(xpath);
		try {
			int count = 0;
			while (matcher.find()) count++;
			for (int i = 0; i < count; count--) {
				xpath = replaceByMatcher(xpath, xpathPattern);
			}

			System.out.println(xpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
