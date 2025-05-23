package org.salamandrio.skate.infrastructure.configuration;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration {

  @Autowired
  private DataSource dataSource;

  @PostConstruct
  public void skateFlywayConfiguration() {
    new Flyway(new FluentConfiguration()
      .dataSource(dataSource)
      .schemas("migrations")
      .defaultSchema("migrations")
      .table("skate_migrations_history")
      .locations("db/migration-skate")
    ).migrate();
  }
}
