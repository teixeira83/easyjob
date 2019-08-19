package com.easyjob.service;

import com.easyjob.model.Permission;
import com.easyjob.model.Usuario;
import com.easyjob.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDefatailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        Usuario u = usuarioRepository.findByUsername(username);
        return new User(u.getUsername(), u.getPassword(), authorities(u.getPermissoes()));
    }

    private List<GrantedAuthority> authorities(List<Permission> lista){
        List<GrantedAuthority> novaLista = new ArrayList<>();
        for(Permission p : lista){
            novaLista.add(new SimpleGrantedAuthority("ROLE_" + p.getNome()));
        }
        return novaLista;
    }
}
