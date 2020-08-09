package app.ws.entrepatas.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSummary {
    private Long id;
    private String username;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;
}
