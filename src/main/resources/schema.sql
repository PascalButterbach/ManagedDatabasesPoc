create table [testOne].[dbo].[customer]
(
    uuid UNIQUEIDENTIFIER not null,
    name NVARCHAR(254)    not null

   CONSTRAINT [pk_customer]PRIMARY KEY (uuid)
);