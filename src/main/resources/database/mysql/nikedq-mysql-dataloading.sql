
use nikedq;

-- ====================
-- DQ_USER_PROFILE_TAB
-- ====================

INSERT INTO DQ_USER_PROFILE_TAB (USER_NAME, USER_PASSWORD) VALUES ('test', md5('password'));
INSERT INTO DQ_USER_PROFILE_TAB (USER_NAME, USER_PASSWORD) VALUES ('test1', md5('password'));
INSERT INTO DQ_USER_PROFILE_TAB (USER_NAME, USER_PASSWORD) VALUES ('test2', md5('password'));

-- =================
-- DQ_USER_ROLE_TAB
-- =================

INSERT INTO DQ_USER_ROLE_TAB (ROLE_NAME) VALUES ('suser');
INSERT INTO DQ_USER_ROLE_TAB (ROLE_NAME) VALUES ('user');
INSERT INTO DQ_USER_ROLE_TAB (ROLE_NAME) VALUES ('admin');

-- =========================
-- DQ_USER_ROLE_MAPPING_TAB
-- =========================

INSERT INTO DQ_USER_ROLE_MAPPING_TAB (USER_ID, USER_ROLE_ID) VALUES (1, 1);
INSERT INTO DQ_USER_ROLE_MAPPING_TAB (USER_ID, USER_ROLE_ID) VALUES (2, 2);
INSERT INTO DQ_USER_ROLE_MAPPING_TAB (USER_ID, USER_ROLE_ID) VALUES (3, 3);

-- ============================
-- DQ_USER_ROLE_PERMISSION_TAB
-- ============================

INSERT INTO DQ_USER_ROLE_PERMISSION_TAB (PERMISSION) VALUES ('suser:*');
INSERT INTO DQ_USER_ROLE_PERMISSION_TAB (PERMISSION) VALUES ('user:*');
INSERT INTO DQ_USER_ROLE_PERMISSION_TAB (PERMISSION) VALUES ('admin:*');

-- ====================================
-- DQ_USER_ROLE_PERMISSION_MAPPING_TAB
-- ====================================

INSERT INTO DQ_USER_ROLE_PERMISSION_MAPPING_TAB (USER_ROLE_ID, USER_ROLE_PERMISSION_ID) VALUES (1, 1);
INSERT INTO DQ_USER_ROLE_PERMISSION_MAPPING_TAB (USER_ROLE_ID, USER_ROLE_PERMISSION_ID) VALUES (1, 2);
INSERT INTO DQ_USER_ROLE_PERMISSION_MAPPING_TAB (USER_ROLE_ID, USER_ROLE_PERMISSION_ID) VALUES (1, 3);
INSERT INTO DQ_USER_ROLE_PERMISSION_MAPPING_TAB (USER_ROLE_ID, USER_ROLE_PERMISSION_ID) VALUES (2, 2);
INSERT INTO DQ_USER_ROLE_PERMISSION_MAPPING_TAB (USER_ROLE_ID, USER_ROLE_PERMISSION_ID) VALUES (3, 3);

-- #######################################################################################################################################################

-- =====================
-- DQ_DB_CONNECTION_TAB
-- =====================

INSERT INTO DQ_DB_CONNECTION_TAB (CONNECTION_NAME, CONNECTION_DESCRIPTION, CONNECTION_SERVER_TYPE, CONNECTION_SERVER_HOST, CONNECTION_DB_LINK, CONNECTION_CREATED_BY, CONNECTION_CREATED_DATETIME) VALUES ('LOCAL_CONN', 'LOCAL_CONN', 'MSSQLSERVER', 'LOCALHOST', 'LOCALHOST', 'TEST', CURRENT_TIMESTAMP);

-- ====================
-- DQ_RULE_TC_CASE_TAB
-- ====================

INSERT INTO DQ_RULE_TC_CASE_TAB (CONNECTION_ID, RULE_CASE_NAME, RULE_CASE_DESCRIPTION, RULE_CASE_TARGET_DB, RULE_CASE_TARGET_TABLE, RULE_CASE_ORIG_TAB_SIZE, RULE_CASE_ROWS_GAP_GT, RULE_CASE_ROWS_GAP_LT, RULE_CASE_SEVERITY, RULE_CASE_CREATED_BY, RULE_CASE_CREATED_DATETIME) VALUES (1, 'USER_PROFILE_SIZE', 'Verify table size of user profile', 'NIKEDQ', 'DQ_USER_PROFILE_TAB', 10, 10.0, 10.0, 'HIGH', 'TEST', CURRENT_TIMESTAMP);
INSERT INTO DQ_RULE_TC_CASE_TAB (CONNECTION_ID, RULE_CASE_NAME, RULE_CASE_DESCRIPTION, RULE_CASE_TARGET_DB, RULE_CASE_TARGET_TABLE, RULE_CASE_ORIG_TAB_SIZE, RULE_CASE_ROWS_GAP_GT, RULE_CASE_ROWS_GAP_LT, RULE_CASE_SEVERITY, RULE_CASE_CREATED_BY, RULE_CASE_CREATED_DATETIME) VALUES (1, 'USER_ROLE_SIZE', 'Verify table size of user role', 'NIKEDQ', 'DQ_USER_ROLE_TAB', 10, 10.0, 10.0, 'MEDIUM', 'TEST1', CURRENT_TIMESTAMP);
INSERT INTO DQ_RULE_TC_CASE_TAB (CONNECTION_ID, RULE_CASE_NAME, RULE_CASE_DESCRIPTION, RULE_CASE_TARGET_DB, RULE_CASE_TARGET_TABLE, RULE_CASE_ORIG_TAB_SIZE, RULE_CASE_ROWS_GAP_GT, RULE_CASE_ROWS_GAP_LT, RULE_CASE_SEVERITY, RULE_CASE_CREATED_BY, RULE_CASE_CREATED_DATETIME) VALUES (1, 'USER_ROLE_PERMISSION_SIZE', 'Verify table size of role permission', 'NIKEDQ', 'DQ_USER_ROLE_PERMISSION_TAB', 10, 10.0, 10.0, 'LOW', 'TEST2', CURRENT_TIMESTAMP);

-- ================================
-- DQ_RULE_TC_CASE_RUN_HISTORY_TAB
-- ================================

INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (1, 3, STR_TO_DATE('2019-03-24 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-24 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (1, 10, STR_TO_DATE('2019-03-25 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-25 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (1, 5, STR_TO_DATE('2019-03-26 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-26 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (1, 6, STR_TO_DATE('2019-03-27 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-27 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (1, 11, STR_TO_DATE('2019-03-28 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-28 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (1, 7, STR_TO_DATE('2019-03-29 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-29 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (1, 12, STR_TO_DATE('2019-03-30 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-30 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (1, 3, STR_TO_DATE('2019-03-31 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-31 16:45:11', '%Y-%m-%d %H:%i:%s'));

INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (2, 16, STR_TO_DATE('2019-03-24 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-24 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (2, 20, STR_TO_DATE('2019-03-25 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-25 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (2, 3, STR_TO_DATE('2019-03-26 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-26 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (2, 5, STR_TO_DATE('2019-03-27 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-27 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (2, 4, STR_TO_DATE('2019-03-28 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-28 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (2, 10, STR_TO_DATE('2019-03-29 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-29 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (2, 3, STR_TO_DATE('2019-03-30 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-30 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (2, 6, STR_TO_DATE('2019-03-31 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-31 16:45:11', '%Y-%m-%d %H:%i:%s'));

INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (3, 5, STR_TO_DATE('2019-03-24 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-24 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (3, 18, STR_TO_DATE('2019-03-25 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-25 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (3, 6, STR_TO_DATE('2019-03-26 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-26 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (3, 3, STR_TO_DATE('2019-03-27 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-27 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (3, 3, STR_TO_DATE('2019-03-28 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-28 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (3, 9, STR_TO_DATE('2019-03-29 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-29 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (3, 10, STR_TO_DATE('2019-03-30 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-30 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_TC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_ROWS, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (3, 3, STR_TO_DATE('2019-03-31 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-03-31 16:45:11', '%Y-%m-%d %H:%i:%s'));

-- ====================
-- DQ_RULE_QS_CASE_TAB
-- ====================

INSERT INTO DQ_RULE_QS_CASE_TAB (CONNECTION_ID, RULE_CASE_NAME, RULE_CASE_DESCRIPTION, RULE_CASE_SQL, RULE_CASE_SEVERITY, RULE_CASE_CREATED_BY, RULE_CASE_CREATED_DATETIME) VALUES (1, 'DQ_RULE_TC_CASE_RUN_HISTORY_TAB_START_DATE', 'Verify run start date on today for rule tc case run history', 'select 1 as EXEC_RESULT from DQ_RULE_TC_CASE_RUN_HISTORY_TAB where RULE_CASE_ID = 1 having DATE_FORMAT(max(RULE_CASE_RUN_START_DATE), ''%Y-%m-%d'') = DATE_FORMAT(NOW(), ''%Y-%m-%d'')', 'HIGH', 'TEST', CURRENT_TIMESTAMP);

-- ================================
-- DQ_RULE_QS_CASE_RUN_HISTORY_TAB
-- ================================

INSERT INTO DQ_RULE_QS_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_RESULT, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE) VALUES (1, 1, STR_TO_DATE('2019-04-03 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-04-03 16:45:11', '%Y-%m-%d %H:%i:%s'));

-- =======================
-- DQ_RULE_R21FC_CASE_TAB
-- =======================

INSERT INTO DQ_RULE_R21FC_CASE_TAB (CONNECTION_ID, RULE_CASE_NAME, RULE_CASE_OWNER, RULE_CASE_BUSINESS_FUNCTION, RULE_CASE_DESCRIPTION, RULE_CASE_TARGET_DB, RULE_CASE_TARGET_TABLE, RULE_CASE_TARGET_FIELD, RULE_CASE_LAST_MODIFIED_BY, RULE_CASE_LAST_MODIFIED_DATE)
VALUES (1, 'Data Latency Check on Revenue/Demand', 'BARBARA GOU', 'Sales', 'Verify run transaction date on today for sales day level data', 'NIKEDQ', 'SALES_DAY_LEVEL_FACT', 'TRAN_DT', 'TEST', CURRENT_TIMESTAMP);
INSERT INTO DQ_RULE_R21FC_CASE_TAB (CONNECTION_ID, RULE_CASE_NAME, RULE_CASE_OWNER, RULE_CASE_BUSINESS_FUNCTION, RULE_CASE_DESCRIPTION, RULE_CASE_TARGET_DB, RULE_CASE_TARGET_TABLE, RULE_CASE_TARGET_FIELD, RULE_CASE_LAST_MODIFIED_BY, RULE_CASE_LAST_MODIFIED_DATE)
VALUES (1, 'Data Latency Check on Traffic', 'BARBARA GOU', 'Sales', 'Verify run traffic and transaction date on today for store', 'NIKEDQ', 'STORE_TRAFFIC_AND_TRANS_COUNT_FACT', 'DT', 'TEST', CURRENT_TIMESTAMP);

-- ===================================
-- DQ_RULE_R21FC_CASE_RUN_HISTORY_TAB
-- ===================================

INSERT INTO DQ_RULE_R21FC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_RESULT, RULE_CASE_TGT_MEASURE_VAL, RULE_CASE_TOLERANCE, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE)
VALUES (1, 'PASSED', '2019-04-05', 2, STR_TO_DATE('2019-04-05 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-04-05 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_R21FC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_RESULT, RULE_CASE_TGT_MEASURE_VAL, RULE_CASE_TOLERANCE, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE)
VALUES (2, 'PASSED', '2019-04-05', 2, STR_TO_DATE('2019-04-05 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-04-05 16:45:11', '%Y-%m-%d %H:%i:%s'));

-- =======================
-- DQ_RULE_R33MC_CASE_TAB
-- =======================

INSERT INTO DQ_RULE_R33MC_CASE_TAB (RULE_CASE_NAME, RULE_CASE_OWNER, RULE_CASE_BUSINESS_FUNCTION, RULE_CASE_DESCRIPTION, SRC_CONNECTION_ID, RULE_CASE_SOURCE_DB, RULE_CASE_SOURCE_TABLE, RULE_CASE_SOURCE_FIELD, RULE_CASE_SOURCE_CONDITION_FIELD, TGT_CONNECTION_ID, RULE_CASE_TARGET_DB, RULE_CASE_TARGET_TABLE, RULE_CASE_TARGET_FIELD, RULE_CASE_TARGET_CONDITION_FIELD, RULE_CASE_LAST_MODIFIED_BY, RULE_CASE_LAST_MODIFIED_DATE)
VALUES ('Recent 30 days Revenue/Demand value comparison against history', 'BARBARA GOU', 'Sales', 'Verify run transaction date on today for sales day level data between 2 months', 1, 'NIKEDQ', 'SALES_DAY_LEVEL_FACT', 'TOT_NET_SALES_RTL_AMT_PLUS_VAT', 'TRAN_DT > (now() - INTERVAL 60 DAY) and TRAN_DT <= (now() - INTERVAL 30 DAY)', 1, 'NIKEDQ', 'SALES_DAY_LEVEL_FACT', 'TOT_NET_SALES_RTL_AMT_PLUS_VAT', 'TRAN_DT > (now() - INTERVAL 30 DAY)', 'TEST', CURRENT_TIMESTAMP);
INSERT INTO DQ_RULE_R33MC_CASE_TAB (RULE_CASE_NAME, RULE_CASE_OWNER, RULE_CASE_BUSINESS_FUNCTION, RULE_CASE_DESCRIPTION, SRC_CONNECTION_ID, RULE_CASE_SOURCE_DB, RULE_CASE_SOURCE_TABLE, RULE_CASE_SOURCE_FIELD, RULE_CASE_SOURCE_CONDITION_FIELD, TGT_CONNECTION_ID, RULE_CASE_TARGET_DB, RULE_CASE_TARGET_TABLE, RULE_CASE_TARGET_FIELD, RULE_CASE_TARGET_CONDITION_FIELD, RULE_CASE_LAST_MODIFIED_BY, RULE_CASE_LAST_MODIFIED_DATE)
VALUES ('Recent 30 days traffic value comparison against history', 'BARBARA GOU', 'Sales', 'Verify run traffic and transaction date on today for store between 2 months', 1, 'NIKEDQ', 'STORE_TRAFFIC_AND_TRANS_COUNT_FACT', 'TRAFFIC_COUNT', 'DT > (now() - INTERVAL 60 DAY) and DT <= (now() - INTERVAL 30 DAY)', 1, 'NIKEDQ', 'STORE_TRAFFIC_AND_TRANS_COUNT_FACT', 'TRAFFIC_COUNT', 'DT > (now() - INTERVAL 30 DAY)', 'TEST', CURRENT_TIMESTAMP);

-- ===================================
-- DQ_RULE_R33MC_CASE_RUN_HISTORY_TAB
-- ===================================

INSERT INTO DQ_RULE_R33MC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_RESULT, RULE_CASE_SRC_MEASURE_VAL, RULE_CASE_TGT_MEASURE_VAL, RULE_CASE_DIFF_PCNT, RULE_CASE_TOLERANCE, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE)
VALUES (1, 'PASSED', 80, 100, 20, 50, STR_TO_DATE('2019-04-07 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-04-07 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO DQ_RULE_R33MC_CASE_RUN_HISTORY_TAB (RULE_CASE_ID, RULE_CASE_RUN_RESULT, RULE_CASE_SRC_MEASURE_VAL, RULE_CASE_TGT_MEASURE_VAL, RULE_CASE_DIFF_PCNT, RULE_CASE_TOLERANCE, RULE_CASE_RUN_START_DATE, RULE_CASE_RUN_END_DATE)
VALUES (2, 'PASSED', 100, 60, 40, 50, STR_TO_DATE('2019-04-07 16:45:11', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2019-04-07 16:45:11', '%Y-%m-%d %H:%i:%s'));

-- =====================
-- SALES_DAY_LEVEL_FACT
-- =====================

INSERT INTO SALES_DAY_LEVEL_FACT (TOT_NET_SALES_RTL_AMT_PLUS_VAT, TRAN_DT) VALUES (80, STR_TO_DATE('2019-03-01 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO SALES_DAY_LEVEL_FACT (TOT_NET_SALES_RTL_AMT_PLUS_VAT, TRAN_DT) VALUES (100, STR_TO_DATE('2019-04-06 16:45:11', '%Y-%m-%d %H:%i:%s'));

-- ===================================
-- STORE_TRAFFIC_AND_TRANS_COUNT_FACT
-- ===================================

INSERT INTO STORE_TRAFFIC_AND_TRANS_COUNT_FACT (TRAFFIC_COUNT, DT) VALUES (100, STR_TO_DATE('2019-03-02 16:45:11', '%Y-%m-%d %H:%i:%s'));
INSERT INTO STORE_TRAFFIC_AND_TRANS_COUNT_FACT (TRAFFIC_COUNT, DT) VALUES (60, STR_TO_DATE('2019-04-06 16:45:11', '%Y-%m-%d %H:%i:%s'));
