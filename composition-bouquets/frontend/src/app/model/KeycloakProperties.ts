export class KeycloakProperties {
  enabled: boolean;
  authServerUrl: string;
  realm: string;
  resource: string;
  requiredUserRole: string;
  // credentials: Credentials;
}

export class Credentials {
  secret: string;
}
