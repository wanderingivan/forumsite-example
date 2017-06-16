# forumsite-example

## A basic forum site example built using [JSF](http://www.javaserverfaces.org/), [Hibernate](http://hibernate.org/), [H2 Database](http://www.h2database.com) and [MySql](http://www.mysql.com/) for [WildFly](http://wildfly.org/).
 
 
 
 **How to run**:
  1. Download repository
  2. Execute mvn wildfly:run -DskipTests
  5. Load localhost:8080/ForumSite on your favorite browser
  
  While wildfly can be ran in embedded mode for demonstration purposes,
  tests will use arquillian wildfly plugin in managed mode,
  hence the argument -Djboss.test.home=<path> is required.
  
  An additional argument -Ddriver.binary.loc is required if using chrome browser for selenium tests. 
  
  If you don't want to use preset threads and users add -Dbuild.preset.skip=true argument
     
  