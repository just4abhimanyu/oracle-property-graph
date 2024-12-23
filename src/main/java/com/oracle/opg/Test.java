//package com.oracle.opg;
//
//import java.sql.DriverManager;
//import java.sql.Connection;
//
//
//public class Test {
//
//    public static void main(String[] args) throws Exception {
//        String dbConnectString = args[0];
//        String username = args[1];
//        String password = args[2];
//
//        // Obtain a JDBC database connectionå
//        DriverManager.registerDriver(new PgqlJdbcRdbmsDriver());
//        String jdbcUrl = "jdbc:oracle:pgql:@" + dbConnectString;
//        System.out.println("connecting to " + jdbcUrl);
//
//        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
//            conn.setAutoCommit(false);
//
//            // Create PGQL connection
//            PgqlConnection pgqlConn = PgqlConnection.getConnection(conn);
//
//            // Create a PGQL statement to execute PGQL queries
//            PgqlStatement pgqlStmt = pgqlConn.createStatement();
//
//            // Create a PGQL property graph using the CREATE PROPERTY GRAPH statement
//            String pgPgqlName = "BANK_GRAPH";
//            String createPgPgqlQuery =
//                    "CREATE PROPERTY GRAPH " + pgPgqlName + " " +
//                            "VERTEX TABLES ( BANK_ACCOUNTS AS ACCOUNTS " +
//                            "KEY (ID) " +
//                            "LABEL ACCOUNTS " +
//                            "PROPERTIES (ID, NAME)" +
//                            ") " +
//                            "EDGE TABLES ( BANK_TXNS AS TRANSFERS " +
//                            "KEY (FROM_ACCT_ID, TO_ACCT_ID, AMOUNT) " +
//                            "SOURCE KEY (FROM_ACCT_ID) REFERENCES ACCOUNTS (ID) " +
//                            "DESTINATION KEY (TO_ACCT_ID) REFERENCES ACCOUNTS (ID) " +
//                            "LABEL TRANSFERS " +
//                            "PROPERTIES (FROM_ACCT_ID, TO_ACCT_ID, AMOUNT, DESCRIPTION)" +
//                            ") OPTIONS(PG_PGQL)";
//
//            pgqlStmt.execute(createPgPgqlQuery);
//
//            // Execute a query to retrieve the first 10 elements of the graph
//            String pgqlQuery =
//                    "SELECT e.from_acct_id, e.to_acct_id, e.amount FROM " +
//                            "MATCH (n:ACCOUNTS) -[e:TRANSFERS]-> (m:ACCOUNTS) ON " +
//                            pgPgqlName + " LIMIT 10";
//
//            PgqlResultSet rs = pgqlStmt.executeQuery(pgqlQuery);
//            rs.print();
//
//            // Drop the PGQL property graph using the DROP PROPERTY GRAPH statement
//            String dropPgPgqlQuery = "DROP PROPERTY GRAPH " + pgPgqlName;
//            pgqlStmt.execute(dropPgPgqlQuery);
//        }
//        System.exit(0);
//    }
//}
