--when Spring Boot starts 'data.sql' is kicking off and running by default

insert into beer (id, beer_name, beer_style, created_date, last_modified_date, min_on_hand, quantity_to_brew, price, upc, version ) values ('0a818933-087d-47f2-ad83-2f986ed087eb', 'Mango Bobs', 'IPA', current_timestamp , current_timestamp , 12,  200, 12.95, '0631234200036', 1);
insert into beer (id, beer_name, beer_style, created_date, last_modified_date, min_on_hand, quantity_to_brew, price, upc, version ) values ('a712d914-61ea-4623-8bd0-32c0f6545bfd', 'Galaxy Cat', 'PALE_ALE', current_timestamp , current_timestamp , 12,  200, 12.95, '0631234300019', 1);
insert into beer (id, beer_name, beer_style, created_date, last_modified_date, min_on_hand, quantity_to_brew, price, upc, version ) values ('026cc3c8-3a0c-4083-a05b-e908048c1b08', 'Pinball Porter', 'PORTER', current_timestamp , current_timestamp , 12,  200, 12.95, '0083783375213', 1);