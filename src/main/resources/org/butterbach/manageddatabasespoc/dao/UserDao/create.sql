INSERT INTO [user]
    (uuid, name)
OUTPUT
    inserted.uuid, inserted.name
values (:user.uuid,
        :user.name)