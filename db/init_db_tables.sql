-- DROP TABLE public.users;
-- DROP TABLE public.roles;
-- DROP TABLE public.users_roles;

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL,
    password character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;

INSERT INTO public.users(
	id, password, username)
	VALUES (1, '$2a$10$Z9RnqLAxSanorLe6Cc.JgOZr42ui0gVGbQ2oqjYCgKCUOqktgw9le', 'admin');
	
INSERT INTO public.users(
	id, password, username)
	VALUES (2, '$2a$10$4QGsEF2a7X.DpEFncbVH/OdSrlRM3UXmivON5EJbkxicO/0PRQCH6', 'user');

CREATE TABLE IF NOT EXISTS public.roles
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT roles_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.roles
    OWNER to postgres;
	
INSERT INTO public.roles(
	id, name)
	VALUES (1, 'ROLE_ADMIN');
INSERT INTO public.roles(
	id, name)
	VALUES (2, 'ROLE_USER');

CREATE TABLE IF NOT EXISTS public.users_roles
(
    user_id bigint NOT NULL,
    roles_id bigint NOT NULL,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, roles_id),
    CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fka62j07k5mhgifpp955h37ponj FOREIGN KEY (roles_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.users_roles
    OWNER to postgres;
	
INSERT INTO public.users_roles(
	user_id, roles_id)
	VALUES (1, 1);
INSERT INTO public.users_roles(
	user_id, roles_id)
	VALUES (2, 2);
	