create table
    tb_course (
        creation_date timestamp(6),
        inactivation_date timestamp(6),
        id uuid not null,
        instructor_id uuid not null,
        code varchar(255) unique,
        description TEXT,
        name varchar(255),
        status varchar(255) check (status in ('ACTIVE', 'INACTIVE')),
        primary key (id)
    );

create table
    tb_course_feedback (
        grade integer,
        feedback_date timestamp(6),
        course_id uuid not null,
        id uuid not null,
        student_id uuid not null,
        reason varchar(255),
        primary key (id),
        unique (student_id, course_id)
    );

create table
    tb_enrollment (
        enrollment_date timestamp(6),
        course_id uuid not null,
        id uuid not null,
        student_id uuid not null,
        primary key (id),
        unique (student_id, course_id)
    );

create table
    tb_user (
        creation_date timestamp(6),
        id uuid not null,
        email varchar(255) unique,
        name varchar(255),
        password varchar(255),
        role varchar(255) check (role in ('STUDENT', 'INSTRUCTOR', 'ADMIN')),
        username varchar(255) unique,
        primary key (id)
    );

alter table if exists tb_course add constraint FK786svhw5n35xyvh60gyh5it11 foreign key (instructor_id) references tb_user;

alter table if exists tb_course_feedback add constraint FKsntxrdu8whe1qh0rue2njfd67 foreign key (course_id) references tb_course;

alter table if exists tb_course_feedback add constraint FKq8vm8wdqco6o4g9j0xts22kjo foreign key (student_id) references tb_user;

alter table if exists tb_enrollment add constraint FKk130dj0tg3uu9wqfsu1hkwvok foreign key (course_id) references tb_course;

alter table if exists tb_enrollment add constraint FKje9fa1cntthyu9n51co18xnt9 foreign key (student_id) references tb_user;