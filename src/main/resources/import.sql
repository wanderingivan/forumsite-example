                                       
INSERT INTO AttributedTypeEntity VALUES ('04e95660-f4f0-429b-b838-7e35affbbdf4'),('0aa06836-0a61-4fcc-bbdf-7472ca60221a'),('0d5c5056-cc43-40b3-a3ae-72c34171b089'),('12bf5bbd-b4b3-455d-948b-4141649d59b6'),('243fece2-00c2-4f96-bbc3-12aadd1ec2a8'),('28ec414e-3456-45ad-869e-9b0914b2591c'),('290f7336-b2bb-4f84-9408-d93cb1a15184'),('3d55b35c-4d05-475c-a37e-fd945c5ad15b'),('4043a40f-206f-4118-866b-42f5897b983a'),('450d0bed-ffc8-4e1f-b4b2-824bf39e8870'),('49f5e557-1993-44aa-a197-124d0972bb4b'),('72400b97-138f-4d3b-89d4-b73546e447eb'),('7ca292a3-edc4-4d33-b2e3-37d59892de2e'),('9596adea-e4ca-455b-b062-ba7d2831b85a'),('9b2a98d7-4073-4ff5-bd53-66884b0ab362'),('a34ae6cf-a098-4c61-9f41-72dc698221b1'),('e3081196-f3b8-41e8-baa5-b4b278abe4fb'),('e5bd584d-1d96-40fe-9b35-d29ba076c73f'),('f209b353-676f-4222-bd5a-f0ea0b320b27'),('f2c91e4d-8b24-4a09-ae83-f2ed2e813a43'),('f94c4e7e-87c9-46cd-9cbb-660450e51653');
                                          
INSERT INTO PartitionTypeEntity VALUES ('DEFAULT','default','org.picketlink.idm.model.basic.Realm','f209b353-676f-4222-bd5a-f0ea0b320b27');
                                          
INSERT INTO IdentityTypeEntity VALUES ('2017-05-03 18:10:24','1',NULL,'org.picketlink.idm.model.basic.Role','28ec414e-3456-45ad-869e-9b0914b2591c','f209b353-676f-4222-bd5a-f0ea0b320b27'),('2017-05-03 19:04:29','1',NULL,'org.picketlink.idm.model.basic.User','450d0bed-ffc8-4e1f-b4b2-824bf39e8870','f209b353-676f-4222-bd5a-f0ea0b320b27'),('2017-04-12 19:31:47',1,NULL,'org.picketlink.idm.model.basic.User','0d5c5056-cc43-40b3-a3ae-72c34171b089','f209b353-676f-4222-bd5a-f0ea0b320b27'),('2017-05-03 18:10:24',1,NULL,'org.picketlink.idm.model.basic.User','243fece2-00c2-4f96-bbc3-12aadd1ec2a8','f209b353-676f-4222-bd5a-f0ea0b320b27'),('2017-05-03 18:10:24',1,NULL,'org.picketlink.idm.model.basic.Role','7ca292a3-edc4-4d33-b2e3-37d59892de2e','f209b353-676f-4222-bd5a-f0ea0b320b27'),('2017-05-03 19:05:43',1,NULL,'org.picketlink.idm.model.basic.User','9596adea-e4ca-455b-b062-ba7d2831b85a','f209b353-676f-4222-bd5a-f0ea0b320b27'),('2017-05-03 18:11:09',1,NULL,'org.picketlink.idm.model.basic.User','f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','f209b353-676f-4222-bd5a-f0ea0b320b27'),('2017-05-03 19:05:01',1,NULL,'org.picketlink.idm.model.basic.User','f94c4e7e-87c9-46cd-9cbb-660450e51653','f209b353-676f-4222-bd5a-f0ea0b320b27');
                                          
INSERT INTO `RoleTypeEntity` VALUES ('user','28ec414e-3456-45ad-869e-9b0914b2591c'),('admin','7ca292a3-edc4-4d33-b2e3-37d59892de2e');

INSERT INTO AccountTypeEntity VALUES (NULL,NULL,NULL,'admin','243fece2-00c2-4f96-bbc3-12aadd1ec2a8'),('email@email2.com',NULL,NULL,'username2','450d0bed-ffc8-4e1f-b4b2-824bf39e8870'),('email@email4.com',NULL,NULL,'username4','9596adea-e4ca-455b-b062-ba7d2831b85a'),('email@email.com',NULL,NULL,'username1','f2c91e4d-8b24-4a09-ae83-f2ed2e813a43'),('email@email3.com',NULL,NULL,'username3','f94c4e7e-87c9-46cd-9cbb-660450e51653');

INSERT INTO RelationshipTypeEntity VALUES ('org.picketlink.idm.model.basic.Grant','0aa06836-0a61-4fcc-bbdf-7472ca60221a'),('org.picketlink.idm.model.basic.Grant','12bf5bbd-b4b3-455d-948b-4141649d59b6'),('org.picketlink.idm.model.basic.Grant','3d55b35c-4d05-475c-a37e-fd945c5ad15b'),('org.picketlink.idm.model.basic.Grant','49f5e557-1993-44aa-a197-124d0972bb4b'),('org.picketlink.idm.model.basic.Grant','e3081196-f3b8-41e8-baa5-b4b278abe4fb');                                 
                                       
INSERT INTO RelationshipIdentityTypeEntity VALUES (2,'role','7ca292a3-edc4-4d33-b2e3-37d59892de2e','49f5e557-1993-44aa-a197-124d0972bb4b'),(3,'assignee','243fece2-00c2-4f96-bbc3-12aadd1ec2a8','49f5e557-1993-44aa-a197-124d0972bb4b'),(7,'role','28ec414e-3456-45ad-869e-9b0914b2591c','e3081196-f3b8-41e8-baa5-b4b278abe4fb'),(8,'assignee','f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','e3081196-f3b8-41e8-baa5-b4b278abe4fb'),(12,'role','28ec414e-3456-45ad-869e-9b0914b2591c','12bf5bbd-b4b3-455d-948b-4141649d59b6'),(13,'assignee','450d0bed-ffc8-4e1f-b4b2-824bf39e8870','12bf5bbd-b4b3-455d-948b-4141649d59b6'),(17,'role','28ec414e-3456-45ad-869e-9b0914b2591c','0aa06836-0a61-4fcc-bbdf-7472ca60221a'),(18,'assignee','f94c4e7e-87c9-46cd-9cbb-660450e51653','0aa06836-0a61-4fcc-bbdf-7472ca60221a'),(22,'role','28ec414e-3456-45ad-869e-9b0914b2591c','3d55b35c-4d05-475c-a37e-fd945c5ad15b'),(23,'assignee','9596adea-e4ca-455b-b062-ba7d2831b85a','3d55b35c-4d05-475c-a37e-fd945c5ad15b');                                    
                                        
INSERT INTO PasswordCredentialTypeEntity VALUES (1,'2017-05-03 18:10:24',NULL,'org.picketlink.idm.credential.storage.EncodedPasswordStorage',STRINGDECODE('xCxEXLKikNIeqU27YYOEdr0jqsTY+FWbNgniCNJKnu9OZ3JAA6lp3y8UwFGtqbsP/BlBbNTxTnyl\nAmuIIWR8HQ=='),'-8351306160089645233','243fece2-00c2-4f96-bbc3-12aadd1ec2a8'),(5,'2017-05-03 18:11:09',NULL,'org.picketlink.idm.credential.storage.EncodedPasswordStorage',STRINGDECODE('N78PIFvIV93Ph9haCAAhsaHG63nEjZVSv3Hk8uTDbc1XPHVMkkZh+uljklOoCOsa0c4zaQwa75RN\n/js9nPZbXA=='),'8524542794117485867','f2c91e4d-8b24-4a09-ae83-f2ed2e813a43'),(10,'2017-05-03 19:04:29',NULL,'org.picketlink.idm.credential.storage.EncodedPasswordStorage',STRINGDECODE('s89jJFS6l+fwuBc3OUhKbEKTma5aHTQG0+9+QBHJuuvj067IsMxpXrdekxmea0A0tK4ZvZivIQ2K\na7VQ1EXafQ=='),'-2873648967460190796','450d0bed-ffc8-4e1f-b4b2-824bf39e8870'),(15,'2017-05-03 19:05:01',NULL,'org.picketlink.idm.credential.storage.EncodedPasswordStorage',STRINGDECODE('iC4GKYIHA8Vkp83gfi5J2yUDqzm51VXDvGv7o4NvnHsbYroiE1FB9cTpHmS0RbV4XLqyK5+GaWsv\nYNuDCWzZSg=='),'6436724641777596006','f94c4e7e-87c9-46cd-9cbb-660450e51653'),(20,'2017-05-03 19:05:43',NULL,'org.picketlink.idm.credential.storage.EncodedPasswordStorage',STRINGDECODE('fXVLDGpAhyCBLHRect4voEl84y8x4HXh4wRqc2oX7NINfplIBk6g3OJI1a3x8JGnz3pGIqdLBqZF\n7YRHcLegzw=='),'4185839080679650416','9596adea-e4ca-455b-b062-ba7d2831b85a');
                                                  
INSERT INTO PermissionTypeEntity VALUES (6,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','1','com.forumsite.model.User','4'),(11,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','1','com.forumsite.model.User','9'),(16,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f94c4e7e-87c9-46cd-9cbb-660450e51653','1','com.forumsite.model.User','14'),(21,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','1','com.forumsite.model.User','19'),(26,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','6','com.forumsite.model.ForumThread','24'),(28,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','update,delete','com.forumsite.model.Comment','27'),(31,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','6','com.forumsite.model.ForumThread','29'),(32,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','update,delete','com.forumsite.model.Comment','30'),(35,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','6','com.forumsite.model.ForumThread','33'),(36,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','update,delete','com.forumsite.model.Comment','34'),(38,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','update,delete','com.forumsite.model.Comment','37'),(40,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','update,delete','com.forumsite.model.Comment','39'),(42,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','update,delete','com.forumsite.model.Comment','41'),(44,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','update,delete','com.forumsite.model.Comment','43'),(47,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','6','com.forumsite.model.ForumThread','45'),(48,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','update,delete','com.forumsite.model.Comment','46'),(51,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','6','com.forumsite.model.ForumThread','49'),(52,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','update,delete','com.forumsite.model.Comment','50'),(54,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','update,delete','com.forumsite.model.Comment','53'),(56,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f94c4e7e-87c9-46cd-9cbb-660450e51653','update,delete','com.forumsite.model.Comment','55'),(59,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f94c4e7e-87c9-46cd-9cbb-660450e51653','6','com.forumsite.model.ForumThread','57'),(60,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f94c4e7e-87c9-46cd-9cbb-660450e51653','update,delete','com.forumsite.model.Comment','58'),(63,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','6','com.forumsite.model.ForumThread','61'),(64,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','update,delete','com.forumsite.model.Comment','62'),(67,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','6','com.forumsite.model.ForumThread','65'),(68,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','update,delete','com.forumsite.model.Comment','66'),(70,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','update,delete','com.forumsite.model.Comment','69'),(72,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','update,delete','com.forumsite.model.Comment','71'),(74,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','update,delete','com.forumsite.model.Comment','73'),(76,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','update,delete','com.forumsite.model.Comment','75'),(78,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','update,delete','com.forumsite.model.Comment','77'),(81,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','6','com.forumsite.model.ForumThread','79'),(82,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','update,delete','com.forumsite.model.Comment','80'),(84,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','update,delete','com.forumsite.model.Comment','83'),(86,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f94c4e7e-87c9-46cd-9cbb-660450e51653','update,delete','com.forumsite.model.Comment','85'),(88,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','update,delete','com.forumsite.model.Comment','87'),(91,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','6','com.forumsite.model.ForumThread','89'),(92,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','update,delete','com.forumsite.model.Comment','90'),(95,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','6','com.forumsite.model.ForumThread','93'),(96,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','update,delete','com.forumsite.model.Comment','94'),(98,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','update,delete','com.forumsite.model.Comment','97'),(100,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','update,delete','com.forumsite.model.Comment','99'),(102,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','update,delete','com.forumsite.model.Comment','101'),(104,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','update,delete','com.forumsite.model.Comment','103'),(107,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','6','com.forumsite.model.ForumThread','105'),(108,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','update,delete','com.forumsite.model.Comment','106'),(110,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','update,delete','com.forumsite.model.Comment','109'),(112,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','update,delete','com.forumsite.model.Comment','111'),(115,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','6','com.forumsite.model.ForumThread','113'),(116,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','update,delete','com.forumsite.model.Comment','114'),(118,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','update,delete','com.forumsite.model.Comment','117'),(121,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','6','com.forumsite.model.ForumThread','119'),(122,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:450d0bed-ffc8-4e1f-b4b2-824bf39e8870','update,delete','com.forumsite.model.Comment','120'),(124,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','update,delete','com.forumsite.model.Comment','123'),(126,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f94c4e7e-87c9-46cd-9cbb-660450e51653','update,delete','com.forumsite.model.Comment','125'),(128,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f94c4e7e-87c9-46cd-9cbb-660450e51653','update,delete','com.forumsite.model.Comment','127'),(130,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f2c91e4d-8b24-4a09-ae83-f2ed2e813a43','update,delete','com.forumsite.model.Comment','129'),(132,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:9596adea-e4ca-455b-b062-ba7d2831b85a','update,delete','com.forumsite.model.Comment','131'),(134,'org.picketlink.idm.model.basic.User:f209b353-676f-4222-bd5a-f0ea0b320b27:f94c4e7e-87c9-46cd-9cbb-660450e51653','update,delete','com.forumsite.model.Comment','133');
                                          
INSERT INTO Users VALUES (4,'An example user','email@email.com','4YmZAbQ.jpg','2017-05-03','username1'),(9,'An example user','email@email2.com','1490299849682.jpg','2017-05-03','username2'),(14,'An example user','email@email3.com','vRVcFnL.jpg','2017-05-03','username3'),(19,'An example user','email@email4.com','1.jpg','2017-05-03','username4');
                          
INSERT INTO ForumThread VALUES (24,'First Person Shooter','2017-05-07',261,'2017-05-10 20:36:44','CS:GO',3,19),(29,'First Person Shooter','2017-05-07',189,'2017-05-10 20:54:06','Arma 3',3,9),(33,'First Person Shooter','2017-05-10',250,'2017-05-10 20:34:15','COD 4',3,4),(45,'First Person Shooter','2017-05-06',134,'2017-05-06 20:34:47','Doom 4',1,4),(49,'First Person Shooter','2017-05-10',23,'2017-05-10 20:41:15','Overwatch',2,4),(57,'First Person Shooter','2017-05-10',14,'2017-05-09 19:22:47','Quake 3',1,14),(61,'Real Time Strategy','2017-05-10',55,'2017-05-11 13:04:50','Rome : Total War',12,19),(65,'Real Time Strategy','2017-05-10',678,'2017-05-10 21:58:25','Red Alert',3,19),(79,'Offtopic','2017-05-10',34,'2017-05-10 21:49:01','Nam pulvinar vel sapien in tempor.',4,9),(89,'Offtopic','2017-05-10',47,'2017-05-10 21:50:25',' Phasellus pretium ultrices ultricies',1,19),(93,'Real Time Strategy','2017-05-10',598,'2017-05-10 21:57:48','Starcraft 2',2,19),(105,'Role Play Game','2017-05-10',167,'2017-05-10 22:12:54','Mass Effect Andromeda',3,4),(113,'Role Play Game','2017-05-10',31,'2017-05-11 12:55:05','Diablo III',3,9),(119,'Sports','2017-05-10',2,'2017-05-11 12:51:02','Gran Turismo',1,9);
                           
INSERT INTO Comment VALUES (25,'2017-05-07 13:32:47','###Nam commodo eget purus nec euismod. \r\nOx\r\n',19,24),(27,'2017-05-03 19:42:44','> username4 said: **Nam commodo eget purus nec euismod.** \r\n\r\nMauris iaculis arcu quis mollis elementum. Ut id elementum lorem. **Morbi justo justo, bibendum venenatis egestas ut, suscipit in neque.**\r\n ',9,24),(30,'2017-05-08 23:32:47','Vivamus consequat,_ turpis vehicula semper facilisis_, erat neque luctus erat, egestas rhoncus felis nulla luctus tortor. Etiam imperdiet maximus posuere. Cras non mi vel justo sodales sodales. Pellentesque sit amet neque quam. Sed ac nulla non mauris egestas rhoncus. **Vivamus molestie ligula mi,** sed porttitor orci pulvinar ut. Praesent eleifend orci ut purus eleifend, eget feugiat justo laoreet. Nulla sed rutrum sapien. Fusce in vulputate lacus. Phasellus finibus, est eu condimentum porttitor, lorem augue dictum arcu, id pellentesque augue odio quis nisi.',9,29),(34,'2017-05-09 01:32:47','Morbi ac mi eros. Aenean elementum in quam in bibendum. Morbi finibus nibh fermentum sagittis porta. Aliquam sit amet pulvinar nulla. Nunc ut dui dui. Mauris laoreet non metus sed vehicula. Aliquam vehicula ligula eu elementum cursus. Nam maximus dignissim est. **Donec ut nulla ac purus** vulputate sodales a rhoncus nunc. Vestibulum semper quis erat ut placerat. Quisque congue sed nibh eu vehicula. Ut aliquam risus bibendum justo semper, a mollis augue consequat.',4,33),(37,'2017-05-10 20:32:47','> username1 said: Morbi ac mi eros. Aenean elementum in quam in bibendum. Morbi finibus nibh fermentum sagittis porta. Aliquam sit amet pulvinar nulla. Nunc ut dui dui. Mauris laoreet non metus sed vehicula. Aliquam vehicula ligula eu elementum cursus. Nam maximus dignissim est. **Donec ut nulla ac purus** vulputate sodales a rhoncus nunc. Vestibulum semper quis erat ut placerat. Quisque congue sed nibh eu vehicula. Ut aliquam risus bibendum justo semper, a mollis augue consequat. \r\n\r\nProin non convallis odio, vel gravida velit. Quisque ut diam risus. Morbi ut risus eget leo sagittis venenatis sed sed ante. Nulla pretium mattis sollicitudin. Aenean et mi in dolor ullamcorper scelerisque. __Mauris pulvinar__',9,33),(39,'2017-05-10 20:34:15','Morbi ac mi eros. Aenean elementum in quam in bibendum. Morbi finibus nibh fermentum sagittis porta. Aliquam sit amet pulvinar nulla. Nunc ut dui dui. Mauris laoreet non metus sed vehicula. Aliquam vehicula ligula eu elementum cursus. Nam maximus dignissim est.',4,33),(41,'2017-05-10 20:34:39','Morbi ac mi eros. Aenean elementum in quam in bibendum. Morbi finibus nibh fermentum sagittis porta. Aliquam sit amet pulvinar nulla. \r\n> Nunc ut dui dui. \r\n\r\nMauris laoreet non metus sed vehicula. Aliquam vehicula ligula eu elementum cursus. Nam maximus dignissim est.',4,29),(43,'2017-05-10 20:36:57','Vivamus consectetur libero sit amet odio iaculis finibus. Nunc consectetur consequat nibh, mattis volutpat urna molestie quis. Etiam a massa felis. ',4,24),(46,'2017-05-06 20:34:47',' Pellentesque facilisis viverra velit eu volutpat. Maecenas vitae faucibus massa. Morbi id leo quis purus tincidunt bibendum vitae imperdiet sapien. Sed interdum non felis at lobortis. Nullam lectus leo, interdum et diam eget, tempor porttitor mi. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.',4,45),(50,'2017-05-07 12:32:47',' Morbi ut risus eget leo sagittis venenatis sed sed ante. Nulla pretium mattis sollicitudin. Aenean et mi in dolor ullamcorper scelerisque.',4,49),(53,'2017-05-10 20:41:09','> username1 said:  Morbi ut risus eget leo sagittis venenatis sed sed ante. Nulla pretium mattis sollicitudin. Aenean et mi in dolor ullamcorper scelerisque. \r\n\r\nNullam lectus leo, interdum et diam eget, tempor porttitor mi. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. **Morbi vestibulum** mi sit amet elit sollicitudin posuere. Phasellus condimentum ex eu lorem placerat.',19,49),(55,'2017-05-10 20:54:06','**Nulla sagittis**, urna eu dictum consequat, sem sapien aliquam lectus, sit amet elementum massa sem a felis. Cras pharetra lacus ipsum, a imperdiet magna luctus a. Mauris tempor nec lorem non tristique. Quisque vehicula, magna in tristique varius, nunc arcu mattis velit, a suscipit elit eros ut nisl. Interdum et malesuada fames ac ante ipsum primis in faucibus. ',14,29),(58,'2017-05-09 19:22:47','Quisque ut diam risus. Morbi ut risus eget leo sagittis venenatis sed sed ante. Nulla pretium mattis sollicitudin. \r\n\r\n1. Aenean et mi in dolor ullamcorper scelerisque. \r\n\r\n2. Mauris pulvinar, augue at sodales vulputate, ipsum elit porttitor orci, ac egestas nisl velit id libero. \r\n\r\nCras pharetra lacus ipsum, a imperdiet magna luctus a.\r\n',14,57),(62,'2017-05-04 20:32:47','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam vel elit mi. \r\n\r\n- Nam pulvinar vel sapien in tempor. Integer sit amet placerat mi. In tempus vulputate molestie. \r\n\r\n- In blandit tempus elit quis pulvinar. Aenean dapibus aliquet orci vel fringilla. \r\n\r\n**Donec id feugiat neque.** Praesent a blandit leo, non venenatis ligula. Donec sed magna at dui ullamcorper pulvinar. Phasellus tristique luctus commodo. Sed gravida efficitur lacus aliquet eleifend. Nulla facilisi. Aliquam iaculis turpis sit amet risus imperdiet tristique. Nullam laoreet neque id maximus interdum. Praesent tempus magna vitae ante imperdiet faucibus. ',19,61),(66,'2017-05-05 15:32:47','Donec tempus elit a neque laoreet fringilla. Morbi facilisis sit amet sem in porta. Vivamus eleifend bibendum nisi a facilisis. Nunc sit amet efficitur tortor. Proin sagittis metus massa, at sagittis orci vestibulum in. Vestibulum rutrum ipsum sed est commodo gravida. Vestibulum mattis sem porttitor hendrerit venenatis. Nulla dapibus, ipsum vitae elementum malesuada, urna nisl tincidunt metus, a accumsan justo nisl vel massa. __Integer dictum finibus lectus__, aliquet ullamcorper ante pulvinar interdum. Curabitur ultrices mauris nec purus lacinia, vitae sagittis lacus tempor. ',19,65),(69,'2017-05-10 21:07:59','Nulla facilisi. Aliquam iaculis turpis sit amet risus imperdiet tristique. Nullam laoreet neque id maximus interdum. **Praesent tempus magna vitae ante imperdiet faucibus**. ',4,61),(71,'2017-05-10 21:10:04','> username4 said: Donec tempus elit a neque laoreet fringilla. Morbi facilisis sit amet sem in porta. Vivamus eleifend bibendum nisi a facilisis. Nunc sit amet efficitur tortor. Proin sagittis metus massa, at sagittis orci vestibulum in. Vestibulum rutrum ipsum sed est commodo gravida. Vestibulum mattis sem porttitor hendrerit venenatis.\r\n\r\nInterdum et malesuada fames ac ante ipsum primis in faucibus. Nullam at ligula ac lorem porta faucibus ut id nunc. Sed accumsan cursus neque, id condimentum arcu ullamcorper eu. Praesent ultrices ligula ac eros malesuada consectetur. Donec laoreet turpis dui, at ornare nunc semper at.',4,65),(73,'2017-05-10 21:19:39','Vestibulum laoreet, enim mollis commodo accumsan, est est ullamcorper turpis, eu volutpat augue turpis a nulla. Praesent id ipsum et elit feugiat malesuada non nec quam. Aliquam ullamcorper leo eros, pellentesque ultrices urna tempus vel.',9,61),(75,'2017-05-10 21:20:00','Morbi facilisis sit amet sem in porta. Vivamus eleifend bibendum nisi a facilisis. Nunc sit amet efficitur tortor. Proin sagittis metus massa, at sagittis orci vestibulum in. Vestibulum rutrum ipsum sed est commodo gravida. Vestibulum mattis sem porttitor hendrerit venenatis. Nulla dapibus, ipsum vitae elementum malesuada, urna nisl tincidunt metus, a accumsan justo nisl vel massa. Integer dictum finibus lectus, aliquet ullamcorper ante pulvinar interdum. Curabitur ultrices mauris nec purus lacinia, vitae sagittis lacus tempor. ',9,61),(77,'2017-05-10 21:30:33','> username2 said: Vestibulum laoreet, enim mollis commodo accumsan, est est ullamcorper turpis, eu volutpat augue turpis a nulla. Praesent id ipsum et elit feugiat malesuada non nec quam. Aliquam ullamcorper leo eros, pellentesque ultrices urna tempus vel. \r\n\r\n\r\nVestibulum laoreet, enim mollis commodo accumsan, est est ullamcorper turpis, eu volutpat augue turpis a nulla. Praesent id ipsum et elit feugiat malesuada non nec quam. Aliquam ullamcorper leo eros, pellentesque ultrices urna tempus vel. ',19,61),(80,'2017-05-10 21:39:29','Sed dictum porttitor leo, et efficitur nisl laoreet in. Vestibulum laoreet, enim mollis commodo accumsan, est est ullamcorper turpis, eu volutpat augue turpis a nulla. Praesent id ipsum et elit feugiat malesuada non nec quam. Aliquam ullamcorper leo eros, pellentesque ultrices urna tempus vel. Duis accumsan massa eros, vel scelerisque est tristique id. **Sed eu rhoncus mauris, vel molestie leo. Donec urna velit, egestas a mattis eu, varius ut elit**.',9,79),(83,'2017-05-10 21:40:20','Aliquam ac arcu quam. Vestibulum aliquam, felis dictum pellentesque iaculis, nisl lectus eleifend ex, id porttitor leo lorem eget tortor.',4,79),(85,'2017-05-10 21:44:13','> username1 said: Aliquam ac arcu quam. Vestibulum aliquam, felis dictum pellentesque iaculis, nisl lectus eleifend ex, id porttitor leo lorem eget tortor. \r\n\r\nProin sagittis metus massa, at sagittis orci vestibulum in. Vestibulum rutrum ipsum sed est commodo gravida. Vestibulum mattis sem porttitor hendrerit venenatis.',14,79),(87,'2017-05-10 21:49:01',' Donec sed magna at dui ullamcorper pulvinar. Phasellus tristique luctus commodo. Sed gravida efficitur lacus aliquet eleifend. Nulla facilisi. ',19,79),(90,'2017-05-10 21:50:25','Quisque vel maximus mi, id vulputate orci. Ut vel molestie justo, eu tempor quam. _Aliquam ac arcu quam._ Vestibulum aliquam, felis dictum pellentesque iaculis, nisl lectus eleifend ex, id porttitor leo lorem eget tortor.',19,89),(94,'2017-05-10 21:51:26','**Nullam at ligula ac lorem porta faucibus ut id nunc**. Sed accumsan cursus neque, id condimentum arcu ullamcorper eu. Praesent ultrices ligula ac eros malesuada consectetur. Donec laoreet turpis dui, at ornare nunc semper at. Aenean ac mattis massa. Quisque quis vulputate ligula.',19,93),(97,'2017-05-10 21:57:48','Suspendisse vel est cursus lorem accumsan fringilla nec in arcu. Cras a auctor elit. Quisque vel maximus mi, id vulputate orci. Ut vel molestie justo, eu tempor quam. ',9,93),(99,'2017-05-10 21:58:25','Cras mauris odio, molestie id odio quis, laoreet convallis turpis. Pellentesque et neque blandit, interdum tellus a, rutrum magna. Fusce ultrices dictum tempor. In et dolor eu erat congue finibus non et ligula. Mauris ac turpis fermentum, sagittis ipsum in, porta justo. Ut purus justo, dictum vitae tellus sit amet, pretium sollicitudin erat. Cras commodo lectus lacus, nec feugiat neque malesuada eu. Etiam tincidunt viverra eros, vel dapibus dolor rhoncus non. Nunc aliquet elit tortor, sit amet luctus risus rutrum vitae. ',9,65),(101,'2017-05-10 21:59:28','Nullam neque tortor, luctus non arcu at, gravida pulvinar risus. _Donec vitae vestibulum libero, mollis luctus velit._ Nunc pellentesque aliquet nulla. Vestibulum turpis arcu, convallis sed tristique in, consequat ut dui.',9,61),(103,'2017-05-10 22:07:27','Cras commodo lectus lacus, nec feugiat neque malesuada eu. Etiam tincidunt viverra eros, vel dapibus dolor rhoncus non. Nunc aliquet elit tortor, sit amet luctus risus rutrum vitae.',4,61),(106,'2017-05-10 22:08:08','Donec venenatis et ante sed pulvinar. Phasellus pulvinar ut magna et luctus. Donec tellus eros, euismod tristique elit eget, tincidunt dapibus lectus. Aliquam mollis, magna sed imperdiet tincidunt, turpis erat semper mi, nec iaculis dolor nunc nec arcu. _Integer ligula purus, volutpat in fringilla sit amet, sollicitudin nec risus._',4,105),(109,'2017-05-10 22:09:32','Nunc rutrum mauris et tellus fringilla, in sollicitudin lorem viverra. Aliquam convallis erat at nisi semper, at consectetur enim imperdiet. _In sollicitudin, diam quis faucibus semper_, enim nisi elementum ligula, elementum tempus nibh quam in neque.',9,105),(111,'2017-05-10 22:12:54','Donec vitae vestibulum libero, mollis luctus velit. Nunc pellentesque aliquet nulla. Vestibulum turpis arcu, convallis sed tristique in, consequat ut dui.',19,105),(114,'2017-05-11 12:48:13','Duis tincidunt ac augue quis ornare. Suspendisse porttitor rhoncus dolor, sit amet euismod est hendrerit vel. _Aenean auctor consequat tempor._ Curabitur eros lectus, dignissim eget neque non, aliquam tincidunt arcu. Quisque mollis gravida sapien, eget finibus elit condimentum ac.',9,113),(117,'2017-05-11 12:49:51','> username1 said: Cras commodo lectus lacus, nec feugiat neque malesuada eu. Etiam tincidunt viverra eros, vel dapibus dolor rhoncus non. Nunc aliquet elit tortor, sit amet luctus risus rutrum vitae. \r\n\r\nPraesent molestie neque a justo porttitor mattis. ',9,61),(120,'2017-05-11 12:51:02','Nullam aliquet leo a neque dignissim, sed vestibulum lacus imperdiet. Pellentesque metus libero, maximus congue tristique id, porttitor vitae libero. Nam at laoreet augue, a laoreet ex. Quisque vel vulputate nunc, eget cursus elit. Praesent ut tempus neque. Pellentesque id ultrices neque. Fusce at finibus purus. Donec congue diam ex, quis porta lectus blandit vel. \r\n\r\n\r\n- Mauris eu sem vel nunc lobortis interdum. Cras mattis aliquam justo non vulputate. ',9,119),(123,'2017-05-11 12:54:36','Nullam aliquet leo a neque dignissim, sed vestibulum lacus imperdiet. Pellentesque metus libero, maximus congue tristique id, porttitor vitae libero. Nam at laoreet augue, a laoreet ex. Quisque vel vulputate nunc, eget cursus elit. Praesent ut tempus neque. Pellentesque id ultrices neque. Fusce at finibus purus. Donec congue diam ex, quis porta lectus blandit vel. Mauris eu sem vel nunc lobortis interdum. Cras mattis aliquam justo non vulputate. ',19,113),(125,'2017-05-11 12:55:05','Vivamus sit amet massa a tellus venenatis mollis sit amet a mauris. Morbi nisl diam, aliquet non ipsum sit amet, pellentesque commodo sem.',14,113),(127,'2017-05-11 13:01:44','Nam at laoreet augue, a laoreet ex. Quisque vel vulputate nunc, eget cursus elit. Praesent ut tempus neque. Pellentesque id ultrices neque. Fusce at finibus purus.',14,61),(129,'2017-05-11 13:02:53','uis eget vulputate odio. Ut vel elit eget quam viverra hendrerit ut ut dui. **Etiam gravida**, dolor eu cursus facilisis, leo tellus posuere leo, quis tempus sapien nisi sit amet nisl. ',4,61),(131,'2017-05-11 13:04:05','> username1 said: Cras commodo lectus lacus, nec feugiat neque malesuada eu. Etiam tincidunt viverra eros, vel dapibus dolor rhoncus non. Nunc aliquet elit tortor, sit amet luctus risus rutrum vitae. \r\n\r\nNullam aliquet leo a neque dignissim, sed vestibulum lacus _imperdiet_. Pellentesque metus libero, maximus congue tristique id, porttitor vitae libero. ',19,61),(133,'2017-05-11 13:04:50','Cras convallis suscipit felis non laoreet. Cras fringilla sodales varius. Nam id tellus at nunc maximus ornare eu ac mauris. Nam sed odio hendrerit, laoreet justo aliquet, molestie purus. Sed molestie sem nec faucibus fermentum. Nullam a molestie arcu, in bibendum elit. Sed a enim id ipsum vulputate porttitor. ',14,61);     
                                 
INSERT INTO hibernate_sequence VALUES (135);

