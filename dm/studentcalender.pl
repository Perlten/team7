% Student calender!

% you can call all the below setups, and get the results :) but please try the following command:
% It checks wether students are busy on a day or not
% checkIfStudentIsBusy(24, monday, cs).
% checkIfStudentIsBusy(9, monday, cs).


% student name and id
student(jesper, 24).
student(perlt, 9).
student(due, 22).
student(lass, 14).
student(helsgaun, 99).
student(danielsan, 77).

class(cs, optional).
class(drama, optional).
class(finance, optional).
class(perlt-philosophy-class, required).

rooms(a).
rooms(b).
rooms(c).
rooms(d).

% dates are to see what day a class takes place
dates(monday).
dates(tuesday).
dates(wednesday).
dates(thursday).
dates(friday).

% args: studentId, className
studentInClass(24, cs).
studentInClass(9, cs).
studentInClass(22, cs).
studentInClass(14, drama).
studentInClass(22, drama).
studentInClass(99, finance).
studentInClass(77, finance).
studentInClass(24, perlt-philosophy-class).
studentInClass(9, perlt-philosophy-class).
studentInClass(22, perlt-philosophy-class).
studentInClass(14, perlt-philosophy-class).
studentInClass(99, perlt-philosophy-class).
studentInClass(77, perlt-philosophy-class).

% expelled students
studentExpelled(24).
studentExpelled(99).

% class and date relation
classDay(monday, cs).
classDay(tuesday, finance).
classDay(thursday, perlt-philosophy-class).
classDay(monday, drama).

% Calender function to check wether or not a student is busy with a subject on a day
checkIfStudentIsBusy(ID, DAY, CLASSNAME) :-
    studentHasClass(ID, CLASSNAME),
    dayChecker(DAY, CLASSNAME),
    not(studentExpelledChecker(ID)).

dayChecker(DAY, CLASSNAME) :-
    classDay(DAY, CLASSNAME).

studentHasClass(ID, CLASSNAME) :-
    studentInClass(ID, CLASSNAME).

studentExpelledChecker(ID) :-
    studentExpelled(ID).

