package com.cta.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringGenerator {


    private String genDomain(){
        return RandomStringUtils.random(5, true, false);
    }

    private String genPrefix(){
        return RandomStringUtils.random(8, true, true);
    }



    public String generateEmail() {
        String prefix = genPrefix();
        String domain = genDomain();
        return prefix + "@" + domain + ".com";
    }
}
