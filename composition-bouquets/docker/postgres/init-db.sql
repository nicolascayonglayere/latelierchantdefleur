-- keycloak
CREATE USER keycloak WITH ENCRYPTED PASSWORD 'keycloak';
CREATE DATABASE keycloak;
GRANT ALL PRIVILEGES ON DATABASE keycloak TO keycloak;

-- chantdefleur
CREATE USER chantdefleur WITH ENCRYPTED PASSWORD 'postgres';
CREATE DATABASE chantdefleur;
GRANT ALL PRIVILEGES ON DATABASE chantdefleur TO chantdefleur;
