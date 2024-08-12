INSERT INTO public.dish (count, id, name) VALUES (90, 9, 'Оливье');
INSERT INTO public.dish (count, id, name) VALUES (90, 10, 'Московский');
INSERT INTO public.dish (count, id, name) VALUES (90, 11, 'Цезарь');
INSERT INTO public.dish (count, id, name) VALUES (90, 12, 'Щербет');

INSERT INTO public.user_entity (year, date_registration, date_update, id, email, login, name, password, role, surname, check_ivent) VALUES (0, null, null, 31, null, 'user1', null, '$2a$10$YPxj6DjSY2rQ3OSCgoHmKuDQJtkgJqP9pQSEN9DATR84PkYWbRiaO', 'USER', null, false);
INSERT INTO public.user_entity (year, date_registration, date_update, id, email, login, name, password, role, surname, check_ivent) VALUES (0, null, null, 32, null, 'user2', null, '$2a$10$kbqObRURd.qHrZfdYmudP.6P48RZQaRMVGth9ZhhHlpr.VZqd6EbC', 'USER', null, false);
INSERT INTO public.user_entity (year, date_registration, date_update, id, email, login, name, password, role, surname, check_ivent) VALUES (0, null, null, 33, null, 'user3', null, '$2a$10$.W7/VwTQYLpoMlYb306GwOGmDobXqT0PJwr1mzecvErhXdMsAQmS.', 'USER', null, false);
INSERT INTO public.user_entity (year, date_registration, date_update, id, email, login, name, password, role, surname, check_ivent) VALUES (0, null, null, 29, null, 'chief', null, '$2a$10$1VJxOUlsXCaAZG/kO4j4qOn9as1iZVoDGhqKAe9W1TgWKqsQs7u56', 'CHIEF', null, false);
INSERT INTO public.user_entity (year, date_registration, date_update, id, email, login, name, password, role, surname, check_ivent) VALUES (0, null, null, 30, null, 'cooking', null, '$2a$10$Kay5qSW.cqnrG..Iej6bJ.nGK8DJCSppSFCe0By9aIslOeGYqGm1W', 'COOK', null, false);
INSERT INTO public.user_entity (year, date_registration, date_update, id, email, login, name, password, role, surname, check_ivent) VALUES (0, null, null, 28, null, 'manager', null, '$2a$10$J3rTGPG/NeCzvLc..vREseenrUn8BYuGHEAUtjLu/v82CvoYaLLvO', 'MANAGER', null, false);


INSERT INTO public.menuServices (date_create, id) VALUES ('2024-07-29 19:40:32.702000', 7);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-07-29 19:41:05.860000', 8);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-07-30 16:27:48.338000', 9);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-07-30 16:28:07.292000', 10);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-02 18:46:35.196000', 11);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-02 19:35:10.104000', 12);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-02 20:05:17.424000', 13);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-02 20:06:00.388000', 14);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-02 20:07:48.830000', 15);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-02 20:08:25.459000', 16);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-03 00:08:18.535000', 17);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-03 06:25:21.729000', 18);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-03 06:30:36.624000', 19);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-03 06:48:23.863000', 20);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-03 06:51:37.576000', 21);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-03 06:55:02.700000', 22);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-03 06:56:18.890000', 23);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-06 16:33:32.240000', 24);
INSERT INTO public.menuServices (date_create, id) VALUES ('2024-08-06 17:06:57.419000', 25);

INSERT INTO public.calendar_post (date_create, date_delete, id, menu_id, user_id, date_delete_string, text) VALUES ('2024-08-06 17:06:57.452000', '2024-09-01 00:00:00.000000', 22, 25, 28, '2024-09-01', 'День знаний');

INSERT INTO public.menu_user_list (menu_id, user_list_id) VALUES (25, 31);
INSERT INTO public.menu_user_list (menu_id, user_list_id) VALUES (25, 32);

INSERT INTO public.calendar_post_list_visit_user (calendar_post_id, list_visit_user_id) VALUES (22, 31);
INSERT INTO public.calendar_post_list_visit_user (calendar_post_id, list_visit_user_id) VALUES (22, 32);

INSERT INTO public.send_dish (count, serviced, calendar_post_id, id, menu_id, user_id, name, type) VALUES (2, false, 22, 137, 25, 31, 'Оливье', 'Гость выбрал');
INSERT INTO public.send_dish (count, serviced, calendar_post_id, id, menu_id, user_id, name, type) VALUES (2, false, 22, 138, 25, 31, 'Московский', 'Гость выбрал');
INSERT INTO public.send_dish (count, serviced, calendar_post_id, id, menu_id, user_id, name, type) VALUES (2, false, 22, 139, 25, 31, 'Цезарь', 'Гость выбрал');
INSERT INTO public.send_dish (count, serviced, calendar_post_id, id, menu_id, user_id, name, type) VALUES (2, false, 22, 140, 25, 31, 'Щербет', 'Гость выбрал');
INSERT INTO public.send_dish (count, serviced, calendar_post_id, id, menu_id, user_id, name, type) VALUES (1, false, 22, 141, 25, 32, 'Оливье', 'Гость выбрал');
INSERT INTO public.send_dish (count, serviced, calendar_post_id, id, menu_id, user_id, name, type) VALUES (1, false, 22, 142, 25, 32, 'Московский', 'Гость выбрал');
INSERT INTO public.send_dish (count, serviced, calendar_post_id, id, menu_id, user_id, name, type) VALUES (7, false, 22, 133, 25, null, 'Оливье', 'Заготовка');
INSERT INTO public.send_dish (count, serviced, calendar_post_id, id, menu_id, user_id, name, type) VALUES (1, false, 22, 143, 25, 32, 'Цезарь', 'Гость выбрал');
INSERT INTO public.send_dish (count, serviced, calendar_post_id, id, menu_id, user_id, name, type) VALUES (7, false, 22, 134, 25, null, 'Московский', 'Заготовка');
INSERT INTO public.send_dish (count, serviced, calendar_post_id, id, menu_id, user_id, name, type) VALUES (1, false, 22, 144, 25, 32, 'Щербет', 'Гость выбрал');
INSERT INTO public.send_dish (count, serviced, calendar_post_id, id, menu_id, user_id, name, type) VALUES (7, false, 22, 135, 25, null, 'Цезарь', 'Заготовка');
INSERT INTO public.send_dish (count, serviced, calendar_post_id, id, menu_id, user_id, name, type) VALUES (7, false, 22, 136, 25, null, 'Щербет', 'Заготовка');

INSERT INTO public.menu_list_send_dish (list_send_dish_id, menu_id) VALUES (133, 25);
INSERT INTO public.menu_list_send_dish (list_send_dish_id, menu_id) VALUES (134, 25);
INSERT INTO public.menu_list_send_dish (list_send_dish_id, menu_id) VALUES (135, 25);
INSERT INTO public.menu_list_send_dish (list_send_dish_id, menu_id) VALUES (136, 25);

INSERT INTO public.task (check_chief, score_task, solve, date_create, id, time_to_solve, user_accept_id, user_send_id, date_delete_string, text) VALUES (false, 10, false, '2024-08-06 17:08:09.328000', 3, '2024-08-16 00:00:00.000000', 33, 29, '2024-08-16', 'Задача такая...');
