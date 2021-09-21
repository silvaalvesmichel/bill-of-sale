package br.com.bill.of.sale.service;
import br.com.bill.of.sale.domain.User;
import br.com.bill.of.sale.repository.UserRepository;
import br.com.bill.of.sale.security.UserSS;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if(Objects.isNull(user)){
            throw new UsernameNotFoundException(email);
        }

        return UserSS.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword()).build();

    }
}
