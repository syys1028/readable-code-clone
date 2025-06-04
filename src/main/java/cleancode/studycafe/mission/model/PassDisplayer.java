package cleancode.studycafe.mission.model;

public class PassDisplayer {
    public static String display(PassDisplay pass) {
        StudyCafePassType passType = pass.getPassType();
        int duration = pass.getDuration();
        int price = pass.getPrice();

        if (passType == StudyCafePassType.HOURLY) {
            return String.format("%s시간권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.WEEKLY || passType == StudyCafePassType.FIXED) {
            return String.format("%s주권 - %d원", duration, price);
        }
        return "";
    }
}
