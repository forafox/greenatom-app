-- Создание топиков
INSERT INTO topics (title, user_id) VALUES
                                        ('Amazing World of Adventures', 1),
                                        ('Space Odyssey', 1),
                                        ('Deep Sea Explorers', 1),
                                        ('Secrets of Fairy Tales', 1),
                                        ('Dinosaur Discoveries', 1),
                                        ('Fun Science Experiments', 1),
                                        ('Cowboy Adventures', 1),
                                        ('Mysteries of Pirate Island', 1),
                                        ('Journey to the Planets', 1),
                                        ('Adventures in the Jungle', 1),
                                        ('Dino World', 1),
                                        ('Journey to Enigmatic Stars', 1),
                                        ('Secrets of the Magical Forest', 1),
                                        ('Voyage to the Wondrous Ocean', 1),
                                        ('Secrets of the Dragon Kingdom', 1);

-- Добавление сообщений к топику "Amazing World of Adventures"
INSERT INTO messages (topic_id, user_id, author, text,
                      CREATED_AT)
SELECT t.id, 1, 'Alice', 'This adventure is so exciting!', NOW() FROM topics t WHERE t.title = 'Amazing World of Adventures'
UNION ALL
SELECT t.id, 1, 'Bob', 'I wish I could explore space like astronauts!', NOW() FROM topics t WHERE t.title = 'Amazing World of Adventures'
UNION ALL
SELECT t.id, 1, 'Charlie', 'I found a hidden treasure in the jungle!', NOW() FROM topics t WHERE t.title = 'Amazing World of Adventures'
UNION ALL
SELECT t.id, 1, 'David', 'Dinosaurs are awesome creatures!', NOW() FROM topics t WHERE t.title = 'Amazing World of Adventures'
UNION ALL
SELECT t.id, 1, 'Emma', 'I''m ready for another adventure!', NOW() FROM topics t WHERE t.title = 'Amazing World of Adventures';

-- Добавление сообщений к топику "Space Odyssey"
INSERT INTO messages (topic_id, user_id, author, text,
                      CREATED_AT)
SELECT t.id, 1, 'Frank', 'Space exploration is the future of humanity.', NOW() FROM topics t WHERE t.title = 'Space Odyssey'
UNION ALL
SELECT t.id, 1, 'Grace', 'I dream of being an astronaut someday.', NOW() FROM topics t WHERE t.title = 'Space Odyssey'
UNION ALL
SELECT t.id, 1, 'Henry', 'I wonder if there are aliens out there.', NOW() FROM topics t WHERE t.title = 'Space Odyssey'
UNION ALL
SELECT t.id, 1, 'Ivy', 'Black holes are fascinating but mysterious.', NOW() FROM topics t WHERE t.title = 'Space Odyssey'
UNION ALL
SELECT t.id, 1, 'Jack', 'Exploring other planets would be an incredible adventure.', NOW() FROM topics t WHERE t.title = 'Space Odyssey';

-- Добавление сообщений к топику "Deep Sea Explorers"
INSERT INTO messages (topic_id, user_id, author, text,
                      CREATED_AT)
SELECT t.id, 1, 'Kevin', 'I saw a school of colorful fish today!', NOW() FROM topics t WHERE t.title = 'Deep Sea Explorers'
UNION ALL
SELECT t.id, 1, 'Lily', 'The ocean is full of wonders waiting to be discovered.', NOW() FROM topics t WHERE t.title = 'Deep Sea Explorers'
UNION ALL
SELECT t.id, 1, 'Mike', 'Coral reefs are like underwater cities.', NOW() FROM topics t WHERE t.title = 'Deep Sea Explorers'
UNION ALL
SELECT t.id, 1, 'Nancy', 'I can''t believe how deep the ocean is!', NOW() FROM topics t WHERE t.title = 'Deep Sea Explorers'
UNION ALL
SELECT t.id, 1, 'Olivia', 'I spotted a majestic whale on our dive.', NOW() FROM topics t WHERE t.title = 'Deep Sea Explorers';

-- Добавление сообщений к топику "Secrets of Fairy Tales"
INSERT INTO messages (topic_id, user_id, author, text,
                      CREATED_AT)
SELECT t.id, 1, 'Paul', 'Fairy tales always have a moral lesson hidden within them.', NOW() FROM topics t WHERE t.title = 'Secrets of Fairy Tales'
UNION ALL
SELECT t.id, 1, 'Rachel', 'I love the magic and wonder of fairy tales.', NOW() FROM topics t WHERE t.title = 'Secrets of Fairy Tales'
UNION ALL
SELECT t.id, 1, 'Sam', 'Dragons, witches, and princes - fairy tales have it all!', NOW() FROM topics t WHERE t.title = 'Secrets of Fairy Tales'
UNION ALL
SELECT t.id, 1, 'Tina', 'Every fairy tale has a happy ending, doesn''t it?', NOW() FROM topics t WHERE t.title = 'Secrets of Fairy Tales'
UNION ALL
SELECT t.id, 1, 'Victor', 'I still believe in fairy godmothers!', NOW() FROM topics t WHERE t.title = 'Secrets of Fairy Tales';

-- Добавление сообщений к топику "Dinosaur Discoveries"
INSERT INTO messages (topic_id, user_id, author, text,
                      CREATED_AT)
SELECT t.id, 1, 'Wendy', 'I wish I could travel back in time to see real dinosaurs.', NOW() FROM topics t WHERE t.title = 'Dinosaur Discoveries'
UNION ALL
SELECT t.id, 1, 'Xavier', 'Stegosaurus is my favorite dinosaur!', NOW() FROM topics t WHERE t.title = 'Dinosaur Discoveries'
UNION ALL
SELECT t.id, 1, 'Yara', 'Did you know some dinosaurs could fly?', NOW() FROM topics t WHERE t.title = 'Dinosaur Discoveries'
UNION ALL
SELECT t.id, 1, 'Zane', 'I want to be a paleontologist when I grow up.', NOW() FROM topics t WHERE t.title = 'Dinosaur Discoveries'
UNION ALL
SELECT t.id, 1, 'Alice', 'Tyrannosaurus rex was the king of dinosaurs!', NOW() FROM topics t WHERE t.title = 'Dinosaur Discoveries';
