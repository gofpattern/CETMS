
INSERT INTO users(username,password,enabled)
VALUES ('aqib','aqib123', 1);
INSERT INTO users(username,password,enabled)
VALUES ('alex','123456', 1);

INSERT INTO user_roles (username, role)
VALUES ('aqib', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('aqib', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('alex', 'ROLE_USER');