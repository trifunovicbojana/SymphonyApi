package stepDefinitions;

import apimodels.comments.GetCommentsResponse;
import apimodels.comments.NewCommentRequest;
import apimodels.comments.NewCommentResponse;
import apimodels.login.LoginRequest;
import apimodels.login.LoginResponse;
import apimodels.posts.NewPostRequest;
import apimodels.posts.NewPostResponse;
import apimodels.signup.SignupRequest;
import apimodels.signup.SignupResponse;
import com.google.gson.Gson;
import helpers.Endpoints;
import helpers.SpecBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import static helpers.Helpers.generateRandomString;
import static org.hamcrest.MatcherAssert.assertThat;


public class PartII {
    private Response response;

    SpecBuilder specBuilder;
    private SignupRequest signupRequest;
    private LoginRequest loginRequest;
    private LoginResponse loginResponse;
    private SignupResponse signupResponse;
    private NewPostRequest newPostRequest;
    private NewPostResponse newPostResponse;
    private NewCommentRequest newCommentRequest;
    private NewCommentResponse newCommentResponse;
    private GetCommentsResponse getCommentsResponse;


    public PartII(SpecBuilder specBuilder) {
        this.specBuilder = specBuilder;
    }

    @Given("user register new account with valid data email {string}, password {string}, firstName {string}, lastName {string}, username {string} and dateOfBirth {string}")
    public void userRegisterNewAccountWithValidData(String email, String password, String firstName, String lastName, String username, String dateOfBirth) {
        String rndString = generateRandomString();
        signupRequest = new SignupRequest(email + rndString, password, firstName, lastName, username + rndString, dateOfBirth);
    }

    @When("user registers new account")
    public void userRegistersNewAccount() {
        Gson gson = new Gson();
        String signUpRequestBody = gson.toJson(signupRequest);
        System.out.println(signUpRequestBody);
        response = RestAssured.given(SpecBuilder.requestSpec())
                .body(signUpRequestBody)
                .when().post(Endpoints.SIGNUP);
        signupResponse = gson.fromJson(response.getBody().asString(), SignupResponse.class);

    }

    @Then("user should receive a success response {string}")
    public void userShouldReceiveASuccessResponse(String successMessage) {
        response.then()
                .spec(SpecBuilder.responseSpec())
                .assertThat()
                .statusCode(201);
        Assert.assertEquals(successMessage, signupResponse.getSuccess());

    }

    @And("valid username and password are available")
    public void validUsernameAndPasswordAreAvailable() {
        loginRequest = new LoginRequest(signupRequest.getUsername(), signupRequest.getPassword());

    }

    @When("user logins")
    public void userLogins() {
        Gson gson = new Gson();
        String loginRequestBody = gson.toJson(loginRequest);
        System.out.println(loginRequestBody);
        response = RestAssured.given(SpecBuilder.requestSpec())
                .body(loginRequestBody)
                .when().post(Endpoints.LOGIN);
        loginResponse = gson.fromJson(response.getBody().asString(), LoginResponse.class);
    }

    @Then("user should receive success response with session token")
    public void userShouldReceiveSessionToken() {
        response.then()
                .spec(SpecBuilder.responseSpec())
                .assertThat()
                .statusCode(200);
        Assert.assertNotNull(loginResponse.getToken());
        Assert.assertEquals(loginResponse.getUser().getUsername(), loginRequest.getUsername());
        Assert.assertNotNull(loginResponse.getUser().getId());
    }


    @Given("registered user is logged in with username {string} and password {string}")
    public void registeredUserIsLoggedIn(String username, String password) {
        loginRequest = new LoginRequest(username, password);
        Gson gson = new Gson();
        String loginRequestBody = gson.toJson(loginRequest);
        response = RestAssured.given(SpecBuilder.requestSpec())
                .body(loginRequestBody)
                .when().post(Endpoints.LOGIN);
        loginResponse = gson.fromJson(response.getBody().asString(), LoginResponse.class);
    }

    @When("user adds new post with text {string}")
    public void userAddsNewPost(String newPostText) {
        newPostRequest = new NewPostRequest(newPostText);
        Gson gson = new Gson();
        String newPostRequestBody = gson.toJson(newPostRequest);
        response = RestAssured.given(SpecBuilder.requestSpec())
                .header("Authorization", "token " + loginResponse.getToken())
                .body(newPostRequestBody)
                .when().post(Endpoints.POST);
        newPostResponse = gson.fromJson(response.getBody().asString(), NewPostResponse.class);
    }

    @Then("user should receive a success response")
    public void userShouldReceiveASuccessResponse() {
        response.then()
                .spec(SpecBuilder.responseSpec())
                .assertThat()
                .statusCode(201);
        Assert.assertNotNull(newPostResponse.getId());
        Assert.assertNotNull(newPostResponse.getUser());
        Assert.assertEquals(newPostResponse.getUser().getUsername(), loginRequest.getUsername());
        Assert.assertEquals(newPostRequest.getText(), newPostResponse.getText());
    }

    @When("user adds new comment {string}")
    public void userAddsNewComment(String newComment) {
        this.newCommentRequest = new NewCommentRequest(newComment, newPostResponse.getId());
        Gson gson = new Gson();
        String newCommentRequestBody = gson.toJson(newCommentRequest);
        response = RestAssured.given(SpecBuilder.requestSpec())
                .header("Authorization", "token " + loginResponse.getToken())
                .body(newCommentRequestBody)
                .when().post(Endpoints.POST_COMMENTS);
        newCommentResponse = gson.fromJson(response.getBody().asString(), NewCommentResponse.class);
    }

    @Then("user should receive a success response with comment and user data")
    public void userShouldReceiveASuccessResponseWithCommentAndUserData() {
        response.then()
                .spec(SpecBuilder.responseSpec())
                .assertThat()
                .statusCode(201);
        Assert.assertNotNull(newCommentResponse.getId());
        Assert.assertEquals(newCommentRequest.getText(), newCommentResponse.getText());
        Assert.assertNotNull(newCommentResponse.getUser());
        Assert.assertNotNull(newCommentResponse.getId());
        Assert.assertEquals(newCommentResponse.getUser().getUsername(), loginRequest.getUsername());
    }

    @When("user gets comments from the post")
    public void userGetsCommentsFromThePost() {
        System.out.println(Endpoints.POST + newPostResponse.getId() + "/comments");
        response = RestAssured.given(SpecBuilder.requestSpec())
                .header("Authorization", "token " + loginResponse.getToken())
                .when().get(Endpoints.POST + newPostResponse.getId() + "/comments");
        Gson gson = new Gson();
        getCommentsResponse = gson.fromJson(response.getBody().asString(), GetCommentsResponse.class);

    }

    @Then("user should receive a success response with previously added comment")
    public void userShouldReceiveASuccessResponseWithPreviouslyAddedComment() {
        response.then()
                .spec(SpecBuilder.responseSpec())
                .assertThat()
                .statusCode(200);
        Assert.assertNotNull(getCommentsResponse.getCount());
        Assert.assertEquals(getCommentsResponse.getResults().get(0).getId(), newCommentResponse.getId());
        Assert.assertEquals(getCommentsResponse.getResults().get(0).getCreatedOn(), newCommentResponse.getCreatedOn());
        Assert.assertEquals(getCommentsResponse.getResults().get(0).getText(), newCommentResponse.getText());
    }


}

