# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table mashi_category_word (
  id                        bigint not null,
  added_on                  timestamp,
  last_modified_on          timestamp,
  name                      varchar(255),
  abbreviation              varchar(255),
  constraint uq_mashi_category_word_name unique (name),
  constraint uq_mashi_category_word_abbreviat unique (abbreviation),
  constraint pk_mashi_category_word primary key (id))
;

create table mashi_example_word (
  id                        bigint not null,
  added_on                  timestamp,
  last_modified_on          timestamp,
  word_id                   bigint,
  description               varchar(255),
  constraint uq_mashi_example_word_descriptio unique (description),
  constraint pk_mashi_example_word primary key (id))
;

create table mashi_french_word (
  id                        bigint not null,
  added_on                  timestamp,
  last_modified_on          timestamp,
  precision                 varchar(255),
  plural                    varchar(255),
  origin_id                 bigint,
  type_id                   bigint,
  french_word               varchar(255) not null,
  constraint pk_mashi_french_word primary key (id))
;

create table mashi_image_word (
  id                        bigint not null,
  added_on                  timestamp,
  last_modified_on          timestamp,
  url                       varchar(255),
  caption                   varchar(255),
  constraint uq_mashi_image_word_url unique (url),
  constraint pk_mashi_image_word primary key (id))
;

create table mashi_mashi_word (
  id                        bigint not null,
  added_on                  timestamp,
  last_modified_on          timestamp,
  precision                 varchar(255),
  plural                    varchar(255),
  origin_id                 bigint,
  type_id                   bigint,
  mashi_word                varchar(255) not null,
  constraint pk_mashi_mashi_word primary key (id))
;

create table mashi_origin_word (
  id                        bigint not null,
  added_on                  timestamp,
  last_modified_on          timestamp,
  name                      varchar(255),
  abbreviation              varchar(255),
  constraint uq_mashi_origin_word_name unique (name),
  constraint uq_mashi_origin_word_abbreviatio unique (abbreviation),
  constraint pk_mashi_origin_word primary key (id))
;

create table mashi_abstract_timer (
  added_on                  timestamp,
  last_modified_on          timestamp)
;

create table mashi_type_word (
  id                        bigint not null,
  added_on                  timestamp,
  last_modified_on          timestamp,
  name                      varchar(255),
  abbreviation              varchar(255),
  constraint uq_mashi_type_word_name unique (name),
  constraint uq_mashi_type_word_abbreviation unique (abbreviation),
  constraint pk_mashi_type_word primary key (id))
;

create table mashi_user (
  email                     varchar(255) not null,
  added_on                  timestamp,
  last_modified_on          timestamp,
  first_name                varchar(255),
  last_name                 varchar(255),
  password                  varchar(255),
  constraint pk_mashi_user primary key (email))
;

create table mashi_abstract_word (
  id                        bigint not null,
  added_on                  timestamp,
  last_modified_on          timestamp,
  precision                 varchar(255),
  plural                    varchar(255),
  origin_id                 bigint,
  type_id                   bigint,
  constraint pk_mashi_abstract_word primary key (id))
;


create table word_categories (
  word_id                        bigint not null,
  category_id                    bigint not null,
  constraint pk_word_categories primary key (word_id, category_id))
;

create table word_images (
  word_id                        bigint not null,
  image_id                       bigint not null,
  constraint pk_word_images primary key (word_id, image_id))
;
create sequence mashi_category_word_seq;

create sequence mashi_example_word_seq;

create sequence mashi_french_word_seq;

create sequence mashi_image_word_seq;

create sequence mashi_mashi_word_seq;

create sequence mashi_origin_word_seq;

create sequence mashi_type_word_seq;

create sequence mashi_user_seq;

create sequence mashi_abstract_word_seq;

alter table mashi_example_word add constraint fk_mashi_example_word_word_1 foreign key (word_id) references mashi_abstract_word (id) on delete restrict on update restrict;
create index ix_mashi_example_word_word_1 on mashi_example_word (word_id);
alter table mashi_french_word add constraint fk_mashi_french_word_origin_2 foreign key (origin_id) references mashi_origin_word (id) on delete restrict on update restrict;
create index ix_mashi_french_word_origin_2 on mashi_french_word (origin_id);
alter table mashi_french_word add constraint fk_mashi_french_word_type_3 foreign key (type_id) references mashi_type_word (id) on delete restrict on update restrict;
create index ix_mashi_french_word_type_3 on mashi_french_word (type_id);
alter table mashi_mashi_word add constraint fk_mashi_mashi_word_origin_4 foreign key (origin_id) references mashi_origin_word (id) on delete restrict on update restrict;
create index ix_mashi_mashi_word_origin_4 on mashi_mashi_word (origin_id);
alter table mashi_mashi_word add constraint fk_mashi_mashi_word_type_5 foreign key (type_id) references mashi_type_word (id) on delete restrict on update restrict;
create index ix_mashi_mashi_word_type_5 on mashi_mashi_word (type_id);
alter table mashi_abstract_word add constraint fk_mashi_abstract_word_origin_6 foreign key (origin_id) references mashi_origin_word (id) on delete restrict on update restrict;
create index ix_mashi_abstract_word_origin_6 on mashi_abstract_word (origin_id);
alter table mashi_abstract_word add constraint fk_mashi_abstract_word_type_7 foreign key (type_id) references mashi_type_word (id) on delete restrict on update restrict;
create index ix_mashi_abstract_word_type_7 on mashi_abstract_word (type_id);



alter table word_categories add constraint fk_word_categories_mashi_fren_01 foreign key (word_id) references mashi_french_word (id) on delete restrict on update restrict;

alter table word_categories add constraint fk_word_categories_mashi_cate_02 foreign key (category_id) references mashi_category_word (id) on delete restrict on update restrict;

alter table word_images add constraint fk_word_images_mashi_french_w_01 foreign key (word_id) references mashi_french_word (id) on delete restrict on update restrict;

alter table word_images add constraint fk_word_images_mashi_image_wo_02 foreign key (image_id) references mashi_image_word (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists mashi_category_word;

drop table if exists mashi_example_word;

drop table if exists mashi_french_word;

drop table if exists word_categories;

drop table if exists word_images;

drop table if exists mashi_image_word;

drop table if exists mashi_mashi_word;

drop table if exists mashi_origin_word;

drop table if exists mashi_abstract_timer;

drop table if exists mashi_type_word;

drop table if exists mashi_user;

drop table if exists mashi_abstract_word;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists mashi_category_word_seq;

drop sequence if exists mashi_example_word_seq;

drop sequence if exists mashi_french_word_seq;

drop sequence if exists mashi_image_word_seq;

drop sequence if exists mashi_mashi_word_seq;

drop sequence if exists mashi_origin_word_seq;

drop sequence if exists mashi_type_word_seq;

drop sequence if exists mashi_user_seq;

drop sequence if exists mashi_abstract_word_seq;

