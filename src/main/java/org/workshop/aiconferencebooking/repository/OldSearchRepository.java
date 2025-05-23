package org.workshop.aiconferencebooking.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.workshop.aiconferencebooking.model.Talk;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OldSearchRepository {

    private final EntityManager em;
    private final DataSource dataSource;

    public OldSearchRepository(EntityManager em, DataSource dataSource) {
        this.em = em;
        this.dataSource = dataSource;
    }

    public List<Talk> searchTalk(String input) {
        var lowerInput = input.toLowerCase();
        var query = em.createNativeQuery("select * from Talk t where lower(description) like '%" + lowerInput + "%' OR lower(title) like '%" + lowerInput + "%'", Talk.class);
        var talks = (List<Talk>) query.getResultList();
        return talks;
    }
}
