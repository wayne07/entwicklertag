package de.idos.guava;

import org.junit.Test;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public class GuavaDemoTest {

    @Test
    public void useSupplier() throws Exception {
        Supplier<NameSupplier> supplier = Suppliers.ofInstance(new NameSupplier());
        printName(supplier);
        printName(supplier);
        printName(supplier);
        System.out.println("ENDE");
    }

    @Test
    public void useSupplierMemoized() throws Exception {
        Supplier<NameSupplier> supplier = Suppliers.ofInstance(new NameSupplier());
        Supplier<NameSupplier> memoize = Suppliers.memoize(supplier);
        printName(memoize);
        printName(memoize);
        printName(memoize);
        System.out.println("ENDE");
    }

    private void printName(Supplier<NameSupplier> supplier) {
        NameSupplier nameSupplier = supplier.get();
        System.out.println(nameSupplier.whatsYourName());
    }

}
