package e-learning;

public class FeignClientConfig {

    @Value("${web.flutter-wave-api-secret-key}")
    private String flutterWaveKey;

    @Bean
    public BearerTokenInterceptor bearerTokenInterceptor() {
        return new BearerTokenInterceptor(flutterWaveKey);
    }

    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder()
                .requestInterceptor(bearerTokenInterceptor());
    }

    @Bean
    public Decoder feignDecoder() {
        return (response, type) -> {
            String body = Util.toString(response.body().asReader(Util.UTF_8));
            JavaType javaType = TypeFactory.defaultInstance().constructType(type);
            return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(body, javaType);
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

    
}
