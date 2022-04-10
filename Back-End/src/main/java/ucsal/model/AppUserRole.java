package ucsal.model;

import org.springframework.security.core.GrantedAuthority;

public enum AppUserRole implements GrantedAuthority {
  ROLE_GESTOR, ROLE_SOLICITANTE;

  public String getAuthority() {
    return name();
  }

}
