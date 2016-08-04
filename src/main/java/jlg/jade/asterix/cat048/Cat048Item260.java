/**
 * Created by alexandru on 7/14/16.
 */
package jlg.jade.asterix.cat048;

import jlg.jade.asterix.AsterixItemLength;
import jlg.jade.asterix.FixedLengthAsterixData;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Cat048Item260 - ACAS Resolution Advisory Report
 * Currently active Resolution Advisory (RA), if any, generated by the
 * ACAS associated with the transponder transmitting the report and
 * threat identity data.
 */
public class Cat048Item260 extends FixedLengthAsterixData {
    private int threatTypeIndicator;            // TTI
    private int TIDModeSAddress;                // TID ModeS Address
    private int multiThreatIndicator;           // MTI / MTE
    private int raTerminated;                   // RAT

    private List<String> RAComplementList = new ArrayList<>();         // RAC

    private int ARABit41;
    private int ARABit42;
    private int ARABit43;
    private int ARABit44;
    private int ARABit45;
    private int ARABit46;
    private int ARABit47;
    private int ARABit48;
    private int ARABit49;
    private int ARABit50;
    private int TIDAltitude;
    private int TIDRange;
    private int TIDBearing;

    @Override
    protected int setSizeInBytes() {
        return AsterixItemLength.SEVEN_BYTES.getValue();
    }

    @Override
    protected void decodeFromByteArray(byte[] input, int offset) {
        BitSet bs = BitSet.valueOf(input);

        // TTI
        final int TTI_BIT1_INDEX = 27;
        final int TTI_BIT0_INDEX = 26;

        int TTIBit1Value = 0;
        int TTIBit0Value = 0;

        if (bs.get(TTI_BIT1_INDEX)) {
            TTIBit1Value = 1;
        }

        if (bs.get(TTI_BIT0_INDEX)) {
            TTIBit0Value = 1;
        }

        this.threatTypeIndicator = TTIBit1Value * 2 + TTIBit0Value;

        appendItemDebugMsg("TTI", this.threatTypeIndicator);

        // when TTI = 1 then TID should contain a ModeS Address

        if (this.threatTypeIndicator == 1) {

            // create a String Builder to store the binary representation of the last 4 bytes for item260
            StringBuilder sb = new StringBuilder();
            for (int i = 3; i <= 6; i++) {
                // for each of the last 4 bytes in the input append the zero padded representation to the string builder
                String byteBinaryRepresentation = Integer.toBinaryString(input[i]);
                String zeroPaddedBinaryRepresentation = ("00000000" + byteBinaryRepresentation)
                        .substring(byteBinaryRepresentation.length());

                sb.append(zeroPaddedBinaryRepresentation);
            }

            // extract from the string builder the 24 bits
            String TIDModeSAddressRepresentation = sb.toString().substring(7, 30);

            // parse the extracted 24 bits binary represented string to get the ModeS Address
            this.TIDModeSAddress = Integer.parseInt(TIDModeSAddressRepresentation, 2);

            appendItemDebugMsg("TID ModeS Address", this.TIDModeSAddress);

        }

        // when TTI = 2 then TID should contain altitude, range and bearing

        // ARA - bits 41-50
        final int ARA_BIT41_INDEX = 15;
        final int ARA_BIT42_INDEX = 14;
        final int ARA_BIT43_INDEX = 13;
        final int ARA_BIT44_INDEX = 12;
        final int ARA_BIT45_INDEX = 11;
        final int ARA_BIT46_INDEX = 10;
        final int ARA_BIT47_INDEX = 9;
        final int ARA_BIT48_INDEX = 8;
        final int ARA_BIT49_INDEX = 23;
        final int ARA_BIT50_INDEX = 22;

        if (bs.get(ARA_BIT41_INDEX)) {
            this.ARABit41 = 1;
        }

        appendItemDebugMsg("ARABit41", this.ARABit41);

        if (bs.get(ARA_BIT42_INDEX)) {
            this.ARABit42 = 1;
        }

        appendItemDebugMsg("ARABit42", this.ARABit42);

        if (bs.get(ARA_BIT43_INDEX)) {
            this.ARABit43 = 1;
        }

        appendItemDebugMsg("ARABit43", this.ARABit43);

        if (bs.get(ARA_BIT44_INDEX)) {
            this.ARABit44 = 1;
        }

        appendItemDebugMsg("ARABit44", this.ARABit44);

        if (bs.get(ARA_BIT45_INDEX)) {
            this.ARABit45 = 1;
        }

        appendItemDebugMsg("ARABit45", this.ARABit45);

        if (bs.get(ARA_BIT46_INDEX)) {
            this.ARABit46 = 1;
        }

        appendItemDebugMsg("ARABit46", this.ARABit46);

        if (bs.get(ARA_BIT47_INDEX)) {
            this.ARABit47 = 1;
        }

        appendItemDebugMsg("ARABit47", this.ARABit47);

        if (bs.get(ARA_BIT48_INDEX)) {
            this.ARABit48 = 1;
        }

        appendItemDebugMsg("ARABit49", this.ARABit49);


        appendItemDebugMsg("ARABit48", this.ARABit48);

        if (bs.get(ARA_BIT49_INDEX)) {
            this.ARABit49 = 1;
        }

        appendItemDebugMsg("ARABit49", this.ARABit49);

        if (bs.get(ARA_BIT50_INDEX)) {
            this.ARABit50 = 1;
        }

        appendItemDebugMsg("ARABit50", this.ARABit50);

        // MTI / MTE
        final int MTI_BIT_INDEX = 28;

        if (bs.get(MTI_BIT_INDEX)) {
            this.multiThreatIndicator = 1;
        }

        appendItemDebugMsg("MTI", this.multiThreatIndicator);

        // RAT
        final int RAT_BIT_INDEX = 29;

        if (bs.get(RAT_BIT_INDEX)) {
            this.raTerminated = 1;
        }

        appendItemDebugMsg("RAT", this.raTerminated);

        // RAC
        final int RAC_BIT1_INDEX = 17;
        final int RAC_BIT2_INDEX = 16;
        final int RAC_BIT3_INDEX = 31;
        final int RAC_BIT4_INDEX = 30;

        if (bs.get(RAC_BIT1_INDEX)) {
            this.RAComplementList.add("Do not pass below");
        }

        if (bs.get(RAC_BIT2_INDEX)) {
            this.RAComplementList.add("Do not pass above");
        }

        if (bs.get(RAC_BIT3_INDEX)) {
            this.RAComplementList.add("Do not turn left");
        }

        if (bs.get(RAC_BIT4_INDEX)) {
            this.RAComplementList.add("Do not turn right");
        }

        if (!RAComplementList.isEmpty()) {
            String RAComplementListRepresentation = String.join(",", RAComplementList.stream()
                    .map(s -> s.toString()).collect(Collectors.toList()));
            appendItemDebugMsg("RA complements list", RAComplementListRepresentation);
        }
    }

    @Override
    protected String setDisplayName() {
        return "Cat048Item260 - ACAS Resolution Advisory Report";
    }

    public int getThreatTypeIndicator() {
        return threatTypeIndicator;
    }

    public int getARABit41() {
        return ARABit41;
    }

    public int getARABit42() {
        return ARABit42;
    }

    public int getARABit43() {
        return ARABit43;
    }

    public int getARABit44() {
        return ARABit44;
    }

    public int getARABit45() {
        return ARABit45;
    }

    public int getARABit46() {
        return ARABit46;
    }

    public int getARABit47() {
        return ARABit47;
    }

    public int getARABit48() {
        return ARABit48;
    }

    public int getARABit49() {
        return ARABit49;
    }

    public int getARABit50() {
        return ARABit50;
    }

    public int getMultiThreatIndicator() {
        return multiThreatIndicator;
    }

    public int getRaTerminated() {
        return raTerminated;
    }

    public List<String> getRAComplementList() {
        return RAComplementList;
    }

    /**
     * @return Returns the ModeS Address contained in the Threat Identity Data when Threat Type Indicator is 1
     */
    public int getTIDModeSAddress() {
        return TIDModeSAddress;
    }

    public int getTIDAltitude() {
        return TIDAltitude;
    }

    public int getTIDRange() {
        return TIDRange;
    }

    public int getTIDBearing() {
        return TIDBearing;
    }
}
