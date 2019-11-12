CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `buckets`
(
    `bucket_id` bigint(20) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`bucket_id`)
) ENGINE = MyISAM
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;

CREATE TABLE `buckets_items`
(
    `bucket_id` bigint(20) NOT NULL,
    `item_id`   bigint(20) NOT NULL,
    KEY `FK773jgk471xednobhr8ju3li49` (`item_id`),
    KEY `FK7272bsc9hv6m7k850ekia6f0w` (`bucket_id`)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

CREATE TABLE `items`
(
    `item_id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `item_name` varchar(255) DEFAULT NULL,
    `price`     double       DEFAULT NULL,
    PRIMARY KEY (`item_id`)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

CREATE TABLE `orders`
(
    `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id`  bigint(20) DEFAULT NULL,
    PRIMARY KEY (`order_id`),
    KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

CREATE TABLE `orders_items`
(
    `order_id` bigint(20) NOT NULL,
    `item_id`  bigint(20) NOT NULL,
    KEY `FKc03a4t5e1xbn9g2qp2k2umr64` (`item_id`),
    KEY `FKij1wwgx6o198ubsx1oulpopem` (`order_id`)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

CREATE TABLE `roles`
(
    `role_id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `role_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `UK_716hgxp60ym1lifrdgp67xt5k` (`role_name`)
) ENGINE = MyISAM
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;
INSERT INTO `internet_shop`.`roles`
(`role_id`,
 `role_name`)
VALUES (1, "ADMIN"),
       (2, "USER");

CREATE TABLE `users` (
                         `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `login` varchar(255) DEFAULT NULL,
                         `user_name` varchar(255) DEFAULT NULL,
                         `user_password` varchar(500) DEFAULT NULL,
                         `salt` tinyblob,
                         `surname` varchar(255) DEFAULT NULL,
                         `token` varchar(255) DEFAULT NULL,
                         `bucket_id` bigint(20) DEFAULT NULL,
                         PRIMARY KEY (`user_id`),
                         UNIQUE KEY `login_UNIQUE` (`login`),
                         KEY `FK8l2qc4c6gihjdyoch727guci` (`bucket_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `users_roles` (
                               `user_id` bigint(20) NOT NULL,
                               `role_id` bigint(20) NOT NULL,
                               PRIMARY KEY (`user_id`,`role_id`),
                               KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
