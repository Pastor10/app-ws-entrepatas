package app.ws.entrepatas.controller;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.RoleEntity;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.payload.JwtAuthenticationResponse;
import app.ws.entrepatas.payload.LoginRequest;
import app.ws.entrepatas.payload.UserSummary;
import app.ws.entrepatas.repository.RoleRepository;
import app.ws.entrepatas.repository.UsuarioRepository;
import app.ws.entrepatas.security.JwtTokenProvider;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.UsuarioService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/auth")
@Slf4j
@Api(description = "Recurso para Login y cambio de clave")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    AuthenticationManager authenticationManager;
    UsuarioRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    JwtTokenProvider tokenProvider;
    UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, UsuarioRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("LOGUEARSE CON: "+ loginRequest.getUsername() +" -"+ loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        logger.info("token" + jwt);


        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        UsuarioEntity modelExist = usuarioService.findById(principal.getId());

        if (!modelExist.getEstado()){
            throw  new ServiceException(ErrorCode.V018.getCode(), ErrorCode.V018.getMessage());
        }
        List<RoleEntity> roles = new ArrayList<>();
        if(modelExist!=null){
            roles = modelExist.getPerfil().getRoles();
        }
        JwtAuthenticationResponse resp = JwtAuthenticationResponse.builder()
                .accessToken(jwt)
                .user(UserSummary.builder()
                        .id(principal.getId())
                        .name(principal.getName())
                        .username(principal.getUsername())
                        .authorities(principal.getAuthorities())
                        .build())
                .tokenType("Bearer")
                .roles(roles)
                .build();

        return ResponseEntity.ok(resp);
    }

}
