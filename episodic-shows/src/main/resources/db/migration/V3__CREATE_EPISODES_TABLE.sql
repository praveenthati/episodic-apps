CREATE TABLE episodes (
     id INT NOT NULL AUTO_INCREMENT,
     show_id INT NOT NULL,
     season_number INT NOT NULL,
     episode_number INT NOT NULL,
     PRIMARY KEY (id),
     INDEX episode_shows_ind (show_id),
    FOREIGN KEY (show_id)
        REFERENCES shows(id)
        ON DELETE CASCADE
);