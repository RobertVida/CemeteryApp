package ro.InnovaTeam.cemeteryApp.controller.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import ro.InnovaTeam.cemeteryApp.UserDTO;
import ro.InnovaTeam.cemeteryApp.restClient.AuthenticationRestClient;

/**
 * Created by Catalin Sorecau on 12/9/2014.
 */
public class UserAuthenticationManager implements org.springframework.security.authentication.AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDTO userDTO = AuthenticationRestClient.checkCredentials(username, password);
        if (userDTO != null) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(), userDTO.getId());
        }
        throw new BadCredentialsException("Bad Credentials");
    }
}
