

CREATE TABLE `card_info` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `name` varchar(255) DEFAULT NULL,
                             `card_num` varchar(20) DEFAULT NULL COMMENT '卡号码',
                             `statement_date` int DEFAULT NULL COMMENT '账单日',
                             `max_days` int DEFAULT NULL COMMENT '出账单后最长还款天数',
                             `amount` int DEFAULT NULL COMMENT '总额度',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='卡信息';


CREATE TABLE `repayment_plan` (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `card_id` int DEFAULT NULL COMMENT '卡id',
                                  `month` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'yyyyMM月',
                                  `bill_date` varchar(8) DEFAULT NULL COMMENT '出账日',
                                  `Latest_repayment_date` varchar(8) DEFAULT NULL COMMENT '最晚还款日期',
                                  `last_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5934 DEFAULT CHARSET=utf8 COMMENT='还款计划表';



CREATE TABLE `cart_tran_detail` (
                                    `id` int NOT NULL AUTO_INCREMENT,
                                    `tran_date` date DEFAULT NULL COMMENT '交易日期',
                                    `seq` int DEFAULT NULL COMMENT '交易日期内的第几条，从1开始',
                                    `card_id` int DEFAULT NULL,
                                    `poundage` int DEFAULT NULL COMMENT '手续费',
                                    `tran_type` tinyint(1) DEFAULT NULL COMMENT '1收入：0支出',
                                    `amount` int DEFAULT NULL COMMENT '交易前总金额',
                                    `tran_amount` int DEFAULT NULL COMMENT '交易金额',
                                    `available_credit` int DEFAULT NULL COMMENT '可用额度',
                                    `cyc_repayment_date` date DEFAULT NULL COMMENT '计划还款日',
                                    `cash` int DEFAULT NULL COMMENT '现金',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `trade_date_Seq` (`tran_date`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;