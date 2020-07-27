package com.boxfort.demo.dao;

import com.boxfort.demo.Entities.RaffleMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RaffleMemberDAO extends JpaRepository<RaffleMember, Long> {
    Set<RaffleMember> findBySelection(String selection);
}
