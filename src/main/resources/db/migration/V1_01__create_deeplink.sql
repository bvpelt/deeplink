create table deeplink
(
    id            bigint not null primary key,
    identificatie varchar(32), -- uuid
    version       varchar(12),
    created       timestamptz default current_timestamp,
    content       jsonb
);

create unique index deeplink_identificatie on deeplink (identificatie);
create index deeplink_version on deeplink (version);
create index deeplink_created on deeplink (created);