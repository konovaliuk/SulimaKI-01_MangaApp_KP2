TRUNCATE TABLE mangaapp.manga,
    mangaapp.creator,
    mangaapp.user_role,
    mangaapp.users
RESTART IDENTITY
;
TRUNCATE TABLE mangaapp.manga;
TRUNCATE TABLE mangaapp.creator;
TRUNCATE TABLE mangaapp.user_role;
TRUNCATE TABLE mangaapp.users;

insert into mangaapp.role(name)
values ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_MODERATOR');

insert into mangaapp.users(email, nickname, password)
values ('default', 'noname', 'password');

INSERT INTO mangaapp.creator(name, description) VALUES ('sulima ivan', '');

insert into mangaapp.manga(manga_name, author_id, artist_id, release_year, translator_id, alternative_manga_name, add_datetime, update_datetime, approval_datetime, preview_image_path, is_approved)
VALUES ('Pandora hearts', 1, 1, 1000, 1, '', now(), now(), now(), 'path', false);


insert into mangaapp.user_role(user_id, role_id)
values (1, 2);

select * from mangaapp.users;

select * from mangaapp.user_role;

select * from mangaapp.manga;

SELECT r.*
FROM mangaapp.role r
JOIN mangaapp.user_role ur
ON r.id = ur.role_id
WHERE ur.user_id = 3;
