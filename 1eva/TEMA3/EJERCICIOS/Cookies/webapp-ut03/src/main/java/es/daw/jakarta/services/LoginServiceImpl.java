package es.daw.jakarta.services;

import java.util.Optional;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public class LoginServiceImpl implements LoginService{

    @Override
    public Optional<String> getUserName(HttpServletRequest request) {

        // Forma 1: API Stream
        Cookie[] cookies = request.getCookies() != null ? request.getCookies() : new Cookie[0];
        //Cookie[] cookies = request.getCookies(); // problema con los null!

        return Arrays.stream(cookies)
                    .filter(c -> "username".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findAny();

            

        // Forma 2:
        // Cookie[] cookies = request.getCookies();
        // String nombre = null;

        // if (cookies != null) {
        //     for (Cookie cookie : cookies) {
        //         if ("username".equals(cookie.getName())) {
        //             nombre = cookie.getValue();
        //             return nombre;
        //         }
        //     }
        // }


    }
    
    
}
