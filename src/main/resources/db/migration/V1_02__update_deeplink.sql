
alter table deeplink
    add column md5hash varchar(64);

create unique index deeplink_md5hash on deeplink(md5hash);
