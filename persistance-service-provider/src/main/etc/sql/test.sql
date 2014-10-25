create schema cemeteryDB;
use cemeteryDB;
create table test(
	id bigint auto_increment not null primary key,
	created_at date,
	updated_at date,
	content varchar(250)
);

insert into test(content) values("Proiect colectiv");