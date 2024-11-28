package es.daw.jakarta.services;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;


public interface LoginService {
    public Optional<String> getUserName(HttpServletRequest request);
}
