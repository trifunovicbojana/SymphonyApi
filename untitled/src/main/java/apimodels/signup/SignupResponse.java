package apimodels.signup;

public class SignupResponse {
   private String success;
   private Boolean isInfluencer;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Boolean getInfluencer() {
        return isInfluencer;
    }

    public void setInfluencer(Boolean influencer) {
        isInfluencer = influencer;
    }
}
