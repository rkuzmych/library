insert into usr(id, active, usr_name,  password)
    values(1, true, 'admin', '1');

insert into user_role(user_id, roles)
    values(1, 'USER'), (1, 'ADMIN');