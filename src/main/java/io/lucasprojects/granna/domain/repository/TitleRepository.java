package io.lucasprojects.granna.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.lucasprojects.granna.domain.model.Title;

public interface TitleRepository extends JpaRepository<Title, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM tb_title " + 
    "WHERE due_date " +
    "BETWEEN TO_TIMESTAMP(:start, 'YYYY-MM-DD hh24:MI:SS') AND " + 
    "TO_TIMESTAMP(:end, 'YYYY-MM-DD hh24:MI:SS')")
    List<Title> getCashFlowByDueDate(
        @Param("start") String start,
        @Param("end") String end);
}
