package com.full_monkey.servicios;

import com.full_monkey.entidades.Carrito;
import com.full_monkey.entidades.Compra;
import com.full_monkey.entidades.Perfil;
import com.full_monkey.entidades.Usuario;
import com.full_monkey.enums.Role;
import com.full_monkey.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepositorio;

    @Autowired
    private PerfilServicio ps;

    @Transactional
    public Usuario registroUsuario(String username, String password, String nombre, String apellido, Long dni, Date nacimiento, String email, String domicilio, String fotoPerfil, List<Compra> historial, Carrito pendiente) throws Exception {
        validator(username, password, email);
        Usuario u = new Usuario();
        u.setUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        u.setPassword(encoder.encode(password));
        Perfil p = ps.crearPerfil(nombre, apellido, historial, pendiente, dni, nacimiento, email, domicilio, fotoPerfil);
        u.setPerfil(p);
        u.setRole(Role.USER);
        return usuarioRepositorio.save(u);
    }

    @Transactional
    public Usuario modifUsuario(String id, String username, String password, String perfilid, String nombre, String apellido, Long dni, Date nacimiento, String email, String domicilio, String fotoPerfil) throws Exception {
        Usuario u = usuarioRepositorio.getById(id);
        validatorModif(u, username, password, email);
        if (u == null) {
            throw new Exception("No Existe Usuario con ese id");
        }
        Perfil p = ps.modifPerfil(perfilid, nombre, apellido, dni, nacimiento, email, domicilio, fotoPerfil);
        u.setPerfil(p);
        u.setUsername(username);
        return usuarioRepositorio.save(u);
    }

    @Transactional(readOnly = true)
    public Usuario findById(String id) {
        return usuarioRepositorio.getById(id);
    }

    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return usuarioRepositorio.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Usuario findByEmail(String email) throws Exception {
        if (usuarioRepositorio.findByEmail(email) == null) {
            throw new Exception("No existe usuario con ese email");
        }
        return usuarioRepositorio.findByEmail(email);
    }

    @Transactional
    public void cambiarRol(String id) throws Exception {

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();

            if (usuario.getRole().equals(Role.USER)) {

                usuario.setRole(Role.ADMIN);

            } else if (usuario.getRole().equals(Role.ADMIN)) {
                usuario.setRole(Role.USER);
            }
        }
    }

    public void validator(String username, String password, String email) throws Exception {
        if (findByUsername(username) != null) {
            throw new Exception("");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new Exception("");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new Exception("");
        }
        if (password.length() < 8) {
            throw new Exception("");
        }
        if (findByEmail(email) != null) {
            throw new Exception("");
        }
    }

    public void validatorModif(Usuario u, String username, String password, String email) throws Exception {
        if (u.getUsername().equals(username)) {
            if (password == null || password.trim().isEmpty()) {
                throw new Exception("");
            }
            if (password.length() < 8) {
                throw new Exception("");
            }
        } else {
            if (findByUsername(username) != null) {
                throw new Exception("");
            }
            if (username == null || username.trim().isEmpty()) {
                throw new Exception("");
            }
            if (password == null || password.trim().isEmpty()) {
                throw new Exception("");
            }
            if (password.length() < 8) {
                throw new Exception("");
            }
        }

        if (!u.getPerfil().getEmail().equals(email)) {
            if (findByEmail(email) != null) {
                throw new Exception("");
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByUsername(username);
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + usuario.getRole());
            permisos.add(p1);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);

            User user = new User(usuario.getUsername(), usuario.getPassword(), permisos);
            return user;

        } else {
            return null;
        }
    }
}
