drop schema bicycle_rental;
create schema bicycle_rental;
use bicycle_rental;

create table images
(
    id         bigint(20) auto_increment,
    title      varchar(128) not null,
    image_link varchar(568) not null,
    constraint images_pk
        primary key (id)
);

create table store
(
    id      bigint(20) auto_increment,
    address varchar(255) not null,
    phone   varchar(13)  not null,
    constraint store_pk
        primary key (id)
);

create table role
(
    id   bigint(20) auto_increment,
    name varchar(128) not null,
    constraint role_pk
        primary key (id)
);
create unique index user_role_role_uindex
    on role (name);

create table user_status
(
    id   bigint(20) auto_increment,
    name varchar(128) not null,
    constraint status_pk
        primary key (id)
);
create unique index user_status_status_uindex
    on user_status (name);


create table users
(
    id        bigint(20) auto_increment,
    email     varchar(320)  not null,
    password  varchar(128)  not null,
    name      varchar(128)  not null,
    balance   numeric(9, 2) not null,
    status_id bigint(20)    not null,
    image_id  bigint(20)    not null,
    constraint user_id_pk
        primary key (id),
    constraint user_status_status_fk
        foreign key (status_id) references user_status (id)
            ON UPDATE cascade ON DELETE restrict,
    constraint user_image_fk
        foreign key (image_id) references images (id)
            ON UPDATE cascade ON DELETE no action
);
create unique index email_uindex
    on users (email);

create table bank_account
(
    iban            varchar(20)  not null,
    holder_name     varchar(128) not null,
    expiration_date varchar(15)  not null,
    cvv             int(4)       not null,
    user_id         bigint(20)   not null,
    constraint user_id_pk
        primary key (iban, user_id),
    constraint bank_account_user_fk
        foreign key (user_id) references users (id)
            ON UPDATE cascade ON DELETE cascade
);
create unique index bank_account_uindex
    on bank_account (iban);


create table user_role
(
    user_id bigint(20) not null,
    role_id bigint(20) not null,
    constraint user_role_pk
        primary key (user_id, role_id),
    constraint user_role_user_fk
        foreign key (user_id) references users (id)
            ON UPDATE cascade ON DELETE cascade,
    constraint user_role_role_fk
        foreign key (role_id) references role (id)
            ON UPDATE cascade ON DELETE cascade
);

create table producer
(
    id   bigint(20) auto_increment,
    name varchar(128) not null,
    constraint bicycle_producer_pk
        primary key (id)
);
create unique index bicycle_producer_uindex
    on producer (name);

create table type
(
    id   bigint(20) auto_increment,
    name varchar(128) not null,
    constraint type_pk
        primary key (id)
);
create unique index type_uindex
    on type (name);


create table product
(
    id             bigint(20) auto_increment,
    type_id        bigint(20)    not null,
    producer_id    bigint(20)    not null,
    model          varchar(128)  not null,
    price_per_hour numeric(4, 2) not null,
    image_id       bigint(20)    not null,
    constraint product_pk
        primary key (id),
    constraint product_image_fk
        foreign key (image_id) references images (id)
            ON UPDATE cascade ON DELETE no action,
    constraint product_producer_fk
        foreign key (producer_id) references producer (id)
            ON UPDATE cascade ON DELETE restrict,
    constraint product_type_fk
        foreign key (type_id) references type (id)
            ON UPDATE cascade ON DELETE restrict
);

create table inventory
(
    id         bigint(20) auto_increment,
    product_id bigint(20) not null,
    store_id   bigint(20) not null,
    constraint inventory_pk
        primary key (id),
    constraint inventory_product_fk
        foreign key (product_id) references product (id)
            ON UPDATE cascade ON DELETE cascade,
    constraint inventory_store_fk
        foreign key (store_id) references store (id)
            ON UPDATE cascade on DELETE cascade
);
create unique index bicycle_uindex
    on inventory (product_id);


create table rental_status
(
    id   bigint(20) auto_increment,
    name varchar(128) not null,
    constraint type_pk
        primary key (id)
);
create unique index type_uindex
    on rental_status (name);


create table rental
(
    id               bigint(20) auto_increment,
    user_id          bigint(20) not null,
    rental_date      datetime   not null,
    return_date      datetime,
    total            int        not null,
    status_id        bigint(20) not null,
    constraint rental_pk
        primary key (id),
    constraint rental_user_fk
        foreign key rental (user_id) references users (id)
            ON UPDATE cascade ON DELETE restrict,
    constraint rental_rental_status_fk
        foreign key rental (status_id) references rental_status (id)
            ON UPDATE cascade ON DELETE restrict
);

create table rental_products
(
    inventory_id bigint(20) not null,
    rental_id    bigint(20) not null,
    product_qty  int        not null,
    constraint rental_products_pk
        primary key (inventory_id, rental_id),
    constraint rental_products_rental_fk
        foreign key rental_products (rental_id) references rental (id)
            ON UPDATE cascade ON DELETE cascade,
    constraint rental_inventory_fk
        foreign key rental_products (inventory_id) references inventory (id)
            ON UPDATE cascade ON DELETE cascade
);

create table payment
(
    id        bigint(20) auto_increment,
    iban      varchar(20)   not null,
    total     numeric(4, 2) not null,
    rental_id bigint(20)    not null,
    constraint payment
        primary key (id),
    constraint iban_payment_fk
        foreign key payment (iban) references bank_account (iban)
            ON UPDATE cascade ON DELETE no action,
    constraint payment_rental_fk
        foreign key payment (rental_id) references rental (id)
            ON UPDATE cascade ON DELETE no action
);

create table verification_token
(
    id           bigint(20) auto_increment,
    token        varchar(255) not null,
    created_date datetime     not null,
    user_id      bigint(20)   not null,
    constraint verification_token_id
        primary key verification_token (id),
    constraint verification_token_user_fk
        foreign key verification_token (user_id) references users (id)
            ON UPDATE cascade ON DELETE cascade
);
create unique index verification_token
    on verification_token (id);
