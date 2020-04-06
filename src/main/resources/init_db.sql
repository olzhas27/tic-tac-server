drop table if exists users;
drop table if exists games;

CREATE TABLE if not exists "users" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"login"	TEXT NOT NULL UNIQUE,
	"status"	TEXT CHECK(status = 'none' or (status in ('authorized','pending','playing') and ip is not null and port is not null and token is not null)),
	"token"	TEXT UNIQUE,
	"ip"	TEXT,
	"port"	INTEGER
)

CREATE TABLE if not exists "games" (
	"session_id"	TEXT NOT NULL,
	"player_x"	INTEGER,
	"player_o"	INTEGER,
	"winner"	INTEGER CHECK(winner=0 OR winner in (player_x,player_o)),
	"status"	TEXT CHECK(status in ('WAITING_FOR_X','WAITING_FOR_O','COMPLETED')),
	"field_json"	TEXT NOT NULL,
	PRIMARY KEY("session_id"),
	FOREIGN KEY("player_x") REFERENCES "users"("id"),
	FOREIGN KEY("winner") REFERENCES "users"("id"),
	FOREIGN KEY("player_o") REFERENCES "users"("id")
)