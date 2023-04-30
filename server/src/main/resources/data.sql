INSERT INTO users (username) VALUES ('John');
INSERT INTO users (username) VALUES ('Jane');

INSERT INTO boards (title) VALUES ('FirstBoard');

INSERT INTO board_members (role, board_id, user_id) VALUES ('OWNER', 1, 1);
INSERT INTO board_members (role, board_id, user_id) VALUES ('MEMBER', 1, 2);

INSERT INTO lists (title, board_id) VALUES ('FirstList', 1);
INSERT INTO lists (title, board_id) VALUES ('SecondList', 1);

INSERT INTO cards (title, description, list_id) VALUES ('FirstCard', '1111', 1);
INSERT INTO cards (title, description, list_id) VALUES ('SecondCard', '2222', 1);
INSERT INTO cards (title, description, list_id) VALUES ('ThirdCard', '3333', 2);
INSERT INTO cards (title, description, list_id) VALUES ('FourthCard', '4444', 2);

INSERT INTO card_members (board_member_id, card_id) VALUES (1, 1);
INSERT INTO card_members (board_member_id, card_id) VALUES (1, 2);