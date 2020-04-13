package cmd.lucas.feedpoll.dao;

import cmd.lucas.feedpoll.dao.contract.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class NewsDaoImpl implements NewsDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NewsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public JdbcTemplate jdbcTemplate() {
        return this.jdbcTemplate;
    }
}
