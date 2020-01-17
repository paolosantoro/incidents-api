package com.numerico.api.incidents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numerico.api.incidents.security.JwtAccessTokenCustomizer;
import com.numerico.api.incidents.security.SecurityProperties;

@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnProperty(prefix = "rest.security", value = "enabled", havingValue = "true")
@Import({SecurityProperties.class})
public class SecurityConfig extends ResourceServerConfigurerAdapter {

  @Autowired
  private ResourceServerProperties resourceServerProperties;

  @Autowired
  private SecurityProperties securityProperties;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(resourceServerProperties.getResourceId());
//    resources.tokenServices(remoteTokenServices());
  }

//  @Bean
//  public RemoteTokenServices remoteTokenServices() {
//
//       final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//       remoteTokenServices.setCheckTokenEndpointUrl("https://54.229.183.193:8543/auth/realms/Numerico/protocol/openid-connect/token/introspect");
//       remoteTokenServices.setClientId("deploymentviewer-frontend");
//       remoteTokenServices.setClientSecret("d5e766c6-c691-441e-931f-3524d16cf29c");
//       JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//       String key = "-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiFgu7cnvDrrTD0ChCOKoJaa5w2fVZqNDv3vcJTeWVknG/rJRs+fYdl7ATiIbCntT6eEIBdVri9ZhyecvktkC+bwse4EIN/VKTwu5HFkB6nNRQaHeTLxvuIxfWvEaDHJpZDyMs7cJIPMxy6KU4CoYf78vJXjxEcKZ+Mk9fvqB1NNF4Xbp8orAjvLI1VRUibPCS6487RAzeb00hwhrm5QbtYQMxZbFh82G1jBpsOSFzNFsPTzihrGV21/NjL3QUTutoof7Jr7d12cp87knVrtorNjx1mGpUgZAJEytJwkOBJI0H8gJBXG5KeyK/xrltYqaU0aJbRuY4q0ucZt1Anu3ZQIDAQAB\n-----END PUBLIC KEY-----";
//       converter.setVerifierKey(key);
//       remoteTokenServices.setAccessTokenConverter(converter);
//       return remoteTokenServices;
//   }

  @Override
  public void configure(final HttpSecurity http) throws Exception {

    http.cors()
        .configurationSource(corsConfigurationSource())
        .and()
        .headers()
        .frameOptions()
        .disable()
        .and()
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers(securityProperties.getApiMatcher())
        .authenticated();

  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    if (null != securityProperties.getCorsConfiguration()) {
      source.registerCorsConfiguration("/**", securityProperties.getCorsConfiguration());
    }
    return source;
  }

  @Bean
  public JwtAccessTokenCustomizer jwtAccessTokenCustomizer(ObjectMapper mapper) {
    return new JwtAccessTokenCustomizer(mapper);
  }

//  @Bean
//  public OAuth2RestTemplate oauth2RestTemplate(OAuth2ProtectedResourceDetails details) {
//    OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(details);
//
//    //Prepare by getting access token once
//    oAuth2RestTemplate.getAccessToken();
//    return oAuth2RestTemplate;
//  }
}
