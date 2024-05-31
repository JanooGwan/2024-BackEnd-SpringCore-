package com.example.demo.repository;

import java.sql.PreparedStatement;
import java.util.List;

import com.example.demo.exceptions.InvalidReferenceException;
import com.example.demo.exceptions.ResourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Member;

@Repository
public class MemberRepositoryJdbc implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemberRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Member> memberRowMapper = (rs, rowNum) -> new Member(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("password")
    );

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("""
                SELECT id, name, email, password
                FROM member
                """, memberRowMapper);
    }

    @Override
    public Member findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("""
                                
                            SELECT id, name, email, password
                    FROM member
                    WHERE id = ?
                    """, memberRowMapper, id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("해당 회원을 찾을 수 없습니다.");
        }
    }

    @Override
    public Member insert(Member member) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement("""
                        INSERT INTO member (name, email, password) VALUES (?, ?, ?)
                        """, new String[]{"id"});
                ps.setString(1, member.getName());
                ps.setString(2, member.getEmail());
                ps.setString(3, member.getPassword());
                return ps;
            }, keyHolder);
            return findById(keyHolder.getKey().longValue());
        } catch (Exception e) {
            throw new InvalidReferenceException("해당 이메일로 가입한 회원이 존재합니다.");
        }
    }

    @Override
    public Member update(Member member) {
        try {
            jdbcTemplate.update("""
                    UPDATE member
                    SET name = ?, email = ?
                    WHERE id = ?
                    """, member.getName(), member.getEmail(), member.getId());
            return findById(member.getId());
        } catch (Exception e) {
            throw new InvalidReferenceException("해당 이메일로 가입한 회원이 존재합니다.");
        }

    }

    @Override
    public void deleteById(Long id) {
        try {
            jdbcTemplate.update("""
                    DELETE FROM member
                    WHERE id = ?
                    """, id);
        } catch (Exception e) {
            throw new InvalidReferenceException("해당 회원이 작성한 글이 있어 삭제할 수 없습니다.");
        }
    }

    @Override
    public Member findByEmail(String email) {
        return null;
    }

    @Override
    public boolean hasArticles(Long memberId) {
        return false;
    }
}
