package com.easyjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyjob.model.Aluno;

public interface Alunos extends JpaRepository<Aluno, Long> {

}
