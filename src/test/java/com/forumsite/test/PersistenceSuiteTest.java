package com.forumsite.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.forumsite.test.persistence.*;

@RunWith(Suite.class)
@SuiteClasses({ForumThreadPersistenceTests.class,
               UserPersistenceTests.class,
               CommentPersistenceTests.class})
public class PersistenceSuiteTest {

}
