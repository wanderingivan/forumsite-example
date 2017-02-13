package com.forumsite.test.validation;

import java.util.stream.IntStream;


public abstract class AbstractValidationTest {

    protected String testStringOfSize(int size){
        return IntStream.range(0, size)
                        .mapToObj(i->(char)i)
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }
}
