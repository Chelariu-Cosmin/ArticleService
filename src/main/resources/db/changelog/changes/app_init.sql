-- DROP DATABASE IF EXISTS "article";

create table articleBD.articles
(
    article_id              bigserial,
    article_category_type   character varying(20)  not null,
    article_description     character varying(255) not null,
    article_name            character varying(100) not null,
    article_price           numeric(19, 2)         not null,
    article_unit_of_measure character varying(20)  not null,
    article_url             character varying(255)
);

alter table ONLY articleBD.articles
    add constraint article_param_pkey primary key (article_id);
