package helpers;

public interface Endpoints {
String TESTING_ENVIRONMENT =  System.getProperty("environment", "https://randomlyapi.symphony.is/api");
String SIGNUP="/auth/signup/";
String USERS="/users/";
String LOGIN="/auth/login/";
String POST="/posts/";
String POST_COMMENTS="/post-comments/";

}
