package com.easyjob.controller;

import com.easyjob.model.Permission;
import com.easyjob.model.Student;
import com.easyjob.model.Teacher;
import com.easyjob.model.Usuario;
import com.easyjob.repository.PermissionRepository;
import com.easyjob.repository.Students;
import com.easyjob.repository.TeacherRepository;
import com.easyjob.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/register")
public class RestRegisterController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    Students studentRepository;

    @Autowired
    TeacherRepository teacherRepository;


    //Para inserir o usuário basta fazer uma requisição a
    //http://localhost:8080/rest/register
    //enviando como parametro role, category e nome
    //ex: role: ADMIN, category: student, nome: Tulio
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Usuario u,
                                    @RequestParam("role") String role,
                                    @RequestParam("category") String category,
                                    @RequestParam("name") String nome){

        if(category.equalsIgnoreCase("student")){

            u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
            usuarioRepository.save(u);

            Student s = new Student();
            Usuario userAfterSave = usuarioRepository.findByUsername(u.getUsername());

            List<Permission> permissionList = setRole(role);
            u.setPermissoes(permissionList);

            s.setName(nome);
            s.setUsuario(userAfterSave);
            studentRepository.save(s);
            return new ResponseEntity<Student>(s, HttpStatus.CREATED);
        }else{
            u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
            usuarioRepository.save(u);
            Teacher t = new Teacher();
            Usuario userAfterSave = usuarioRepository.findByUsername(u.getUsername());
            Permission p = permissionRepository.findByNome(role);
            List<Permission> permissionList = setRole(role);
            u.setPermissoes(permissionList);
            t.setName(nome);
            t.setUsuario(userAfterSave);
            teacherRepository.save(t);
            return new ResponseEntity<Teacher>(t, HttpStatus.CREATED);
        }

    }



    public List<Permission> setRole(String role){

        Permission p = permissionRepository.findByNome(role);
        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(p);
        return permissionList;
    }




}
