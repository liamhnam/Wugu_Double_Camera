package org.apache.log4j.spi;

public class DefaultRepositorySelector implements RepositorySelector {
    final LoggerRepository repository;

    public DefaultRepositorySelector(LoggerRepository loggerRepository) {
        this.repository = loggerRepository;
    }

    @Override
    public LoggerRepository getLoggerRepository() {
        return this.repository;
    }
}
