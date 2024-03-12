package eus.hekuntza.zubiri.filters;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class AuthenticationLogginFilter implements Filter {

  private final Logger logger = Logger.getLogger(AuthenticationLogginFilter.class.getName());

  @Autowired
  SecurityContext securityContext;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    if (securityContext != null) {
      logger.info("Successfully authenticated request with username " + securityContext.getAuthentication().getName());
    }
    chain.doFilter(request, response);
  }

}
