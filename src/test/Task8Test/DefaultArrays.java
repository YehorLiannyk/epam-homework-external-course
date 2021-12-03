package Task8Test;

public interface DefaultArrays {
     String[] array = new String[] {
        "mama", "mia", "mama", "mia", "arn", "arn", "arn", "arn", "arn", "praga", "praga", "praga", "praga"
     };

    String[] arrayWithoutMama = new String[] {
            "", "mia", "", "mia", "arn", "arn", "arn", "arn", "arn", "praga", "praga", "praga", "praga"
    };

    String[] arrayWithoutMamaAndNull = new String[] {
            "mia", "mia", "arn", "arn", "arn", "arn", "arn", "praga", "praga", "praga", "praga"
    };

    String data = "mama;mia;mia;mama;arn;portugala;mama;mia;mia;mama;arn;portugala;";
}
