create database hospital_management_system;

use hospital_management_system;

create table Patients(
p_id int primary key,
name varchar(20),
age int,
gender varchar(10)
);

create table Doctors(
d_id int primary key,
name varchar(10),
specialization varchar(10)
);

create table Appointments(
appointment_id int primary key,
p_id int,
d_id int,
appointment_date datetime default now(),
foreign key (p_id) references Patients (p_id),
foreign key (d_id)references Doctors (d_id)
);

insert into Doctors values(1, 'Adeela', 'Cardio');

select * from patients;
select * from doctors;
select * from appointments;