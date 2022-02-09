create table [customer]
(
    uuid UNIQUEIDENTIFIER not null,
    name NVARCHAR(254)    not null

        CONSTRAINT [pk_customer]PRIMARY KEY (uuid)
);

create table [user]
(
    uuid UNIQUEIDENTIFIER not null,
    name NVARCHAR(254)    not null

        CONSTRAINT [pk_user]PRIMARY KEY (uuid)
);

CREATE TABLE [DATABASE_CONNECTION]
(
    tenant_uuid UNIQUEIDENTIFIER,
    url         NVARCHAR(1024),
    password    NVARCHAR(256),
    username    NVARCHAR(256)
)
