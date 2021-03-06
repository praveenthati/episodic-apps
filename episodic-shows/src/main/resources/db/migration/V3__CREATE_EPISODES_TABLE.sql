CREATE TABLE episodes (
     id BIGINT NOT NULL AUTO_INCREMENT,
     show_id BIGINT NOT NULL,
     season_number BIGINT NOT NULL,
     episode_number BIGINT NOT NULL,
     PRIMARY KEY (id),
     INDEX episode_shows_ind (show_id),
    FOREIGN KEY (show_id)
        REFERENCES shows(id)
        ON DELETE CASCADE
);