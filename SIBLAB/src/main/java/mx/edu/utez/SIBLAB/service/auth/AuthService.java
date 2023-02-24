package mx.edu.utez.SIBLAB.service.auth;

import mx.edu.utez.SIBLAB.models.user.User;
import mx.edu.utez.SIBLAB.models.user.UserRepository;
import mx.edu.utez.SIBLAB.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
@Transactional
@Service("userDetailsService")
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException(email);
        }

        var roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(user.getEmail()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                roles
        );
    }
}
