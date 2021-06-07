package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomValue {

    public String getString(Integer count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }
}
