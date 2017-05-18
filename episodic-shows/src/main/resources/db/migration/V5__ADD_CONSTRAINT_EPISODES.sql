ALTER TABLE episodes
ADD UNIQUE (show_id,season_number,episode_number);