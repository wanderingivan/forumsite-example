package com.forumsite.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.forumsite.test.validation.*;

@RunWith(Suite.class)
@SuiteClasses({CommentValidationTests.class,
               UserValidationTests.class,
               ForumThreadValidationTests.class})
public class ValidationsSuiteTest {}
