CREATE DATABASE IF NOT EXISTS apptarefas;

USE apptarefas;

CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

create table tasks (
    task_id bigint not null auto_increment,
    completed bit not null,
    created_at date,
    description varchar(255),
    due_date date,
    task_name varchar(255) not null,
    user_id bigint not null,
    primary key (task_id)
);

select * from user;

select * from tasks;