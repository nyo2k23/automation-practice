package com.cta.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringGenerator {

    private String generateEmailDomain(){
        return RandomStringUtils.random(5, true, false);
    }
    private String genetateEmailPrefix(){
        return RandomStringUtils.random(8, true, true);
    }


    public String generateValidEmail() {
        String prefix = genetateEmailPrefix();
        String domain = generateEmailDomain();
        return prefix + "@" + domain + ".com";
    }
}
