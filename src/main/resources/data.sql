DROP TABLE IF EXISTS deposits;

CREATE TABLE `deposits` (
  `deposit_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `start_dt` date NOT NULL,
  `end_dt` date NOT NULL,
  PRIMARY KEY (`deposit_id`)
);

INSERT INTO `deposits` ( `customer_id`, `start_dt`, `end_dt`)
VALUES ( 1, CURDATE()-250, CURDATE()-250);

INSERT INTO `deposits` ( `customer_id`, `start_dt`, `end_dt`)
VALUES ( 1, CURDATE()-200, CURDATE()-180);