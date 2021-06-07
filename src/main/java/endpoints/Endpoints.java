package endpoints;

public final class Endpoints {

    public static class Pet {
        public static final String FIND_BY_STATUS = "/pet/findByStatus";
        public static final String ADD_PUT = "/pet";
        public static final String FIND_UPDATE_DELETE = "/pet/";
    }

    public static class Store {
        public static final String GET_INVENTORIES = "/store/inventory";
        public static final String ADD_ORDER = "/store/order";
        public static final String FIND_DELETE_ORDER = "/store/order/";
    }

    public static class User {

    }
}
