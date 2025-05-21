package it.powerservice.managermag;

public class FilterUtility {
    public static String getOperator(String operator) {
        String sqlOp = "";
        switch (operator) {
            case "contain":
                sqlOp = "CONTAIN";
                break;
            case "notcontain":
                sqlOp = "NOTCONTAIN";
                break;
            case "equal":
                sqlOp = "=";
                break;
            case "notequal":
                sqlOp = "<>";
                break;
            case "great":
                sqlOp = ">";
                break;
            case "less":
                sqlOp = "<";
                break;
            case "gte":
                sqlOp = ">=";
                break;
            case "lte":
                sqlOp = "<=";
                break;
            case "range":
                sqlOp = "BETWEEN";
                break;
            default:
                sqlOp = "";
        }
        return sqlOp;
    }

}
