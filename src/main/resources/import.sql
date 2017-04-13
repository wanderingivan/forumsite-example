
LOCK TABLES `AttributedTypeEntity` WRITE;
INSERT INTO `AttributedTypeEntity` VALUES 
                                         ('04e95660-f4f0-429b-b838-7e35affbbdf4'),
                                         ('0d5c5056-cc43-40b3-a3ae-72c34171b089'),
                                         ('290f7336-b2bb-4f84-9408-d93cb1a15184'),
                                         ('4043a40f-206f-4118-866b-42f5897b983a'),
                                         ('72400b97-138f-4d3b-89d4-b73546e447eb'),
                                         ('9b2a98d7-4073-4ff5-bd53-66884b0ab362'),
                                         ('a34ae6cf-a098-4c61-9f41-72dc698221b1'),
                                         ('e5bd584d-1d96-40fe-9b35-d29ba076c73f'),
                                         ('f209b353-676f-4222-bd5a-f0ea0b320b27');
UNLOCK TABLES;

LOCK TABLES `PartitionTypeEntity` WRITE;
INSERT INTO `PartitionTypeEntity` VALUES ('DEFAULT','default','org.picketlink.idm.model.basic.Realm','f209b353-676f-4222-bd5a-f0ea0b320b27');
UNLOCK TABLES;

LOCK TABLES `IdentityTypeEntity` WRITE;
INSERT INTO `IdentityTypeEntity` VALUES 
                                              ('2017-04-12 19:31:47','',NULL,'org.picketlink.idm.model.basic.User','0d5c5056-cc43-40b3-a3ae-72c34171b089','f209b353-676f-4222-bd5a-f0ea0b320b27'),
                                              ('2017-04-12 19:20:09','',NULL,'org.picketlink.idm.model.basic.Role','4043a40f-206f-4118-866b-42f5897b983a','f209b353-676f-4222-bd5a-f0ea0b320b27'),
                                              ('2017-04-12 19:21:26','',NULL,'org.picketlink.idm.model.basic.User','9b2a98d7-4073-4ff5-bd53-66884b0ab362','f209b353-676f-4222-bd5a-f0ea0b320b27'),
                                              ('2017-04-12 19:20:09','',NULL,'org.picketlink.idm.model.basic.User','a34ae6cf-a098-4c61-9f41-72dc698221b1','f209b353-676f-4222-bd5a-f0ea0b320b27'),
                                              ('2017-04-12 19:20:09','',NULL,'org.picketlink.idm.model.basic.Role','e5bd584d-1d96-40fe-9b35-d29ba076c73f','f209b353-676f-4222-bd5a-f0ea0b320b27');
UNLOCK TABLES;

LOCK TABLES `RoleTypeEntity` WRITE;
INSERT INTO `RoleTypeEntity` VALUES ('user','4043a40f-206f-4118-866b-42f5897b983a'),
                                    ('admin','e5bd584d-1d96-40fe-9b35-d29ba076c73f');
UNLOCK TABLES;

LOCK TABLES `PasswordCredentialTypeEntity` WRITE;
INSERT INTO `PasswordCredentialTypeEntity` VALUES 
 (1,'2017-04-12 19:20:09',NULL,'org.picketlink.idm.credential.storage.EncodedPasswordStorage','/i8OzEynWyuJu/5d45NKtP53W8qeNRA0+Nxw91j7wZJoThrqTLK5/HvUXLAdBldqFTXdy+XJQB2V\n05tGFJrczQ==','-4453637265318533892','a34ae6cf-a098-4c61-9f41-72dc698221b1'),
(5,'2017-04-12 19:21:26',NULL,'org.picketlink.idm.credential.storage.EncodedPasswordStorage','sAQ0wAF914/KQ6ZS23LfRbzHr0gfRAlhRUc1TpJO+ewjPnEyMClsk1Hv3vSlx13caoESWROUIviC\nmpvyw2dcbA==','5348443346660052653','9b2a98d7-4073-4ff5-bd53-66884b0ab362'),
(15,'2017-04-12 19:31:47',NULL,'org.picketlink.idm.credential.storage.EncodedPasswordStorage','94aC+3ladG1oCaJ2vfSUjYosRhY+iHLsrP81N98/i2t4CjTwBb323TH3khCunYbt4c/t2mYKi3tB\n2axvR3ahRw==','865734182569446740','0d5c5056-cc43-40b3-a3ae-72c34171b089');
UNLOCK TABLES;

LOCK TABLES `RelationshipTypeEntity` WRITE;
INSERT INTO `RelationshipTypeEntity` VALUES ('org.picketlink.idm.model.basic.Grant','04e95660-f4f0-429b-b838-7e35affbbdf4'),
                                            ('org.picketlink.idm.model.basic.Grant','290f7336-b2bb-4f84-9408-d93cb1a15184'),
                                            ('org.picketlink.idm.model.basic.Grant','72400b97-138f-4d3b-89d4-b73546e447eb');
UNLOCK TABLES;

LOCK TABLES `RelationshipIdentityTypeEntity` WRITE;
INSERT INTO `RelationshipIdentityTypeEntity` VALUES (2,'role','e5bd584d-1d96-40fe-9b35-d29ba076c73f','290f7336-b2bb-4f84-9408-d93cb1a15184'),
                                                    (3,'assignee','a34ae6cf-a098-4c61-9f41-72dc698221b1','290f7336-b2bb-4f84-9408-d93cb1a15184'),
                                                    (7,'role','4043a40f-206f-4118-866b-42f5897b983a','72400b97-138f-4d3b-89d4-b73546e447eb'),
                                                    (8,'assignee','9b2a98d7-4073-4ff5-bd53-66884b0ab362','72400b97-138f-4d3b-89d4-b73546e447eb'),
                                                    (17,'role','4043a40f-206f-4118-866b-42f5897b983a','04e95660-f4f0-429b-b838-7e35affbbdf4'),
                                                    (18,'assignee','0d5c5056-cc43-40b3-a3ae-72c34171b089','04e95660-f4f0-429b-b838-7e35affbbdf4');
UNLOCK TABLES;

LOCK TABLES `Users` WRITE;
INSERT INTO `Users` VALUES (4,'descripti','email@email3.com','placeholder.jpg','username2'),
                           (14,'descriptino','email@email6.com','1489953064993.jpg','username3'),
                           (111,'A Test User','email@imil.com','8MMDv2R.jpg','testUser');
UNLOCK TABLES;


LOCK TABLES `ForumThread` WRITE;
INSERT INTO `ForumThread` VALUES (9,'category3',NULL,0,NULL,'A post',0,4),
                                 (19,'category4',NULL,0,NULL,'a thread',0,14),
                                 (24,'category3',NULL,0,NULL,'Another post',0,14),
                                 (111,'category2','2017-01-01',1,'2017-01-01 00:00:00','TestName',1,111),
                                 (222,'category2','2017-01-01',1,'2017-01-01 00:00:00','TestName2',1,111),
                                 (333,'category1','2017-01-01',1,'2017-01-01 00:00:00','TestName3',30,111),
                                 (334,'category1','2017-01-01',1,'2017-01-01 00:00:00','TestName4',1,111),
                                 (335,'category1','2017-01-01',1,'2017-01-01 00:00:00','TestName5',1,111),
                                 (336,'category1','2017-01-01',1,'2017-01-01 00:00:00','TestName6',1,111),
                                 (337,'category1','2017-01-01',1,'2017-01-01 00:00:00','TestName7',1,111);
UNLOCK TABLES;

LOCK TABLES `Comment` WRITE;
INSERT INTO `Comment` VALUES (10,NULL,'a message\r\n\r\n### A header',4,9),
                             (12,NULL,'a post _emphasized text_',4,333),
                             (20,NULL,'**a category name**',14,19),
                             (22,NULL,'> username2 said: a message\r\n\r\n### A header \r\n\r\n\r\nReply with quote',14,9),
                             (25,NULL,'The password is _password_',14,24),
                             (109,'2017-01-01 00:00:00','A message',111,111),
                             (111,'2017-01-01 00:00:00','A message',111,333),
                             (112,'2017-01-01 00:00:00','A message',111,333),
                             (113,'2017-01-01 00:00:00','A message',111,333),
                             (114,'2017-01-01 00:00:00','A message',111,333),
                             (115,'2017-01-01 00:00:00','A message',111,333),
                             (116,'2017-01-01 00:00:00','A message',111,333),
                             (117,'2017-01-01 00:00:00','A message',111,333),
                             (118,'2017-01-01 00:00:00','A message',111,333),
                             (119,'2017-01-01 00:00:00','A message',111,333),
                             (120,'2017-01-01 00:00:00','A message',111,333),
                             (121,'2017-01-01 00:00:00','A message',111,333),
                             (122,'2017-01-01 00:00:00','A message',111,333),
                             (123,'2017-01-01 00:00:00','A message',111,333),
                             (124,'2017-01-01 00:00:00','A message',111,333),
                             (125,'2017-01-01 00:00:00','A message',111,333),
                             (126,'2017-01-01 00:00:00','A message',111,333),
                             (127,'2017-01-01 00:00:00','A message',111,333),
                             (128,'2017-01-01 00:00:00','A message',111,333),
                             (129,'2017-01-01 00:00:00','A message',111,333),
                             (130,'2017-01-01 00:00:00','A message',111,333),
                             (131,'2017-01-01 00:00:00','A message',111,333),
                             (132,'2017-01-01 00:00:00','A message',111,333),
                             (133,'2017-01-01 00:00:00','A message',111,333),
                             (134,'2017-01-01 00:00:00','A message',111,333),
                             (135,'2017-01-01 00:00:00','A message',111,333),
                             (136,'2017-01-01 00:00:00','A message',111,333),
                             (137,'2017-01-01 00:00:00','A message',111,333),
                             (138,'2017-01-01 00:00:00','A message',111,333),
                             (139,'2017-01-01 00:00:00','A message',111,333),
                             (140,'2017-01-01 00:00:00','A message',111,333),
                             (213,'2017-01-01 00:00:00','A message',111,222),
                             (214,'2017-01-01 00:00:00','A message',111,333),
                             (215,'2017-01-01 00:00:00','A message',111,334),
                             (216,'2017-01-01 00:00:00','A message',111,335),
                             (217,'2017-01-01 00:00:00','A message',111,336),
                             (218,'2017-01-01 00:00:00','A message',111,337);
UNLOCK TABLES;

UPDATE ForumThread SET posts = 30 WHERE id = 333;



LOCK TABLES `PermissionTypeEntity` WRITE;
INSERT INTO `PermissionTypeEntity` VALUES 
                        (6,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9b2a98d7-4073-4ff5-bd53-66884b0ab362','1','com.forumsite.model.User','4'),
                        (11,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9b2a98d7-4073-4ff5-bd53-66884b0ab362','6','com.forumsite.model.ForumThread','9'),
                        (13,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9b2a98d7-4073-4ff5-bd53-66884b0ab362','update,delete','com.forumsite.model.Comment','12'),
                        (16,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:0d5c5056-cc43-40b3-a3ae-72c34171b089','1','com.forumsite.model.User','14'),
                        (21,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:0d5c5056-cc43-40b3-a3ae-72c34171b089','6','com.forumsite.model.ForumThread','19'),
                        (23,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:0d5c5056-cc43-40b3-a3ae-72c34171b089','update,delete','com.forumsite.model.Comment','22'),
                        (26,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:0d5c5056-cc43-40b3-a3ae-72c34171b089','6','com.forumsite.model.ForumThread','24');
UNLOCK TABLES;


LOCK TABLES `hibernate_sequence` WRITE;
INSERT INTO `hibernate_sequence` VALUES (27);
UNLOCK TABLES;



