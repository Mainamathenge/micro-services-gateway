public class BearerTokenInterceptor {

    private final String bearerToken;

    public BearerTokenInterceptor(String token) {
        this.bearerToken = token;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "Bearer " + bearerToken);
    }
    
}
