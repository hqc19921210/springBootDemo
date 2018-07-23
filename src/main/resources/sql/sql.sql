drop table if EXISTS USER_INFO;
create table USER_INFO(
	id  int auto_increment primary key,
	userNo varchar(100),
	name varchar(100),
	password varchar(200),
	createTime datetime DEFAULT CURRENT_TIMESTAMP
)

create table lightning_log(
	id int PRIMARY KEY AUTO_INCREMENT,
	createTime datetime DEFAULT CURRENT_TIMESTAMP,
	updateTime datetime DEFAULT CURRENT_TIMESTAMP,
	devEUI VARCHAR(200),
	time datetime,
	fPort int,
	gatewayCount int,
	rssi int,
	fCnt int,
	loRaSNR float,
	data VARCHAR(400),
	devicePath VARCHAR(200),
	functionCode VARCHAR(200),
	dataLen VARCHAR(200),
	ligntningCount int,
	ligntningTime VARCHAR(200),
	peakValue VARCHAR(200),
	effectiveValue VARCHAR(200),
	waveHeadTime VARCHAR(200),
	halfPeakTime VARCHAR(200),
	actionTime VARCHAR(200),
	energy VARCHAR(200),
	status VARCHAR(200)
)AUTO_INCREMENT = 1 ;