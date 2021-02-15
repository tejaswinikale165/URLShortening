
use neueda;

CREATE TABLE neueda.userdetails (
  id INT AUTO_INCREMENT,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (email) );

create table neueda.urldetails ( 
	id INT AUTO_INCREMENT PRIMARY KEY,
	longURL VARCHAR(255) NOT NULL,
	preshitcount int,
    hrlyhitcount int,
	userid int,
	createddate datetime not null,
	FOREIGN KEY (userid) REFERENCES userdetails(id) );
  
  create table neueda.urlanalysisdw ( 
	id INT ,
	longURL VARCHAR(255) NOT NULL,
	hitcount int,
	userid int,
      urldate date,
      urltime time,
      urlyear int,
      urlday  int,
      urlhour int);

delimiter |  
CREATE EVENT IF NOT EXISTS reurring_event
ON SCHEDULE EVERY 1 hour
STARTS CURRENT_TIMESTAMP
DO
begin
INSERT INTO neueda.urlanalysisdw ( 
     id, 
      longurl, 
      hitcount, 
      userid,
      urldate,
      urltime,
      urlyear,
      urlday,
      urlhour) 
SELECT id, 
       longurl, 
       hrlyhitcount, 
       userid,
       DATE(now()),
       time(now()),
       year(now()),
       day(now()),
       hour(now())
       
FROM neueda.urldetails
WHERE userid != 1;
update neueda.urldetails set hrlyhitcount=0 where userid!=1;

 END |

delimiter ;


insert into neueda.userdetails (email,password) values("notuser","notuser");
