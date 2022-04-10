package ucsal.model;

import org.springframework.security.core.GrantedAuthority;

public enum AppUserRole implements GrantedAuthority {
  ROLE_ADMIN, ROLE_CLIENT, ROLE_COZINHA, ROLE_BRUNCH, ROLE_BAR, ROLE_PIZZAS, ROLE_BURGUES, ROLE_SOBREMESA, ROLE_LIMPEZA, ROLE_DESCARTAVEIS;

  public String getAuthority() {
    return name();
  }

}
