package de.idos.guava;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public class NameSupplier implements Supplier<String> {

    private final Clock clock= Clock.systemDefaultZone();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss-A");

    public NameSupplier() {
//        this.clock = Clock.systemDefaultZone();
//        try {
//            Thread.sleep(2000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public String whatsYourName() {
        LocalDateTime localDateTime = LocalDateTime.now(clock);
        return String.format("Dummy-%s", localDateTime.format(formatter));
    }

    @Override
    public String get() {
        return whatsYourName();
    }
}
