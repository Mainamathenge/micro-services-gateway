package e-learning;

public class FeignErrrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 401 || response.status() == 403) {
            return new UnauthorizedException("Not Authorized");
        }

        return new ELearningExceptions(response.reason());
    }

    
}
