package uz.pdp.citypostservice.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uz.pdp.citypostservice.domain.dto.UserDto;
import uz.pdp.citypostservice.domain.dto.UserReadDto;
import uz.pdp.citypostservice.domain.entity.response.JwtTokenEntity;
import uz.pdp.citypostservice.exceptions.DataNotFoundException;
import uz.pdp.citypostservice.repository.token.JwtTokenRepository;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final RestTemplate restTemplate;
    private final JwtTokenRepository jwtTokenRepository;
    @Value("${services.user-service-url}")
    private String userURL;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(userURL + "/api/v1/get/user")
                .queryParam("username",username);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET,entity, UserDto.class).getBody();
    }

    public UserReadDto getUser(String username, Principal principal){
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(userURL + "/api/v1/get/user")
                .queryParam("username", username);
        HttpHeaders httpHeaders = new HttpHeaders();
        JwtTokenEntity jwtToken = jwtTokenRepository.findById(principal.getName()).orElseThrow(() -> new DataNotFoundException("Jwt not found!"));
        httpHeaders.add("Authorization","Bearer " + jwtToken.getToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                UserReadDto.class).getBody();
    }
}
