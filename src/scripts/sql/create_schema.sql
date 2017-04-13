DROP TABLE IF EXISTS `RoleTypeEntity`;
DROP TABLE IF EXISTS `RelationshipIdentityTypeEntity`;
DROP TABLE IF EXISTS `RelationshipTypeEntity`;
DROP TABLE IF EXISTS `GroupTypeEntity`;
DROP TABLE IF EXISTS `AccountTypeEntity`;
DROP TABLE IF EXISTS `PermissionTypeEntity`;
DROP TABLE IF EXISTS `PasswordCredentialTypeEntity`;
DROP TABLE IF EXISTS `IdentityTypeEntity`;
DROP TABLE IF EXISTS `PartitionTypeEntity`;
DROP TABLE IF EXISTS `AttributeTypeEntity`;
DROP TABLE IF EXISTS `AttributedTypeEntity`;
DROP TABLE IF EXISTS `hibernate_sequence`;
DROP TABLE IF EXISTS `Comment`;
DROP TABLE IF EXISTS `ForumThread`;
DROP TABLE IF EXISTS `Users`;

CREATE TABLE `AttributedTypeEntity` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `AttributeTypeEntity` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  `value` longtext,
  `owner_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ehu3pv5hm09wme4vkmwwct7x5` (`owner_id`),
  CONSTRAINT `FK_ehu3pv5hm09wme4vkmwwct7x5` FOREIGN KEY (`owner_id`) REFERENCES `AttributedTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `PartitionTypeEntity` (
  `configurationName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_2la0ajikmqq2oylb9b3txlakb` FOREIGN KEY (`id`) REFERENCES `AttributedTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `IdentityTypeEntity` (
  `createdDate` datetime DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `expirationDate` datetime DEFAULT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `partition_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g7e4b2ks8kb6tpjb1tbsfp42g` (`partition_id`),
  CONSTRAINT `FK_f3d4bi3xn9kph5uj8p4rufay8` FOREIGN KEY (`id`) REFERENCES `AttributedTypeEntity` (`id`),
  CONSTRAINT `FK_g7e4b2ks8kb6tpjb1tbsfp42g` FOREIGN KEY (`partition_id`) REFERENCES `PartitionTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `AccountTypeEntity` (
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `loginName` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_7iu5vl4yukkrqnr7cawu5br46` FOREIGN KEY (`id`) REFERENCES `IdentityTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `GroupTypeEntity` (
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jd61h1pmevgfdkjvxr3vyhdml` (`parent_id`),
  CONSTRAINT `FK_dcnn37f1v104dnxarqx2dikw8` FOREIGN KEY (`id`) REFERENCES `IdentityTypeEntity` (`id`),
  CONSTRAINT `FK_jd61h1pmevgfdkjvxr3vyhdml` FOREIGN KEY (`parent_id`) REFERENCES `GroupTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `PasswordCredentialTypeEntity` (
  `id` bigint(20) NOT NULL,
  `effectiveDate` datetime DEFAULT NULL,
  `expiryDate` datetime DEFAULT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  `passwordEncodedHash` varchar(255) DEFAULT NULL,
  `passwordSalt` varchar(255) DEFAULT NULL,
  `owner_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8g31qsmla607xjwnamfqn1nf5` (`owner_id`),
  CONSTRAINT `FK_8g31qsmla607xjwnamfqn1nf5` FOREIGN KEY (`owner_id`) REFERENCES `AttributedTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `PermissionTypeEntity` (
  `id` bigint(20) NOT NULL,
  `assignee` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `resourceClass` varchar(255) DEFAULT NULL,
  `resourceIdentifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `RelationshipTypeEntity` (
  `typeName` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ay6h7h4e7t0my7p7jsp6obvqs` FOREIGN KEY (`id`) REFERENCES `AttributedTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `RelationshipIdentityTypeEntity` (
  `identifier` bigint(20) NOT NULL,
  `descriptor` varchar(255) DEFAULT NULL,
  `identityType_id` varchar(255) DEFAULT NULL,
  `owner_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`identifier`),
  KEY `FK_di27wg0grlx8bmlyr4jxffw49` (`identityType_id`),
  KEY `FK_rlk0qy8e3g13ud2rqonjmd9rt` (`owner_id`),
  CONSTRAINT `FK_di27wg0grlx8bmlyr4jxffw49` FOREIGN KEY (`identityType_id`) REFERENCES `IdentityTypeEntity` (`id`),
  CONSTRAINT `FK_rlk0qy8e3g13ud2rqonjmd9rt` FOREIGN KEY (`owner_id`) REFERENCES `RelationshipTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `RoleTypeEntity` (
  `name` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_awlh3ujrqy6rqsud7p204o3d4` FOREIGN KEY (`id`) REFERENCES `IdentityTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Users` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `imageName` varchar(255) DEFAULT NULL,
  `username` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_usrtbl` (`username`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `ForumThread` (
  `id` bigint(20) NOT NULL,
  `category` varchar(255) NOT NULL,
  `createdOn` date DEFAULT NULL,
  `hits` int(11) NOT NULL DEFAULT '1',
  `lastUpdate` datetime DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `posts` int(11) NOT NULL DEFAULT '1',
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fthrd` (`name`),
  KEY `FK_author` (`author_id`),
  CONSTRAINT `FK_author` FOREIGN KEY (`author_id`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;  

CREATE TABLE `Comment` (
  `id` bigint(20) NOT NULL,
  `lastUpdate` datetime DEFAULT NULL,
  `message` varchar(225) NOT NULL,
  `comment_author_id` bigint(20) DEFAULT NULL,
  `thread_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_comment_author` (`comment_author_id`),
  KEY `FK_comment_thread` (`thread_id`),
  CONSTRAINT `FK_comment_author` FOREIGN KEY (`comment_author_id`) REFERENCES `Users` (`id`),
  CONSTRAINT `FK_comment_thread` FOREIGN KEY (`thread_id`) REFERENCES `ForumThread` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;  

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;