INSERT INTO customer
    (uuid, name)
OUTPUT
    inserted.uuid, inserted.name
values (:customer.uuid,
        :customer.name)