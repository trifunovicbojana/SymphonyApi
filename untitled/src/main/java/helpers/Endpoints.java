package helpers;

public interface Endpoints {
String TESTING_ENVIRONMENT =  System.getProperty("environment", "https://randomlyapi.symphony.is/api");
String SIGNUP=TESTING_ENVIRONMENT+"/auth/signup/";
String USERS=TESTING_ENVIRONMENT+"/users/";
String LOGIN=TESTING_ENVIRONMENT+"/auth/login/";
String POST=TESTING_ENVIRONMENT+"/posts/";
String POST_COMMENTS=TESTING_ENVIRONMENT+"/post-comments/";

}
