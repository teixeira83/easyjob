package com.easyjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyjob.model.Developer;

public interface Developers extends JpaRepository<Developer, Long> {

}
