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
);

CREATE TABLE `AttributeTypeEntity` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  `value` longtext,
  `owner_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_AT_OWNER` FOREIGN KEY (`owner_id`) REFERENCES `AttributedTypeEntity` (`id`)
);

CREATE TABLE `PartitionTypeEntity` (
  `configurationName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_AT_ID` FOREIGN KEY (`id`) REFERENCES `AttributedTypeEntity` (`id`)
);

CREATE TABLE `IdentityTypeEntity` (
  `createdDate` datetime DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `expirationDate` datetime DEFAULT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `partition_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_AT_ID_IT` FOREIGN KEY (`id`) REFERENCES `AttributedTypeEntity` (`id`),
  CONSTRAINT `FK_PT_ID_IT` FOREIGN KEY (`partition_id`) REFERENCES `PartitionTypeEntity` (`id`)
);


CREATE TABLE `AccountTypeEntity` (
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `loginName` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ID_IT` FOREIGN KEY (`id`) REFERENCES `IdentityTypeEntity` (`id`)
);


CREATE TABLE `GroupTypeEntity` (
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_dcnn37f1v104dnxarqx2dikw8` FOREIGN KEY (`id`) REFERENCES `IdentityTypeEntity` (`id`),
  CONSTRAINT `FK_jd61h1pmevgfdkjvxr3vyhdml` FOREIGN KEY (`parent_id`) REFERENCES `GroupTypeEntity` (`id`)
);

CREATE TABLE `PasswordCredentialTypeEntity` (
  `id` bigint(20) NOT NULL,
  `effectiveDate` datetime DEFAULT NULL,
  `expiryDate` datetime DEFAULT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  `passwordEncodedHash` varchar(255) DEFAULT NULL,
  `passwordSalt` varchar(255) DEFAULT NULL,
  `owner_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_8g31qsmla607xjwnamfqn1nf5` FOREIGN KEY (`owner_id`) REFERENCES `AttributedTypeEntity` (`id`)
);

CREATE TABLE `PermissionTypeEntity` (
  `id` bigint(20) NOT NULL,
  `assignee` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `resourceClass` varchar(255) DEFAULT NULL,
  `resourceIdentifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `RelationshipTypeEntity` (
  `typeName` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ay6h7h4e7t0my7p7jsp6obvqs` FOREIGN KEY (`id`) REFERENCES `AttributedTypeEntity` (`id`)
);

CREATE TABLE `RelationshipIdentityTypeEntity` (
  `identifier` bigint(20) NOT NULL,
  `descriptor` varchar(255) DEFAULT NULL,
  `identityType_id` varchar(255) DEFAULT NULL,
  `owner_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`identifier`),
  CONSTRAINT `FK_REL_ID_IT` FOREIGN KEY (`identityType_id`) REFERENCES `IdentityTypeEntity` (`id`),
  CONSTRAINT `FK_RT_ID_REL` FOREIGN KEY (`owner_id`) REFERENCES `RelationshipTypeEntity` (`id`)
);

CREATE TABLE `RoleTypeEntity` (
  `name` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_RT_ID_IT` FOREIGN KEY (`id`) REFERENCES `IdentityTypeEntity` (`id`)
);

CREATE TABLE `Users` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `imageName` varchar(255) DEFAULT NULL,
  `signedOn` date DEFAULT NULL,
  `username` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_usrtbl` (`username`,`email`)
);

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
  CONSTRAINT `FK_author` FOREIGN KEY (`author_id`) REFERENCES `Users` (`id`)
);  

CREATE TABLE `Comment` (
  `id` bigint(20) NOT NULL,
  `lastUpdate` datetime DEFAULT NULL,
  `message` varchar(1000) NOT NULL,
  `comment_author_id` bigint(20) DEFAULT NULL,
  `thread_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_comment_author` FOREIGN KEY (`comment_author_id`) REFERENCES `Users` (`id`),
  CONSTRAINT `FK_comment_thread` FOREIGN KEY (`thread_id`) REFERENCES `ForumThread` (`id`)
);  

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
);

INSERT INTO `hibernate_sequence` VALUES (1);