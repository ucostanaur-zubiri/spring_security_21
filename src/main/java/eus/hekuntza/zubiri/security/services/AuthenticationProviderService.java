package eus.hekuntza.zubiri.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import eus.hekuntza.zubiri.security.model.CustomUserDetails;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {
  
  @Autowired
  private JpaUserDetailsService userDetailsService;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      String username = authentication.getName();
      String password = authentication.getCredentials().toString();

      CustomUserDetails user = userDetailsService.loadUserByUsername(username);
      return checkPassword(user, password, passwordEncoder);
  }

  private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder encoder) {
      if (encoder.matches(rawPassword, user.getPassword())) {
          return new UsernamePasswordAuthenticationToken(
              user.getUsername(),
              user.getPassword(),
              user.getAuthorities());
      } else {
          throw new BadCredentialsException("Bad credentials");
      }
  }

  @Override
  public boolean supports(Class<?> aClass) {
      return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
  }
  
}
