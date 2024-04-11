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
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Alice', 'I just discovered a hidden cave full of ancient artifacts!', NOW()
FROM topics t WHERE t.title = 'Amazing World of Adventures'
UNION ALL
SELECT t.id, 1, 'Bob', 'Swimming with dolphins in the Caribbean was an unforgettable experience.', NOW()
FROM topics t WHERE t.title = 'Amazing World of Adventures'
UNION ALL
SELECT t.id, 1, 'Charlie', 'Hiking through the Amazon rainforest was both thrilling and challenging.', NOW()
FROM topics t WHERE t.title = 'Amazing World of Adventures'
UNION ALL
SELECT t.id, 1, 'David', 'Camping under the stars in the Sahara desert was a magical experience.', NOW()
FROM topics t WHERE t.title = 'Amazing World of Adventures'
UNION ALL
SELECT t.id, 1, 'Emma', 'Exploring ancient ruins in Greece felt like stepping back in time.', NOW()
FROM topics t WHERE t.title = 'Amazing World of Adventures';

-- Добавление сообщений к топику "Space Odyssey"
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Frank', 'The view of Earth from space is simply breathtaking.', NOW()
FROM topics t WHERE t.title = 'Space Odyssey'
UNION ALL
SELECT t.id, 1, 'Grace', 'Training at NASA has been a dream come true.', NOW()
FROM topics t WHERE t.title = 'Space Odyssey'
UNION ALL
SELECT t.id, 1, 'Henry', 'The International Space Station is an engineering marvel.', NOW()
FROM topics t WHERE t.title = 'Space Odyssey'
UNION ALL
SELECT t.id, 1, 'Ivy', 'Studying gravitational waves opens up new possibilities in astrophysics.', NOW()
FROM topics t WHERE t.title = 'Space Odyssey'
UNION ALL
SELECT t.id, 1, 'Jack', 'Mars colonization is closer than we think.', NOW()
FROM topics t WHERE t.title = 'Space Odyssey';


-- Добавление сообщений к топику "Deep Sea Explorers"
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Frank', 'The ocean''s depths are filled with mysteries waiting to be unraveled.', NOW()
FROM topics t WHERE t.title = 'Deep Sea Explorers'
UNION ALL
SELECT t.id, 1, 'Grace', 'Exploring the coral reefs feels like diving into a fantasy world.', NOW()
FROM topics t WHERE t.title = 'Deep Sea Explorers'
UNION ALL
SELECT t.id, 1, 'Henry', 'I''m mesmerized by the bioluminescent creatures we encounter on our dives.', NOW()
FROM topics t WHERE t.title = 'Deep Sea Explorers'
UNION ALL
SELECT t.id, 1, 'Ivy', 'The ocean''s biodiversity is awe-inspiring and worth protecting.', NOW()
FROM topics t WHERE t.title = 'Deep Sea Explorers'
UNION ALL
SELECT t.id, 1, 'Jack', 'Witnessing a sea turtle hatchling making its way to the ocean is a heartwarming sight.', NOW()
FROM topics t WHERE t.title = 'Deep Sea Explorers';

-- Добавление сообщений к топику "Secrets of Fairy Tales"
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Kevin', 'Every dive feels like stepping into a magical realm straight out of a fairy tale.', NOW()
FROM topics t WHERE t.title = 'Secrets of Fairy Tales'
UNION ALL
SELECT t.id, 1, 'Lily', 'Spotting a seahorse camouflaged among the sea grass is like finding a hidden treasure.', NOW()
FROM topics t WHERE t.title = 'Secrets of Fairy Tales'
UNION ALL
SELECT t.id, 1, 'Mike', 'Diving into shipwrecks feels like exploring the remnants of ancient civilizations.', NOW()
FROM topics t WHERE t.title = 'Secrets of Fairy Tales'
UNION ALL
SELECT t.id, 1, 'Nancy', 'Discovering a rare species of fish fills me with excitement and wonder.', NOW()
FROM topics t WHERE t.title = 'Secrets of Fairy Tales'
UNION ALL
SELECT t.id, 1, 'Olivia', 'Encountering a playful pod of dolphins makes every dive unforgettable.', NOW()
FROM topics t WHERE t.title = 'Secrets of Fairy Tales';


--- Добавление сообщений к топику "Dinosaur Discoveries"
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Paul', 'The study of dinosaurs opens up a window to our planet''s prehistoric past.', NOW()
FROM topics t WHERE t.title = 'Dinosaur Discoveries'
UNION ALL
SELECT t.id, 1, 'Rachel', 'I''m fascinated by the diversity of dinosaur species that once roamed the Earth.', NOW()
FROM topics t WHERE t.title = 'Dinosaur Discoveries'
UNION ALL
SELECT t.id, 1, 'Sam', 'Dinosaurs ignite our imagination and curiosity about the ancient world.', NOW()
FROM topics t WHERE t.title = 'Dinosaur Discoveries'
UNION ALL
SELECT t.id, 1, 'Tina', 'Exploring fossilized dinosaur footprints gives us clues about their behavior.', NOW()
FROM topics t WHERE t.title = 'Dinosaur Discoveries'
UNION ALL
SELECT t.id, 1, 'Victor', 'I dream of one day discovering a new species of dinosaur!', NOW()
FROM topics t WHERE t.title = 'Dinosaur Discoveries';

-- Добавление сообщений к топику "Fun Science Experiments"
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Wendy', 'I wish I could travel back in time to see real dinosaurs.', NOW()
FROM topics t WHERE t.title = 'Fun Science Experiments'
UNION ALL
SELECT t.id, 1, 'Xavier', 'Stegosaurus is my favorite dinosaur!', NOW()
FROM topics t WHERE t.title = 'Fun Science Experiments'
UNION ALL
SELECT t.id, 1, 'Yara', 'Did you know some dinosaurs could fly?', NOW()
FROM topics t WHERE t.title = 'Fun Science Experiments'
UNION ALL
SELECT t.id, 1, 'Zane', 'I want to be a paleontologist when I grow up.', NOW()
FROM topics t WHERE t.title = 'Fun Science Experiments'
UNION ALL
SELECT t.id, 1, 'Alice', 'Tyrannosaurus rex was the king of dinosaurs!', NOW()
FROM topics t WHERE t.title = 'Fun Science Experiments';

-- Добавление сообщений к топику "Cowboy Adventures"
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Alice', 'Riding through the Wild West makes me feel alive!', NOW()
FROM topics t WHERE t.title = 'Cowboy Adventures'
UNION ALL
SELECT t.id, 1, 'Bob', 'I love the thrill of chasing outlaws across the desert.', NOW()
FROM topics t WHERE t.title = 'Cowboy Adventures'
UNION ALL
SELECT t.id, 1, 'Charlie', 'Discovering a hidden gold mine was the highlight of my journey.', NOW()
FROM topics t WHERE t.title = 'Cowboy Adventures'
UNION ALL
SELECT t.id, 1, 'David', 'Encountering a herd of buffalo was a breathtaking sight.', NOW()
FROM topics t WHERE t.title = 'Cowboy Adventures'
UNION ALL
SELECT t.id, 1, 'Emma', 'I can''t wait to saddle up for another adventure!', NOW()
FROM topics t WHERE t.title = 'Cowboy Adventures';

-- Добавление сообщений к топику "Mysteries of Pirate Island"
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Frank', 'The tales of buried treasure and ghostly pirates add an air of mystery to the island.', NOW()
FROM topics t WHERE t.title = 'Mysteries of Pirate Island'
UNION ALL
SELECT t.id, 1, 'Grace', 'Exploring the abandoned pirate caves sends shivers down my spine.', NOW()
FROM topics t WHERE t.title = 'Mysteries of Pirate Island'
UNION ALL
SELECT t.id, 1, 'Henry', 'I''ve heard rumors of a haunted shipwreck off the coast.', NOW()
FROM topics t WHERE t.title = 'Mysteries of Pirate Island'
UNION ALL
SELECT t.id, 1, 'Ivy', 'The secret map leads to hidden treasure, but also to danger.', NOW()
FROM topics t WHERE t.title = 'Mysteries of Pirate Island'
UNION ALL
SELECT t.id, 1, 'Jack', 'Beware the curse of Blackbeard''s lost loot!', NOW()
FROM topics t WHERE t.title = 'Mysteries of Pirate Island';


-- Добавление сообщений к топику "Journey to the Planets"
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Kevin', 'I saw a meteor shower today!', NOW()
FROM topics t WHERE t.title = 'Journey to the Planets'
UNION ALL
SELECT t.id, 1, 'Lily', 'Space is vast and full of mysteries waiting to be explored.', NOW()
FROM topics t WHERE t.title = 'Journey to the Planets'
UNION ALL
SELECT t.id, 1, 'Mike', 'The beauty of Saturn''s rings never fails to amaze me.', NOW()
FROM topics t WHERE t.title = 'Journey to the Planets'
UNION ALL
SELECT t.id, 1, 'Nancy', 'I can''t wait to visit Mars and see it up close!', NOW()
FROM topics t WHERE t.title = 'Journey to the Planets'
UNION ALL
SELECT t.id, 1, 'Olivia', 'Exploring the moons of Jupiter would be an incredible adventure.', NOW()
FROM topics t WHERE t.title = 'Journey to the Planets';

-- Добавление сообщений к топику "Adventures in the Jungle"
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Paul', 'The jungle is teeming with life and secrets waiting to be discovered.', NOW()
FROM topics t WHERE t.title = 'Adventures in the Jungle'
UNION ALL
SELECT t.id, 1, 'Rachel', 'Every rustle in the leaves fills me with anticipation and excitement.', NOW()
FROM topics t WHERE t.title = 'Adventures in the Jungle'
UNION ALL
SELECT t.id, 1, 'Sam', 'Venturing deeper into the jungle feels like stepping into another world.', NOW()
FROM topics t WHERE t.title = 'Adventures in the Jungle'
UNION ALL
SELECT t.id, 1, 'Tina', 'The call of exotic birds echoes through the dense foliage.', NOW()
FROM topics t WHERE t.title = 'Adventures in the Jungle'
UNION ALL
SELECT t.id, 1, 'Victor', 'Discovering hidden ruins in the heart of the jungle is an explorer''s dream come true.', NOW()
FROM topics t WHERE t.title = 'Adventures in the Jungle';


-- Добавление сообщений к топику "Dino World"
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Alice', 'I''m thrilled to embark on this journey through Dino World!', NOW()
FROM topics t WHERE t.title = 'Dino World'
UNION ALL
SELECT t.id, 1, 'Bob', 'The roar of a T-Rex sends chills down my spine.', NOW()
FROM topics t WHERE t.title = 'Dino World'
UNION ALL
SELECT t.id, 1, 'Charlie', 'I discovered a fossilized footprint of a velociraptor!', NOW()
FROM topics t WHERE t.title = 'Dino World'
UNION ALL
SELECT t.id, 1, 'David', 'Stegosaurus is my favorite dinosaur species.', NOW()
FROM topics t WHERE t.title = 'Dino World'
UNION ALL
SELECT t.id, 1, 'Emma', 'I can''t wait to see the massive Diplodocus in person!', NOW()
FROM topics t WHERE t.title = 'Dino World';

-- Добавление сообщений к топику "Journey to Enigmatic Stars"
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Frank', 'Space exploration is the future of humanity.', NOW()
FROM topics t WHERE t.title = 'Journey to Enigmatic Stars'
UNION ALL
SELECT t.id, 1, 'Grace', 'I dream of being an astronaut someday.', NOW()
FROM topics t WHERE t.title = 'Journey to Enigmatic Stars'
UNION ALL
SELECT t.id, 1, 'Henry', 'I wonder if there are aliens out there.', NOW()
FROM topics t WHERE t.title = 'Journey to Enigmatic Stars'
UNION ALL
SELECT t.id, 1, 'Ivy', 'Black holes are fascinating but mysterious.', NOW()
FROM topics t WHERE t.title = 'Journey to Enigmatic Stars'
UNION ALL
SELECT t.id, 1, 'Jack', 'Exploring other planets would be an incredible adventure.', NOW()
FROM topics t WHERE t.title = 'Journey to Enigmatic Stars';


-- Добавление сообщений к топику "Secrets of the Magical Forest"
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Kevin', 'I saw a school of colorful fish today!', NOW() FROM topics t WHERE t.title = 'Secrets of the Magical Forest'
UNION ALL
SELECT t.id, 1, 'Lily', 'The ocean is full of wonders waiting to be discovered.', NOW() FROM topics t WHERE t.title = 'Secrets of the Magical Forest'
UNION ALL
SELECT t.id, 1, 'Mike', 'Coral reefs are like underwater cities.', NOW() FROM topics t WHERE t.title = 'Secrets of the Magical Forest'
UNION ALL
SELECT t.id, 1, 'Nancy', 'I can''t believe how deep the ocean is!', NOW() FROM topics t WHERE t.title = 'Secrets of the Magical Forest'
UNION ALL
SELECT t.id, 1, 'Olivia', 'I spotted a majestic whale on our dive.', NOW() FROM topics t WHERE t.title = 'Secrets of the Magical Forest';

-- Добавление сообщений к топику "Voyage to the Wondrous Ocean"
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Paul', 'Fairy tales always have a moral lesson hidden within them.', NOW() FROM topics t WHERE t.title = 'Voyage to the Wondrous Ocean'
UNION ALL
SELECT t.id, 1, 'Rachel', 'I love the magic and wonder of fairy tales.', NOW() FROM topics t WHERE t.title = 'Voyage to the Wondrous Ocean'
UNION ALL
SELECT t.id, 1, 'Sam', 'Dragons, witches, and princes - fairy tales have it all!', NOW() FROM topics t WHERE t.title = 'Voyage to the Wondrous Ocean'
UNION ALL
SELECT t.id, 1, 'Tina', 'Every fairy tale has a happy ending, doesn''t it?', NOW() FROM topics t WHERE t.title = 'Voyage to the Wondrous Ocean'
UNION ALL
SELECT t.id, 1, 'Victor', 'I still believe in fairy godmothers!', NOW() FROM topics t WHERE t.title = 'Voyage to the Wondrous Ocean';

-- Добавление сообщений к топику "Secrets of the Dragon Kingdom"
INSERT INTO messages (topic_id, user_id, author, text, CREATED_AT)
SELECT t.id, 1, 'Wendy', 'I wish I could travel back in time to see real dinosaurs.', NOW() FROM topics t WHERE t.title = 'Secrets of the Dragon Kingdom'
UNION ALL
SELECT t.id, 1, 'Xavier', 'Stegosaurus is my favorite dinosaur!', NOW() FROM topics t WHERE t.title = 'Secrets of the Dragon Kingdom'
UNION ALL
SELECT t.id, 1, 'Yara', 'Did you know some dinosaurs could fly?', NOW() FROM topics t WHERE t.title = 'Secrets of the Dragon Kingdom'
UNION ALL
SELECT t.id, 1, 'Zane', 'I want to be a paleontologist when I grow up.', NOW() FROM topics t WHERE t.title = 'Secrets of the Dragon Kingdom'
UNION ALL
SELECT t.id, 1, 'Alice', 'Tyrannosaurus rex was the king of dinosaurs!', NOW() FROM topics t WHERE t.title = 'Secrets of the Dragon Kingdom';

