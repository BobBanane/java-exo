package fr.eni.demowebservice.security.services;

import fr.eni.demowebservice.dal.UserInfoRepository;
import fr.eni.demowebservice.entities.AuthenticationRequest;
import fr.eni.demowebservice.entities.AuthenticationResponse;
import fr.eni.demowebservice.entities.UserInfo;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private UserInfoRepository userInfoRepository;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public AuthenticationResponse authenticate( AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPseudo(), request.getPassword()));
        UserInfo user = userInfoRepository.findById(request.getPseudo()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setToken(jwtToken);
        return authResponse;
    }

}