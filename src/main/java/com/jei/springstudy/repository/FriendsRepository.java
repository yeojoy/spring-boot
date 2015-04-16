package com.jei.springstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jei.springstudy.model.Friends;

public interface FriendsRepository extends JpaRepository<Friends, Long>{

	public Friends findByName(String name);
}
