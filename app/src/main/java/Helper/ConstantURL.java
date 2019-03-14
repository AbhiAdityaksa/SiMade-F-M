package Helper;

public class ConstantURL {
    public static final String BASE_URL = "https://simade.itcc-udayana.com/";

    public static final class Permission {
        public static final int USER=0;
        public static final int ADMIN=1;
    }

    public static class URL {
        public static String api(){
            return BASE_URL+"api/";
        }

        public static String photoIdentity(String file){
            return BASE_URL+"uploads/identity/"+file;
        }

        public static String photoProfile(String file){
            return BASE_URL+"uploads/profile/"+file;
        }

        public static String photoCarity(String file){
            return BASE_URL+"uploads/carity/"+file;
        }

        public static String photoInfo(String file){
            return BASE_URL+"uploads/info/"+file;
        }
    }
}
