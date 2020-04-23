
use nikehealthchecker;

-- =============================
-- Table of DQ_USER_PROFILE_TAB
-- =============================

create table DQ_USER_PROFILE_TAB (
	USER_ID int not null identity (1, 1) primary key,
	USER_NAME varchar (30) default '' not null unique,
	USER_PASSWORD varchar (150) default '' not null
);

create index idx_USER_NAME_2_DQ_USER_PROFILE_TAB ON DQ_USER_PROFILE_TAB (USER_NAME);

-- ==========================
-- Table of DQ_USER_ROLE_TAB
-- ==========================

create table DQ_USER_ROLE_TAB (
	USER_ROLE_ID int not null identity (1, 1) primary key,
	ROLE_NAME varchar (30) default '' not null unique
);

create index idx_ROLE_NAME_2_DQ_USER_ROLE_TAB ON DQ_USER_ROLE_TAB (ROLE_NAME);

-- ==================================
-- Table of DQ_USER_ROLE_MAPPING_TAB
-- ==================================

create table DQ_USER_ROLE_MAPPING_TAB (
	USER_ID int not null,
	USER_ROLE_ID int not null
);

create index idx_DQ_USER_ROLE_MAPPING_TAB_USER_ID ON DQ_USER_ROLE_MAPPING_TAB (USER_ID, USER_ROLE_ID);
create index idx_DQ_USER_ROLE_MAPPING_TAB_USER_ROLE_ID ON DQ_USER_ROLE_MAPPING_TAB (USER_ROLE_ID);

-- =====================================
-- Table of DQ_USER_ROLE_PERMISSION_TAB
-- =====================================

create table DQ_USER_ROLE_PERMISSION_TAB (
	USER_ROLE_PERMISSION_ID int not null identity (1, 1) primary key,
	PERMISSION varchar (150) default '' not null
);

-- =============================================
-- Table of DQ_USER_ROLE_PERMISSION_MAPPING_TAB
-- =============================================

create table DQ_USER_ROLE_PERMISSION_MAPPING_TAB (
	USER_ROLE_ID int not null,
	USER_ROLE_PERMISSION_ID int not null
);

create index idx_DQ_USER_ROLE_PERMISSION_MAPPING_TAB_USER_ROLE_ID ON DQ_USER_ROLE_PERMISSION_MAPPING_TAB (USER_ROLE_ID, USER_ROLE_PERMISSION_ID);
create index idx_DQ_USER_ROLE_PERMISSION_MAPPING_TAB_USER_ROLE_PERMISSION_ID ON DQ_USER_ROLE_PERMISSION_MAPPING_TAB (USER_ROLE_PERMISSION_ID);

-- #######################################################################################################################################################

-- ==============================
-- Table of DQ_DB_CONNECTION_TAB
-- ==============================

create table DQ_DB_CONNECTION_TAB (
	CONNECTION_ID int not null identity (1, 1) primary key,
	CONNECTION_NAME varchar (300) default '' not null unique,
	CONNECTION_DESCRIPTION varchar (500) default '' not null,
	CONNECTION_SERVER_TYPE varchar (30) default '' not null,
	CONNECTION_SERVER_HOST varchar (100) default '' not null,
	CONNECTION_DB_LINK varchar (100) default '' not null,
	CONNECTION_DB_SCHEMA varchar (200) default '' not null,
	CONNECTION_CREATED_BY varchar (20) default '' not null,
	CONNECTION_CREATED_DATETIME datetime default getdate() not null
);

create index IDX_1_DQ_DB_CONNECTION_TAB on DQ_DB_CONNECTION_TAB (CONNECTION_NAME);

-- =============================
-- Table of DQ_RULE_TC_CASE_TAB
-- =============================

create table DQ_RULE_TC_CASE_TAB (
	RULE_CASE_ID int not null identity (1, 1) primary key,
	CONNECTION_ID int not null,
	RULE_CASE_NAME varchar (300) default '' not null unique,
	RULE_CASE_DESCRIPTION varchar (500) default '' not null,
	RULE_CASE_TARGET_DB varchar (200) default '' not null,
	RULE_CASE_TARGET_TABLE varchar (200) default '' not null,
	RULE_CASE_ORIG_TAB_SIZE int not null,
	RULE_CASE_ROWS_GAP_GT float not null,
	RULE_CASE_ROWS_GAP_LT float not null,
	RULE_CASE_SEVERITY varchar (25) default '' not null,
	RULE_CASE_CREATED_BY varchar (20) default '' not null,
	RULE_CASE_CREATED_DATETIME datetime default getdate() not null
);

create index IDX_1_DQ_RULE_TC_CASE_TAB on DQ_RULE_TC_CASE_TAB (CONNECTION_ID, RULE_CASE_TARGET_DB, RULE_CASE_TARGET_TABLE, RULE_CASE_NAME);
create index IDX_2_DQ_RULE_TC_CASE_TAB on DQ_RULE_TC_CASE_TAB (RULE_CASE_NAME);

-- =========================================
-- Table of DQ_RULE_TC_CASE_RUN_HISTORY_TAB
-- =========================================

create table DQ_RULE_TC_CASE_RUN_HISTORY_TAB (
	RULE_CASE_RUN_ID int not null identity (1, 1) primary key,
	RULE_CASE_ID int not null,
	RULE_CASE_RUN_ROWS int not null,
	RULE_CASE_RUN_START_DATE datetime default getdate() not null,
	RULE_CASE_RUN_END_DATE datetime default getdate() not null
);

create index IDX_1_DQ_RULE_TC_CASE_RUN_HISTORY_TAB on DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE);

-- =============================
-- Table of DQ_RULE_QS_CASE_TAB
-- =============================

create table DQ_RULE_QS_CASE_TAB (
	RULE_CASE_ID int not null identity (1, 1) primary key,
	CONNECTION_ID int not null,
	RULE_CASE_NAME varchar (300) default '' not null unique,
	RULE_CASE_DESCRIPTION varchar (500) default '' not null,
	RULE_CASE_SQL varchar (2000) default '' not null,
	RULE_CASE_SEVERITY varchar (25) default '' not null,
	RULE_CASE_CREATED_BY varchar (20) default '' not null,
	RULE_CASE_CREATED_DATETIME datetime default getdate() not null
);

create index IDX_1_DQ_RULE_QS_CASE_TAB on DQ_RULE_QS_CASE_TAB (CONNECTION_ID, RULE_CASE_NAME);
create index IDX_2_DQ_RULE_QS_CASE_TAB on DQ_RULE_QS_CASE_TAB (RULE_CASE_NAME);

-- =========================================
-- Table of DQ_RULE_QS_CASE_RUN_HISTORY_TAB
-- =========================================

create table DQ_RULE_QS_CASE_RUN_HISTORY_TAB (
	RULE_CASE_RUN_ID int not null identity (1, 1) primary key,
	RULE_CASE_ID int not null,
	RULE_CASE_RUN_RESULT int not null,
	RULE_CASE_RUN_START_DATE datetime default getdate() not null,
	RULE_CASE_RUN_END_DATE datetime default getdate() not null
);

create index IDX_1_DQ_RULE_QS_CASE_RUN_HISTORY_TAB on DQ_RULE_QS_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE);

-- ================================
-- Table of DQ_RULE_R21FC_CASE_TAB
-- ================================

create table DQ_RULE_R21FC_CASE_TAB (
	RULE_CASE_ID int not null identity (1, 1) primary key,
	CONNECTION_ID int not null,
	RULE_CASE_NAME varchar (300) default '' not null unique,
	RULE_CASE_OWNER varchar (50) default '' not null,
	RULE_CASE_BUSINESS_FUNCTION varchar (500) default '' not null,
	RULE_CASE_DESCRIPTION varchar (500) default '' not null,
	RULE_CASE_TARGET_TABLE varchar (200) default '' not null,
	RULE_CASE_TARGET_FIELD varchar (200) default '' not null,
	RULE_CASE_TARGET_CONDITION_FIELD varchar (500) default '' not null,
	RULE_CASE_LAST_MODIFIED_BY varchar (50) default '' not null,
	RULE_CASE_LAST_MODIFIED_DATE datetime default getdate() not null
);

create index IDX_1_DQ_RULE_R21FC_CASE_TAB on DQ_RULE_R21FC_CASE_TAB (RULE_CASE_NAME);

-- ============================================
-- Table of DQ_RULE_R21FC_CASE_RUN_HISTORY_TAB
-- ============================================

create table DQ_RULE_R21FC_CASE_RUN_HISTORY_TAB (
	RULE_CASE_RUN_ID int not null identity (1, 1) primary key,
	RULE_CASE_ID int not null,
	RULE_CASE_RUN_RESULT varchar (20) default '' not null,
	RULE_CASE_TGT_MEASURE_VAL varchar (20) default '' not null,
	RULE_CASE_TOLERANCE int not null,
	RULE_CASE_DIFFERENCE int not null,
	RULE_CASE_RUN_START_DATE datetime default getdate() not null,
	RULE_CASE_RUN_END_DATE datetime default getdate() not null
);

create index IDX_1_DQ_RULE_R21FC_CASE_RUN_HISTORY_TAB on DQ_RULE_R21FC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID);

-- ================================
-- Table of DQ_RULE_R33MC_CASE_TAB
-- ================================

create table DQ_RULE_R33MC_CASE_TAB (
	RULE_CASE_ID int not null identity (1, 1) primary key,
	RULE_CASE_NAME varchar (300) default '' not null unique,
	RULE_CASE_OWNER varchar (50) default '' not null,
	RULE_CASE_BUSINESS_FUNCTION varchar (500) default '' not null,
	RULE_CASE_DESCRIPTION varchar (500) default '' not null,
	SRC_CONNECTION_ID int not null,
	RULE_CASE_SOURCE_TABLE varchar (200) default '' not null,
	RULE_CASE_SOURCE_FIELD varchar (200) default '' not null,
	RULE_CASE_SOURCE_CONDITION_FIELD varchar (500) default '' not null,
	TGT_CONNECTION_ID int not null,
	RULE_CASE_TARGET_TABLE varchar (200) default '' not null,
	RULE_CASE_TARGET_FIELD varchar (200) default '' not null,
	RULE_CASE_TARGET_CONDITION_FIELD varchar (500) default '' not null,
	RULE_CASE_LAST_MODIFIED_BY varchar (50) default '' not null,
	RULE_CASE_LAST_MODIFIED_DATE datetime default getdate() not null
);

create index IDX_1_DQ_RULE_R33MC_CASE_TAB on DQ_RULE_R33MC_CASE_TAB (RULE_CASE_NAME);

-- ============================================
-- Table of DQ_RULE_R33MC_CASE_RUN_HISTORY_TAB
-- ============================================

create table DQ_RULE_R33MC_CASE_RUN_HISTORY_TAB (
	RULE_CASE_RUN_ID int not null identity (1, 1) primary key,
	RULE_CASE_ID int not null,
	RULE_CASE_RUN_RESULT varchar (20) default '' not null,
	RULE_CASE_SRC_MEASURE_VAL bigint not null,
	RULE_CASE_TGT_MEASURE_VAL bigint not null,
	RULE_CASE_DIFF_PCNT int not null,
	RULE_CASE_TOLERANCE int not null,
	RULE_CASE_RUN_START_DATE datetime default getdate() not null,
	RULE_CASE_RUN_END_DATE datetime default getdate() not null
);

create index IDX_1_DQ_RULE_R33MC_CASE_RUN_HISTORY_TAB on DQ_RULE_R33MC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID);
