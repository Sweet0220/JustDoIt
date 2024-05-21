CREATE TABLE user(
	id bigint primary key auto_increment,
    email varchar(255),
    username varchar(255),
    password varchar(255),
    name varchar(255)
);

CREATE TABLE list(
	id bigint primary key auto_increment,
    name varchar(255),
    id_user bigint,
    FOREIGN KEY user_reference (id_user) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE,
    category varchar(255),
    priority int
);

CREATE TABLE task(
	id bigint primary key auto_increment,
    title varchar(255),
    description varchar(255),
    status varchar(255),
    id_list bigint,
    deadline date,
    start_date date,
    FOREIGN KEY list_reference (id_list) REFERENCES list(id) ON DELETE CASCADE ON UPDATE CASCADE
);