package com.forumsite.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.forumsite.test.web.*;

@RunWith(Suite.class)
@SuiteClasses({
    CreateUserPageTests.class,
    CreateThreadPageTests.class,
    UpdateThreadPageTests.class,
    UpdateUserPageTests.class,
    AddCommentPageTests.class,
    SearchPageTests.class
})
public class WebPageSuiteTest {

}
