package br.csi.Animo.service;


import br.csi.Animo.model.User;
import br.csi.Animo.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService  implements UserDetailsService{
    private final UserRepository repository;

    public AutenticacaoService(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User usuario = this.repository.findByEmail(email);
        if (usuario==null){
            throw new UsernameNotFoundException("Email ou senha incorretos");
        }else{
            UserDetails user = User.withUsername(usuario.getEmail()).password(usuario.getSenha())
                    .authorities(usuario.getPermissao()).build();
            return user;
        }
    }
}
