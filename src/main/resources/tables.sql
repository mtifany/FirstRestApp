create table Sensor(
                       id int primary key generated always as identity,
                       name varchar(100) not null unique
);

create table Measurement(
                            id int primary key generated always as identity,
                            value float not null,
                            raining boolean not null,
                            created_at timestamp not null,
                            sensor varchar(100) references Sensor(name)
);