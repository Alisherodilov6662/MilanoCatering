package com.example.service;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private final DataSource dataSource;

    public CommandLineAppStartupRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
    }
}
