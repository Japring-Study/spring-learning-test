package cholog.configuration;

public class AuthenticationPrincipalArgumentResolver {
    private AuthService authService;

    public AuthenticationPrincipalArgumentResolver() {
        this.authService = authService;
    }

    public String findMemberName() {
        return authService.findMemberName();
    }

    public AuthService getAuthService() {
        return authService;
    }
}
