create schema if not exists stisys;
use stisys;
create table if not exists STUDENT
	(studentID CHAR(6) NOT NULL,
    firstName VARCHAR(15) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    dob DATE,
    programID INT,
    
    primary key (studentID));
    
create table if not exists PROGRAM
	(programID INT NOT NULL,
	programName VARCHAR(30) NOT NULL,
    requiredCPs INT NOT NULL,
    
    primary key (programID));
    
create table if not exists COURSE
	(courseID INT NOT NULL,
    courseName VARCHAR(3) NOT NULL,
    courseDescription VARCHAR(30),
    creditPoints INT NOT NULL,
    programID INT,
    
    primary key (courseID),
    foreign key (programID) references PROGRAM(programID));
    
create table if not exists ATTEMPTS
	(studentID CHAR(6) NOT NULL,
    courseID INT NOT NULL,
    attemptYear CHAR(4) NOT NULL,
    term INT NOT NULL,
    grade INT NOT NULL,
    
    primary key (courseID, studentID, attemptYear, term),
    foreign key (courseID) references COURSE(courseID),
    foreign key (studentID) references STUDENT(studentID));

create table if not exists PREREQUISITE 
	(advancedCourseID INT NOT NULL,
    prerequisiteCourseID INT NOT NULL,
    
    primary key(advancedCourseID, prerequisiteCourseID),
    foreign key (advancedCourseID) references COURSE(courseID),
    foreign key (prerequisiteCourseID) references COURSE(courseID));
    
alter table STUDENT
	add constraint foreign key (programID) references Program(programID);

insert into PROGRAM
values (1, 'Information Engineering', 120);
insert into PROGRAM
values (2, 'Renewable Energies', 110);

insert into STUDENT
values ('123456', 'John', 'Wayne', '1998-05-11', 1);
insert into STUDENT
values ('234567', 'Anna', 'Meyer', '1999-02-13', 1);

insert into COURSE
values (4, 'MA1', 'Mathematics 1', 8, 1);
insert into COURSE
values (9, 'MA2', 'Mathematics 2', 8, 1);
insert into COURSE
values (13, 'SS1', 'Signals and Systems 1', 6, 1);
insert into COURSE
values (15, 'DB', 'Databases', 6, 1);

insert into PREREQUISITE
values (9, 4);
insert into PREREQUISITE
values (13, 9);
insert into PREREQUISITE
values (13, 4);

insert into ATTEMPTS
values ('123456', 4, '2021', 1, 7);
insert into ATTEMPTS
values ('123456', 9, '2021', 2, 9);
insert into ATTEMPTS
values ('123456', 13, '2022', 1, 3);
insert into ATTEMPTS
values ('123456', 13, '2022', 2, 6);

select firstName, lastName from Student where programID = 1;

select distinct courseName from Course, Prerequisite where courseID = advancedCourseID;

select sum(creditPoints) 
from Course, Attempts 
where attempts.courseID = Course.courseID 
	AND attempts.studentID = 123456 
	AND grade > 4;
  
alter table attempts
	add constraint SID
    foreign key (studentID) references Student(studentID)
    on delete cascade;
alter table attempts
	add constraint CID
    foreign key (courseID) references Course(courseID)
    on delete cascade;
    
delete from student
	where studentID = 123456;