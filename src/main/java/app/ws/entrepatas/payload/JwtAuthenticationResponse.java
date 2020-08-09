package app.ws.entrepatas.payload;

import app.ws.entrepatas.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationResponse {

    private String accessToken;
    private String tokenType;
    private UserSummary user;
    private List<RoleEntity> roles;
}
