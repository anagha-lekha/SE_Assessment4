import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionTest {

    @org.junit.jupiter.api.Test
    void canAddPrescription1() {
        //both objects pass and are written to file
        Prescription testPres1 = new Prescription(
                123,
                "Anagha",
                "Lekha",
                "Kristal Jade, ORR, Bellandur, Bengaluru",
                20,
                180,
                4,
                "12-02-99",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("remark1", "remark2"))
        );

        Prescription testPres2 = new Prescription(
                124,
                "Anoop",
                "Bijukumar",
                "Kristal Olivine, ORR, Bellandur, Bengaluru",
                -20,
                0,
                -4,
                "12-02-99",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("remark1", "remark2"))
        );
        assertAll(
                () -> assertTrue(testPres1.addPrescription()),
                () -> assertTrue(testPres2.addPrescription())
        );
    }

    @org.junit.jupiter.api.Test
    void canAddPrescription2() {
        //first object fails at name check
        Prescription testPres1 = new Prescription(
                123,
                "An", //firstName length is less than 4
                "Lekha",
                "Kristal Jade, ORR, Bellandur, Bengaluru",
                20,
                180,
                4,
                "01-01-01",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("remark1", "remark2"))
        );

        //second object fails at address check
        Prescription testPres2 = new Prescription(
                124,
                "Anoop",
                "Bijukumar",
                "Sydney", //address length is less than 20
                -20,
                0,
                -4,
                "01-01-01",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("remark1", "remark2"))
        );
        assertAll(
                () -> assertFalse(testPres1.addPrescription()),
                () -> assertFalse(testPres2.addPrescription()));
    }

    @org.junit.jupiter.api.Test
    void canAddPrescription3() {
        //first object fails at date check
        Prescription testPres1 = new Prescription(
                123,
                "Anagha",
                "Lekha",
                "Kristal Jade, ORR, Bellandur, Bengaluru",
                20,
                180,
                4,
                "01-22-01", //date is not in dd/MM/yy format
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("remark1", "remark2"))
        );

        //second object fails at cylinder check
        Prescription testPres2 = new Prescription(
                124,
                "Anoop",
                "Bijukumar",
                "Kristal Olivine, ORR, Bellandur, Bengaluru",
                -20,
                0,
                -5, //cylinder less than -4
                "01-01-01",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("remark1", "remark2"))
        );
        assertAll(
                () -> assertFalse(testPres1.addPrescription()),
                () -> assertFalse(testPres2.addPrescription()));
    }

    @org.junit.jupiter.api.Test
    void canAddPrescription4() throws Exception {
        //first object fails at axis check
        Prescription testPres1 = new Prescription(
                123,
                "Anagha",
                "Lekha",
                "Kristal Jade, ORR, Bellandur, Bengaluru",
                20,
                200, //axis more than 180
                4,
                "01-01-01",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("remark1", "remark2"))
        );

        //second object fails at sphere check
        Prescription testPres2 = new Prescription(
                124,
                "Anoop",
                "Bijukumar",
                "Kristal Olivine, ORR, Bellandur, Bengaluru",
                50, //sphere more than 20
                0,
                -4,
                "01-01-01",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("remark1", "remark2"))
        );
        assertAll(
                () -> assertFalse(testPres1.addPrescription()),
                () -> assertFalse(testPres2.addPrescription()));
    }

    @org.junit.jupiter.api.Test
    void canAddPrescription5() throws Exception {
        //first object fails at firstname check
        Prescription testPres1 = new Prescription(
                123,
                "Anagha",
                "Lekha",
                "Kristal Jade, ORR, Bellandur, Bengaluru",
                20,
                200, //axis more than 180
                4,
                "01-01-01",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("remark1", "remark2"))
        );

        //second object fails at axis check
        Prescription testPres2 = new Prescription(
                124,
                "Anoop",
                "Bijukumar Chettikulangara", //lastname length greater than 15
                "Kristal Olivine, ORR, Bellandur, Bengaluru",
                20,
                0,
                -4,
                "01-01-01",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("remark1", "remark2"))
        );
        assertAll(
                () -> assertFalse(testPres1.addPrescription()),
                () -> assertFalse(testPres2.addPrescription()));
    }

    @org.junit.jupiter.api.Test
    void canAddPrescription6() throws Exception {
        //first object fails at firstname check
        Prescription testPres1 = new Prescription(
                123,
                "Anagha", //name is empty
                "Lekha",
                "Kristal Jade, ORR, Bellandur, Bengaluru",
                20,
                200, //axis more than 180
                4,
                "01-01-01",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("remark1", "remark2"))
        );

        //second object fails at axis check
        Prescription testPres2 = new Prescription(
                124,
                "Anoop",
                "Bijukumar",
                "Kristal Olivine, ORR, Bellandur, Bengaluru",
                20,
                -1, //axis is negative
                -4,
                "01-01-01",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("remark1", "remark2"))
        );
        assertAll(
                () -> assertFalse(testPres1.addPrescription()),
                () -> assertFalse(testPres2.addPrescription()));
    }

    @org.junit.jupiter.api.Test
    void canAddRemark1() throws Exception {
        //both objects pass and are written to file
        Prescription testPres1 = new Prescription(
                123,
                "Anagha",
                "Lekha",
                "Kristal Jade, ORR, Bellandur, Bengaluru",
                20,
                180,
                4,
                "12-02-99",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("Hi this is the first remark for Anagha", "Hi this is the second remark for Anagha"))
        );

        Prescription testPres2 = new Prescription(
                124,
                "Anoop",
                "Bijukumar",
                "Kristal Olivine, ORR, Bellandur, Bengaluru",
                -20,
                0,
                -4,
                "12-02-99",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("Hi this is the first remark for Anoop", "Hi this is the second remark for Anoop"))
        );
        assertAll(
                () -> assertTrue(testPres1.addRemark()),
                () -> assertTrue(testPres2.addRemark())
        );
    }

    @org.junit.jupiter.api.Test
    void canAddRemark2() throws Exception {
        //first object fails at remarks size check
        Prescription testPres1 = new Prescription(
                123,
                "Anagha",
                "Lekha",
                "Kristal Jade, ORR, Bellandur, Bengaluru",
                20,
                180,
                4,
                "12-02-99",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("Hi this is the first remark for Anagha", "Hi this is the second remark for Anagha", "Hi this is the third remark for Anagha")) //no. of remarks more than 2
        );

        //second object fails at no. of words check
        Prescription testPres2 = new Prescription(
                124,
                "Anoop",
                "Bijukumar",
                "Kristal Olivine, ORR, Bellandur, Bengaluru",
                -20,
                0,
                -4,
                "12-02-99",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("Hi this a remark", "Hi this is the second remark for Anoop")) //no. of words for first remark less than 6
        );
        assertAll(
                () -> assertFalse(testPres1.addRemark()),
                () -> assertFalse(testPres2.addRemark())
        );
    }

    @org.junit.jupiter.api.Test
    void canAddRemark3() throws Exception {
        //first object fails at uppercase check
        Prescription testPres1 = new Prescription(
                123,
                "Anagha",
                "Lekha",
                "Kristal Jade, ORR, Bellandur, Bengaluru",
                20,
                180,
                4,
                "12-02-99",
                "AkhilaLekha",
                new String[]{"client", "optometrist"},
                new ArrayList(Arrays.asList("hi this is the first remark for Anagha", "Hi this is the second remark for Anagha")) //first letter of first remark not in uppercase
        );

        //second object fails at remarkType check
        Prescription testPres2 = new Prescription(
                124,
                "Anoop",
                "Bijukumar",
                "Kristal Olivine, ORR, Bellandur, Bengaluru",
                -20,
                0,
                -4,
                "12-02-99",
                "AkhilaLekha",
                new String[]{"notclient", "optometrist"}, //remark type not of value client
                new ArrayList(Arrays.asList("Hi this is the first remark for Anagha", "Hi this is the second remark for Anoop"))
        );
        assertAll(
                () -> assertFalse(testPres1.addRemark()),
                () -> assertFalse(testPres2.addRemark())
        );
    }
}