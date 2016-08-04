/**
 * Created by alexandru on 7/21/16.
 */
package jlg.jade.test.asterix.cat048;

import jlg.jade.asterix.cat048.AuralCalculator;
import jlg.jade.asterix.cat048.TCASVersion;
import jlg.jade.asterix.cat048.Cat048Item260;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class AuralCalculatorTest {

    @Test
    public void the_aural_calculator_should_correctly_calculate_aural_for_version604() {
        // arrange
        byte[] input = {48, (byte) 128, 0, 0, 0, 0, 0};
        int offset = 0;
        AuralCalculator auralCalculator = new AuralCalculator();
        Cat048Item260 item260 = new Cat048Item260();

        // act
        item260.decode(input, offset, input.length);
        String auralCode = auralCalculator.determineAuralCode(item260, TCASVersion.VERSION_604);

        // assert
        assertEquals("Aural code is incorrect!", "CCC, or CXC-CXC, or IC-IC, or CCN-CCN",
                     auralCode);
    }

    @Test
    @Parameters({"128, MVS", "166, MVSXM", "198, CXC-CXC", "230, DXD-DXD", "224, AVSA"})
    public void the_aural_calculator_should_correctly_calculate_aural_for_version70(
            int secondInputByte, String expectedAural) {
        // arrange
        byte[] input = {48, (byte) secondInputByte, 0, 0, 0, 0, 0};
        int offset = 0;
        AuralCalculator auralCalculator = new AuralCalculator();
        Cat048Item260 item260 = new Cat048Item260();

        // act
        item260.decode(input, offset, input.length);
        String auralCode = auralCalculator.determineAuralCode(item260, TCASVersion.VERSION_70);

        // assert
        assertEquals("Aural code is incorrect", expectedAural, auralCode);
    }

    @Test
    @Parameters({"128, MVS", "166, MVSXM", "198, CXC-CXC", "230, DXD-DXD", "224, LOLO"})
    public void the_aural_calculator_should_correctly_determine_the_level_off_aural_for_version71(int secondInputByte, String expectedAural) {
        // arrange
        byte[] input = {48, (byte) secondInputByte, 0, 0, 0, 0, 0};
        int offset = 0;
        AuralCalculator auralCalculator = new AuralCalculator();
        Cat048Item260 item260 = new Cat048Item260();

        // act
        item260.decode(input, offset, input.length);
        String auralCode = auralCalculator.determineAuralCode(item260, TCASVersion.VERSION_71);

        // assert
        assertEquals("Aural code is incorrect", expectedAural, auralCode);
    }
}
